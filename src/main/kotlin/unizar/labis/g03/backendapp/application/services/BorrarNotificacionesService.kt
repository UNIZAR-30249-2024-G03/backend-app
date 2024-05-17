package unizar.labis.g03.backendapp.application.services

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository

@Service
class BorrarNotificacionesService {
    private lateinit var personaRepository: PersonaRepository
    @Transactional
    fun borrarNotificaciones(email: String) {
        personaRepository.findByEmail(email).ifPresent { persona ->
            persona.borrarNotificaciones()
            personaRepository.save(persona)
        }
    }
}