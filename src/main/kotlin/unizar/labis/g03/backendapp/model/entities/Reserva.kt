package unizar.labis.g03.backendapp.model.entities

import jakarta.persistence.*
import unizar.labis.g03.backendapp.model.valueObjects.InfoReserva

@Entity
class Reserva(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int?,
    @ManyToOne(fetch = FetchType.EAGER)
    val persona: Persona,
    @OneToMany(fetch = FetchType.EAGER)
    var espacios: MutableList<Espacio> = ArrayList(),
    @Embedded
    val infoReserva: InfoReserva,
    val anulado: Boolean = false){


    /*
    constructor(persona: Persona, espacios: MutableList<Espacio>, infoReserva: InfoReserva)
            : this("", persona, espacios, infoReserva)


     */
    override fun toString(): String {
        return "Reserva(id=$id, persona=$persona, espacios=$espacios, infoReserva=$infoReserva, anulado=$anulado)"
    }

}