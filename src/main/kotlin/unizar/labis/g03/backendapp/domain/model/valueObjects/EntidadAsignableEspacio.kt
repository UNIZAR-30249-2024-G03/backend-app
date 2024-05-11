package unizar.labis.g03.backendapp.domain.model.valueObjects

import jakarta.persistence.*
import unizar.labis.g03.backendapp.domain.model.entities.Persona
import java.util.Optional

enum class TipoEntidadAsignableEspacio{
    EINA, DEPARTAMENTO, PERSONAS
}

@Embeddable
class EntidadAsignableEspacio(
    @Enumerated(EnumType.STRING)
    private val tipoEntidad: TipoEntidadAsignableEspacio,
    @Enumerated(EnumType.STRING)
    private val departamento : Departamento? ,
    @OneToMany(fetch = FetchType.EAGER)
    private val personas : List<Persona>?
){
    constructor(tipoEntidad: TipoEntidadAsignableEspacio) : this(tipoEntidad, null, null)
    constructor(tipoEntidad: TipoEntidadAsignableEspacio, departamento: Departamento?) : this(tipoEntidad, departamento, null) {
        require(tipoEntidad != TipoEntidadAsignableEspacio.DEPARTAMENTO || departamento != null) {
            "El departamento no puede ser nulo cuando el tipo de entidad es DEPARTAMENTO"
        }
    }
    constructor(tipoEntidad: TipoEntidadAsignableEspacio, personas: List<Persona>?) : this(tipoEntidad, null, personas) {
        require(tipoEntidad == TipoEntidadAsignableEspacio.PERSONAS) {
            "Las personas solo pueden ser proporcionadas si el tipo de entidad es PERSONAS"
        }
    }

    fun getTipo(): TipoEntidadAsignableEspacio {
        return tipoEntidad
    }

    fun getDepartamento(): Optional<Departamento>{
        return Optional.ofNullable(departamento)
    }

    fun getPersonas(): Optional<List<Persona>>{
        return Optional.ofNullable(personas)
    }



    override fun toString(): String {
        return when (tipoEntidad) {
            TipoEntidadAsignableEspacio.EINA -> "EINA"
            TipoEntidadAsignableEspacio.DEPARTAMENTO -> "Departamento: ".plus(departamento.toString())
            TipoEntidadAsignableEspacio.PERSONAS -> "Personas: ".plus(personas.toString())
        }
    }
}