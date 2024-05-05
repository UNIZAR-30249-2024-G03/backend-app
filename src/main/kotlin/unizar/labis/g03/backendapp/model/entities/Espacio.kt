package unizar.labis.g03.backendapp.model.entities

import jakarta.persistence.*
import lombok.Setter
import lombok.Getter
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import unizar.labis.g03.backendapp.model.valueObjects.Horario
import unizar.labis.g03.backendapp.model.valueObjects.PorcentajeUsoDefecto
import unizar.labis.g03.backendapp.model.valueObjects.TipoEspacio


@Entity
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
class Espacio (
    @Id
    private val id: String,
    @Column(nullable = true)
    private val tamano: Float,
    @Enumerated(EnumType.STRING)
    private val tipoEspacio: TipoEspacio,
    @Enumerated(EnumType.STRING)
    private var categoriaReserva: TipoEspacio,
    @Column(nullable = true)
    private val numMaxOcupantes: Int,
    @Column(nullable = true)
    private val planta: Int,
    @Column(nullable = true)
    private var reservable: Boolean,
    @Embedded
    @Column(nullable = true)
    private var horario: Horario?,
    @Column(nullable = true)
    private var porcentajeUsoMaximo: Int?
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
        if (horario == null){
            return Horario.horarioDefecto()
        }
        return horario as Horario
    }

    fun setPorcentajeUsoMaximo (nuevoPorcentaje: Int){
        porcentajeUsoMaximo = nuevoPorcentaje
    }
    fun getPorcentajeUsoMaximo(): Int{
        return porcentajeUsoMaximo ?: PorcentajeUsoDefecto.getPorcentaje()
    }
    fun getCapacidadMaxima(): Int {
        val porcentajeReal = porcentajeUsoMaximo ?: PorcentajeUsoDefecto.getPorcentaje()
        return ((numMaxOcupantes.toDouble() / porcentajeReal) * 100).toInt()
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