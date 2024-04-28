package unizar.labis.g03.backendapp.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.model.entities.Persona
import unizar.labis.g03.backendapp.model.entities.Reserva
import unizar.labis.g03.backendapp.repositories.ReservaRepository

@Service
class ConsultarReservasService @Autowired constructor(private val reservaRepository: ReservaRepository) {
    fun consultarReservas(persona: Persona): List<Reserva>? {
        return if (persona.esGerente()) {
            //Solo reservas vivas
            reservaRepository.findByAnulado(false)
        } else reservaRepository.findByPersona(persona)
    }
}
