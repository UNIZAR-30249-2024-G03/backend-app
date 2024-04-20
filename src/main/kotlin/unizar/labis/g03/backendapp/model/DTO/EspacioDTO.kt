package unizar.labis.g03.backendapp.model.DTO

import lombok.Getter

class EspacioDTO(
    private val idEspacio: String,
    private val reservable: Boolean,
    private val categoria: String,
    private val horaInicio: Int,
    private val horaFinal: Int,
    private val porcentajeMaximoPermitido: Int

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
    fun getHoraInicio(): Int{
        return horaInicio
    }
    fun getHoraFinal(): Int{
        return horaFinal
    }
    fun getPorcentajeMaximoPermitido(): Int{
        return porcentajeMaximoPermitido
    }
}
