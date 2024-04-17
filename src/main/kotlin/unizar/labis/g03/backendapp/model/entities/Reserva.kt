package unizar.labis.g03.backendapp.model.entities

import jakarta.persistence.*
import unizar.labis.g03.backendapp.model.valueObjects.InfoReserva

@Entity
class Reserva(
    @Id
    val id: String,
    @ManyToOne
    val persona: Persona,
    @OneToMany
    var espacios: MutableList<Espacio> = ArrayList(),
    @Embedded
    val infoReserva: InfoReserva,
    val anulado: Boolean = false){

}