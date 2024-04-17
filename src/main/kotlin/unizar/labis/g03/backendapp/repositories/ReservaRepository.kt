package unizar.labis.g03.backendapp.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import unizar.labis.g03.backendapp.model.entities.Espacio
import unizar.labis.g03.backendapp.model.entities.Persona
import unizar.labis.g03.backendapp.model.entities.Reserva
import java.time.LocalDateTime


interface ReservaRepository : JpaRepository<Reserva?, String?> {
    @Query("SELECT r FROM Reserva r JOIN r.espacios e WHERE e.id = :id AND r.infoReserva.fechaInicio <= :horaInicio AND r.infoReserva.fechaFinal >= :horaFinal AND r.anulado = false")
    fun encontrarReservasConflictivas(
        @Param("id") id: String,
        @Param("horaInicio") horaInicio: LocalDateTime,
        @Param("horaFinal") horaFinal: LocalDateTime
    ): List<Reserva>
    fun findByPersona(persona: Persona?): List<Reserva>?
    fun findByAnulado(anulado: Boolean): List<Reserva>?
    @Query("SELECT r FROM Reserva r WHERE :espacio MEMBER OF r.espacios")
    fun findByEspacio(espacio: Espacio): List<Reserva>?
    @Query("UPDATE Reserva SET anulado = true WHERE id = ?1")
    fun anularReserva(idReserva: String) {
    }
}
