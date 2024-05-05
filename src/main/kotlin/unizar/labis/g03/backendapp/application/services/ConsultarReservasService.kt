package unizar.labis.g03.backendapp.application.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.domain.model.entities.Reserva
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository
import unizar.labis.g03.backendapp.domain.repositories.ReservaRepository

@Service
class ConsultarReservasService @Autowired constructor(
    private val personaRepository: PersonaRepository,
    private val reservaRepository: ReservaRepository
) {
    fun consultarReservas(emailPersona: String): List<Reserva>? {
        val persona = personaRepository.findByEmail(emailPersona)
        if (persona.isEmpty) return null
        return if (persona.get().esGerente()) {
            //Solo reservas vivas
            reservaRepository.findByAnulado(false)
        } else reservaRepository.findByPersona(persona.get())
    }
}
