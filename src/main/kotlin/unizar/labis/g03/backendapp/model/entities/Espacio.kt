package unizar.labis.g03.backendapp.model.entities

import lombok.Getter
import lombok.Setter
import unizar.labis.g03.backendapp.model.valueObjects.TipoEspacio


class Espacio (
    private val tamano: Float,
    private val tipoEspacio: TipoEspacio,
    private var categoriaReserva: TipoEspacio,
    private val numMaxOcupantes: Int,
    private val planta: Int,
    private var reservable: Boolean,
    private var porcentajeUsoMaximo: Int
){
    fun getReservable(): Boolean{
        return reservable
    }

    fun setReservable(nuevoReservable: Boolean){
        reservable = nuevoReservable
    }

    fun getCategoriaReserva(): TipoEspacio{
        return categoriaReserva
    }

    fun setCategoriaReserva(nuevaCategoria: TipoEspacio){
        categoriaReserva = nuevaCategoria
    }

    fun getPorcentajeUsoMaximo (): Int{
        return porcentajeUsoMaximo
    }

    fun setPorcentajeUsoMaximo (nuevoPorcentaje: Int){
        porcentajeUsoMaximo = nuevoPorcentaje
    }

}