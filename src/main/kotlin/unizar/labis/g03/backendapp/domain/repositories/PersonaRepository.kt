package unizar.labis.g03.backendapp.domain.repositories

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import unizar.labis.g03.backendapp.domain.model.entities.Persona
import java.util.Optional

interface PersonaRepository : JpaRepository<Persona?, String?> {
    fun findByEmail(email: String): Optional<Persona>
    fun findByEmailIn(emails: List<String>): List<Persona>
    @Query("UPDATE Persona p SET p.notificaciones = p.notificaciones || :notificacion WHERE p.email = :email")
    fun addNotificacion(email: String, notificacion: String)


}
