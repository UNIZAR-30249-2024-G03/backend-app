package unizar.labis.g03.backendapp.services

import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.repositories.PersonaRepository
import unizar.labis.g03.backendapp.repositories.ReservaRepository

@Service
class AnularReservaService @Autowired constructor(
    private val reservaRepository: ReservaRepository,
    private val personaRepository: PersonaRepository) {
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
            return true
        }
        return false
    }
}
