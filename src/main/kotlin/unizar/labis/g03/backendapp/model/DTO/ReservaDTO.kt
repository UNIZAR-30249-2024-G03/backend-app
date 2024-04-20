package unizar.labis.g03.backendapp.model.DTO

import lombok.Getter
import unizar.labis.g03.backendapp.model.valueObjects.InfoReserva
import java.time.LocalDateTime

class ReservaDTO // Constructor
    (
    @field:Getter private val idEspacios: List<String>,
    @field:Getter private val emailUsuario: String,
    private val fechaInicio: LocalDateTime,
    private val fechaFinal: LocalDateTime,
    private val numAsistentesPrevistos: Int,
    private val descripcion: String
) {
    fun getIdEspacios(): List<String> {
        return idEspacios
    }
    fun getEmailUsuario(): String {
        return emailUsuario
    }
    fun getFechaInicio(): LocalDateTime {
        return fechaInicio
    }
    fun getFechaFinal(): LocalDateTime {
        return fechaFinal
    }
    fun getNumAsistentesPrevistos(): Int {
        return numAsistentesPrevistos
    }

    fun getDescripcion(): String {
        return descripcion
    }
}
