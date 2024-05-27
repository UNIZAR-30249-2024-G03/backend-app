package unizar.labis.g03.backendapp.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import unizar.labis.g03.backendapp.domain.model.entities.Persona
import unizar.labis.g03.backendapp.domain.model.entities.Reserva
import java.time.LocalDateTime


interface ReservaRepository : JpaRepository<Reserva, String> {
    @Query("SELECT r FROM Reserva r JOIN r.espacios e WHERE e.id = :id AND (r.infoReserva.fechaInicio <= " +
            ":horaFinal OR r.infoReserva.fechaFinal >= :horaInicio) AND r.anulado = false")
    fun encontrarReservasConflictivas(
        @Param("id") id: String,
        @Param("horaInicio") horaInicio: LocalDateTime,
        @Param("horaFinal") horaFinal: LocalDateTime
    ): List<Reserva>
    fun findByPersona(persona: Persona?): List<Reserva>?
    fun findByAnulado(anulado: Boolean): List<Reserva>?

    @Modifying
    @Query("UPDATE Reserva SET anulado = true WHERE id = ?1")
    fun anularReserva(idReserva: Int?) {
    }

    @Modifying
    @Query("UPDATE Reserva SET anulado = true WHERE id IN :ids")
    fun anularReservas(@Param("ids") idsReservas: List<Int?>)




    @Query("SELECT r FROM Reserva r JOIN r.espacios e WHERE e.id = :id AND r.anulado = false AND  r.infoReserva.fechaFinal > :fechaActual")
    fun encontrarReservasEspacio(
        @Param("id") id: String,
        @Param("fechaActual") fechaActual: LocalDateTime,
    ): List<Reserva>
}
