package unizar.labis.g03.backendapp.application.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.domain.model.entities.Persona
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository
import java.util.*


@Service
class BuscarPersonaService {
    @Autowired
    private lateinit var personaRepository: PersonaRepository
    fun buscarPersona(email: String): Optional<Persona> {
        return personaRepository.findByEmail(email)
    }
}