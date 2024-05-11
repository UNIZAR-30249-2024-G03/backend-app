package unizar.labis.g03.backendapp.application.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.domain.model.entities.Persona
import unizar.labis.g03.backendapp.domain.model.valueObjects.Departamento
import unizar.labis.g03.backendapp.domain.model.valueObjects.Rol
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository
import java.util.*

@Service
class ModificarPersonaService @Autowired constructor(private val personaRepository: PersonaRepository) {
    fun modificarPersona(email: String, roles: Set<Rol>, departamento: Departamento) {
        val persona: Optional<Persona> = personaRepository.findByEmail(email)
        if (persona.isPresent) {
            val personaModificada = persona.get()
            personaModificada.setRoles(roles)
            personaModificada.setDepartamento(departamento)
            personaRepository.save(personaModificada)
        } else {
            throw NotFoundException()
        }
    }
}
