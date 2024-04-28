package unizar.labis.g03.backendapp.repositories

import org.springframework.data.jpa.repository.JpaRepository
import unizar.labis.g03.backendapp.model.entities.Persona
import java.util.Optional

interface PersonaRepository : JpaRepository<Persona?, String?> {
    fun findByEmail(email: String): Optional<Persona>
}
