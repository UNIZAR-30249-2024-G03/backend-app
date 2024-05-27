package unizar.labis.g03.backendapp.domain.model.valueObjects

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
    var tipoUso: TipoUsoReserva = TipoUsoReserva.Otros
    constructor() {}
    constructor(fechaInicio: LocalDateTime, fechaFinal: LocalDateTime, descripcion: String,maximaCapacidad: Int,numPersonas: Int, tipoUso: TipoUsoReserva) {
        this.numeroPersonas = numPersonas
        this.fechaInicio = fechaInicio
        this.fechaFinal = fechaFinal
        this.descripcion = descripcion
        this.maximaCapacidad = maximaCapacidad
        this.tipoUso = tipoUso
    }
    fun getHoraInicio(): Int{
        return fechaInicio.hour
    }
    fun getHoraFinal(): Int{
        return fechaFinal.hour
    }
    fun cambiarMaximaCapacidad(diferenciaCapacidad : Int){
        maximaCapacidad += diferenciaCapacidad
    }
    fun capacidadValida(): Boolean{
        return numeroPersonas <= maximaCapacidad
    }
    override fun toString(): String {
        return "InfoReserva(numeroPersonas=$numeroPersonas, fechaInicio=$fechaInicio, fechaFinal=$fechaFinal, descripcion='$descripcion', maximaCapacidad=$maximaCapacidad, tipoUso=$tipoUso)"
    }
}