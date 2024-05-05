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
    private val departamento : Departamento? = null,
    @OneToMany(fetch = FetchType.EAGER)
    private val personas : List<Persona>? = null
){
    constructor() : this(TipoEntidadAsignableEspacio.EINA)

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