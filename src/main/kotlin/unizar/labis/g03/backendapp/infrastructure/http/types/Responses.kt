package unizar.labis.g03.backendapp.infrastructure.http.types

import unizar.labis.g03.backendapp.domain.model.valueObjects.Departamento
import unizar.labis.g03.backendapp.domain.model.valueObjects.InfoReserva
import unizar.labis.g03.backendapp.domain.model.valueObjects.Rol
import unizar.labis.g03.backendapp.domain.model.valueObjects.TipoEspacio


class EspacioOut(
    val id: String? = null,
    val tamano: Float? = null,
    val tipoEspacio: TipoEspacio? = null,
    val categoriaReserva: TipoEspacio? = null,
    val numMaxOcupantes: Int? = null,
    val planta: Int? = null,
    val reservable: Boolean? = null,
    val porcentajeUsoMaximo: Int? = null
) {
    override fun toString(): String {
        return "EspacioOut(id=$id, tamano=$tamano, tipoEspacio=$tipoEspacio, categoriaReserva=$categoriaReserva, numMaxOcupantes=$numMaxOcupantes, planta=$planta, reservable=$reservable, porcentajeUsoMaximo=$porcentajeUsoMaximo)"
    }
}

class PersonaOut(
    val nombre: String? = null,
    val apellido: String? = null,
    val email: String? = null,
    val roles: Set<Rol>? = null,
    val departamentoAdscrito: Departamento? = null
) {
    override fun toString(): String {
        return "PersonaOut(nombre=$nombre, apellido=$apellido, email=$email, roles=$roles, departamentoAdscrito=$departamentoAdscrito)"
    }
}

class ReservaOut(
    val id: String? = null,
    val persona: PersonaOut? = null,
    val espacio: EspacioOut? = null,
    val infoReserva: InfoReserva? = null
) {
    override fun toString(): String {
        return "ReservaOut(id=$id, persona=$persona, espacio=$espacio, infoReserva=$infoReserva)"
    }
}
