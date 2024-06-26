package unizar.labis.g03.backendapp.domain.model.entities

import jakarta.persistence.*
import lombok.Setter
import lombok.Getter
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import unizar.labis.g03.backendapp.domain.model.valueObjects.*


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
    private var porcentajeUsoMaximo: Int?,
    @Embedded
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private var entidadAsignada: EntidadAsignableEspacio?,
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

    fun setHorario(horario: Horario?){
        this.horario = horario
    }
    fun getHorario(): Horario {
        if (horario == null){
            return Horario.horarioDefecto()
        }
        return horario as Horario
    }

    fun setPorcentajeUsoMaximo (nuevoPorcentaje: Int?){
        porcentajeUsoMaximo = nuevoPorcentaje
    }
    fun getPorcentajeUsoMaximo(): Int{
        return porcentajeUsoMaximo ?: PorcentajeUsoDefecto.getPorcentaje()
    }
    fun getCapacidadMaxima(): Int {
        val porcentajeReal = porcentajeUsoMaximo ?: PorcentajeUsoDefecto.getPorcentaje()
        return (numMaxOcupantes * (porcentajeReal.toDouble() / 100)).toInt()
    }

    fun setReservable(reservable: Boolean) {
        this.reservable = reservable
    }

    fun calculaDiferenciaDeCapacidad(porcentajeNuevo: Int?): Int {
        if(porcentajeNuevo == 0 || porcentajeNuevo == porcentajeUsoMaximo || porcentajeNuevo == null  ) return 0
        return  ((numMaxOcupantes.toDouble() / porcentajeNuevo) * 100).toInt() - getCapacidadMaxima()
    }

    fun setCategoriaReserva(categoriaReserva: TipoEspacio) {
        this.categoriaReserva = categoriaReserva
    }
    fun getCategoriaReserva(): TipoEspacio {
        return categoriaReserva
    }

    fun getEntidadAsignada(): EntidadAsignableEspacio?{
        return entidadAsignada
    }

    fun setEntidadAsignada(entidadAsignada: EntidadAsignableEspacio){
        this.entidadAsignada = entidadAsignada
    }

    override fun toString(): String {
        return "Espacio(id='$id', tamano=$tamano, tipoEspacio=$tipoEspacio, categoriaReserva=$categoriaReserva, numMaxOcupantes=$numMaxOcupantes, planta=$planta, reservable=$reservable, horario=$horario, porcentajeUsoMaximo=$porcentajeUsoMaximo, entidadAsignada=$entidadAsignada)"
    }

}