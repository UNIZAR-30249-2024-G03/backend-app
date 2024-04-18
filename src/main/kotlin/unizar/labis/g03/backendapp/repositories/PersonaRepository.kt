package unizar.labis.g03.backendapp.repositories

import org.springframework.data.jpa.repository.JpaRepository
import unizar.labis.g03.backendapp.model.entities.Persona

interface PersonaRepository : JpaRepository<Persona?, String?> {
    fun findByEmail(email: String): Persona
}
