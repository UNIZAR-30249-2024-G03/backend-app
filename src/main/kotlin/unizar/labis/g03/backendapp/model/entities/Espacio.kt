package unizar.labis.g03.backendapp.model.entities

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import lombok.Setter
import jakarta.persistence.Id
import lombok.Getter
import unizar.labis.g03.backendapp.model.valueObjects.Horario
import unizar.labis.g03.backendapp.model.valueObjects.TipoEspacio


@Entity
@Setter
@Getter
class Espacio (
    @Id
    private val id: String,
    private val tamano: Float,
    private val tipoEspacio: TipoEspacio,
    private var categoriaReserva: TipoEspacio,
    private val numMaxOcupantes: Int,
    private val planta: Int,
    private var reservable: Boolean,
    @Embedded
    private var horario: Horario,
    private var porcentajeUsoMaximo: Int
)

{
    fun getId(): String{
        return id
    }
    fun getReservable(): Boolean{
        return reservable
    }

    fun getTamano(): Float {
        return tamano
    }

    fun setHorario(horario: Horario){
        this.horario = horario
    }
    fun getHorario(): Horario{
        return horario
    }
    fun setPorcentajeUsoMaximo (nuevoPorcentaje: Int){
        porcentajeUsoMaximo = nuevoPorcentaje
    }
    fun getPorcentajeUsoMaximo(): Int{
        return porcentajeUsoMaximo
    }
    fun getCapacidadMaxima(): Int {
        return ((numMaxOcupantes.toDouble() / porcentajeUsoMaximo) * 100).toInt()
    }

    fun setReservable(reservable: Boolean) {
        this.reservable = reservable
    }

    fun setCategoriaReserva(categoriaReserva: TipoEspacio) {
        this.categoriaReserva = categoriaReserva
    }
    fun getCategoriaReserva(): TipoEspacio {
        return categoriaReserva
    }

    override fun toString(): String {
        return "Espacio(id='$id', tamano=$tamano, tipoEspacio=$tipoEspacio, categoriaReserva=$categoriaReserva, numMaxOcupantes=$numMaxOcupantes, planta=$planta, reservable=$reservable, horario=$horario, porcentajeUsoMaximo=$porcentajeUsoMaximo)"
    }

}