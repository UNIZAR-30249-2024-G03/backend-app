package unizar.labis.g03.backendapp.model.DTO

import lombok.Getter
import unizar.labis.g03.backendapp.model.entities.Persona
import unizar.labis.g03.backendapp.model.valueObjects.Departamento
import unizar.labis.g03.backendapp.model.valueObjects.EntidadAsignableEspacio
import unizar.labis.g03.backendapp.model.valueObjects.TipoEntidadAsignableEspacio

class EspacioDTO(
    private val idEspacio: String,
    private val reservable: Boolean,
    private val categoria: String,
    private val horaInicio: Int?,
    private val horaFinal: Int?,
    private val porcentajeMaximoPermitido: Int?,
    private val entidadAsignada: TipoEntidadAsignableEspacio,
    private val departamentoAsignado: Departamento? = null,
    private val personasAsignadas: List<String>? = null,
){
    fun getIdEspacio(): String{
        return idEspacio
    }
    fun getReservable(): Boolean{
        return reservable
    }
    fun getCategoria(): String{
        return categoria
    }
    fun getHoraInicio(): Int?{
        return horaInicio
    }
    fun getHoraFinal(): Int?{
        return horaFinal
    }
    fun getPorcentajeMaximoPermitido(): Int?{
        return porcentajeMaximoPermitido
    }

    fun getEntidadAsignada(): TipoEntidadAsignableEspacio{
        return entidadAsignada
    }

    fun getDepartamentoAsignado(): Departamento?{
        return departamentoAsignado
    }

    fun getPersonasAsignadas(): List<String>?{
        return personasAsignadas
    }
}
