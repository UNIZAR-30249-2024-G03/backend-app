package unizar.labis.g03.backendapp.model.entities

import jakarta.persistence.Entity
import unizar.labis.g03.backendapp.model.valueObjects.InfoReserva

@Entity
class Reserva(
    val persona: Persona,
    val espacio: Espacio,
    val infoReserva: InfoReserva) {

}