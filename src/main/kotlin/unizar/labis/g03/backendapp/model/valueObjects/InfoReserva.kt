package unizar.labis.g03.backendapp.model.valueObjects

import java.io.Serializable
import java.util.Date

class InfoReserva : Serializable {
    val numMaxPersonas: Int = 0
    val fechaInicio : Date = Date()
    val fechaFinal : Date = Date()

    // No se si llamarlo textoLibre
    val descripcion: String = ""
}