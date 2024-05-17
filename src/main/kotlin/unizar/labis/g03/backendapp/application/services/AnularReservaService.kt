package unizar.labis.g03.backendapp.application.services

import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.domain.model.entities.Reserva
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository
import unizar.labis.g03.backendapp.domain.repositories.ReservaRepository
import unizar.labis.g03.backendapp.domain.services.NotificarPersonas

@Service
class AnularReservaService @Autowired constructor(
    private val reservaRepository: ReservaRepository,
    private val personaRepository: PersonaRepository,
    private val notificarPersonas: NotificarPersonas
) {

    @Transactional
    fun anularReserva(idPersona: String, idReserva: Int) : Boolean {
        val persona = personaRepository.findByEmail(idPersona)
        if (persona.isEmpty) return false
        val reserva = reservaRepository.findById(idReserva.toString())
        if (reserva.isEmpty) return false

        if (persona.get().esGerente()
            || reserva.get().persona == persona.get()
        ){
            reservaRepository.anularReserva(idReserva)
            notificarPersonas.notificaAnulacion(reserva.get())
            return true
        }
        return false
    }

    fun anularReserva(reserva: Reserva){

    }
}
