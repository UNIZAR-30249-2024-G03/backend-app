package unizar.labis.g03.backendapp.model.valueObjects

import jakarta.persistence.Embeddable
import java.time.LocalDateTime

@Embeddable
class InfoReserva {
    var numeroPersonas: Int = 0
    lateinit var fechaInicio : LocalDateTime
    lateinit var fechaFinal : LocalDateTime
    // No se si llamarlo textoLibre
    var descripcion: String = ""
    var maximaCapacidad: Int = 0
    constructor() {}
    constructor(numMaxPersonas: Int, fechaInicio: LocalDateTime, fechaFinal: LocalDateTime, descripcion: String,maximaCapacidad: Int) {
        this.numeroPersonas = numeroPersonas
        this.fechaInicio = fechaInicio
        this.fechaFinal = fechaFinal
        this.descripcion = descripcion
        this.maximaCapacidad = maximaCapacidad
    }
    fun getHoraInicio(): Int{
        return fechaInicio.hour;
    }
    fun getHoraFinal(): Int{
        return fechaFinal.hour;
    }
}