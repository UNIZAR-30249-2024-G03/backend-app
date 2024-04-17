package unizar.labis.g03.backendapp.model.valueObjects

import java.io.Serializable

class Horario{

    companion object {
        const val HORA_APERTURA_DEFAULT = 8
        const val HORA_CIERRE_DEFAULT = 21
    }
    val horaApertura: Int
    val horaCierre: Int

    constructor(horaApertura: Int, horaCierre: Int) {
        this.horaApertura = horaApertura
        this.horaCierre = horaCierre
    }
    // Constructor por defecto
    constructor() : this(HORA_APERTURA_DEFAULT,HORA_CIERRE_DEFAULT)
    fun estaDentro(horaInicio: Int,horaFin: Int  ): Boolean  {
        return horaInicio in horaApertura..horaCierre && horaFin in horaApertura..horaCierre
    }
    fun horaValida(horaInicio: Int,horaFin: Int): Boolean {
        return horaInicio < horaFin;
    }
}