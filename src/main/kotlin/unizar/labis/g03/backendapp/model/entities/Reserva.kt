package unizar.labis.g03.backendapp.model.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import unizar.labis.g03.backendapp.model.valueObjects.InfoReserva

@Entity
class Reserva(
    @Id
    val id: String,
    @ManyToOne
    val persona: Persona,
    @ManyToOne
    val espacio: Espacio,
    val infoReserva: InfoReserva) {

}