package unizar.labis.g03.backendapp.domain.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.domain.model.entities.Reserva
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository

@Service
class NotificarPersonas {

    @Autowired
    private lateinit var personaRepository: PersonaRepository
    companion object {
        private val ANULACION_RESERVA_INVALIDA = "La reserva del dia %s ya no es válida"
    }

    fun notificaAnulacion(reserva: Reserva) {
        reserva.persona.addNotificacion(obtenerMensajeAnulacion(reserva))
        personaRepository.save(reserva.persona)
    }

    private fun obtenerMensajeAnulacion(reserva: Reserva): String {
        return String.format(ANULACION_RESERVA_INVALIDA, reserva.infoReserva.fechaInicio)
    }
}

