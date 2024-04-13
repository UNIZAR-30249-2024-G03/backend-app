package unizar.labis.g03.backendapp.infrastructure.messaging

import java.util.Date


class FiltroEspacios{
    val id: String? = null
    val categoria: String? = null
    val maxOcupantes: Int? = null
    val planta: Int? = null
    override fun toString(): String {
        return "FiltroEspacios(id=$id, categoria=$categoria, maxOcupantes=$maxOcupantes, planta=$planta)"
    }
}

// Fechas deben estar en formato: yyyy-MM-dd'T'HH:mm:ss+02:00
class RequestReservaEspacio{
    val idEspacio: String? = null
    val idUsuario: String? = null
    val fechaInicio: Date? = null
    val fechaFinal: Date? = null
    val numAsistentesPrevistos: Int? = null
    val descripcion: String? = null
    override fun toString(): String {
        return "RequestReservaEspacio(idEspacio=$idEspacio, idUsuario=$idUsuario, fechaInicio=$fechaInicio, fechaFinal=$fechaFinal, numAsistentesPrevistos=$numAsistentesPrevistos, descripcion=$descripcion)"
    }
}


class RequestEliminarReserva{
    val idPersona: String? = null
    val idReserva: String? = null
    override fun toString(): String {
        return "RequestEliminarReserva(idPersona=$idPersona, idReserva=$idReserva)"
    }
}

class RequestCambiarCaracteristicasPersonal{
    val idPersona: String? = null
    val rol: String? = null
    val rolSecundario: String? = null
    val departamento : String? = null
    override fun toString(): String {
        return "RequestCambiarCaracteristicasPersonal(idPersona=$idPersona, rol=$rol, rolSecundario=$rolSecundario, departamento=$departamento)"
    }
}

class RequestCambiarCaracteristicasEspacio{
    val idEspacio: String? = null
    val idPersona: String? = null
    val reservable: Boolean? = null
    val tipoEspacio: String? = null
    val horaInicio: String? = null
    val horaFinal: String? = null
    val porcentajeMaximoPermitido: Double? = null
    override fun toString(): String {
        return "RequestCambiarCaracteristicasEspacio(idEspacio=$idEspacio, idPersona=$idPersona, reservable=$reservable, tipoEspacio=$tipoEspacio, horaInicio=$horaInicio, horaFinal=$horaFinal, porcentajeMaximoPermitido=$porcentajeMaximoPermitido)"
    }
}