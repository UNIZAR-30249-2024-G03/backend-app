package unizar.labis.g03.backendapp.domain.model.valueObjects

import unizar.labis.g03.backendapp.domain.model.entities.Espacio
import unizar.labis.g03.backendapp.domain.model.entities.Persona

class PoliticaDeReservas {
    val politicasAccesoEINA = mapOf(
        Rol.Estudiante to setOf(TipoEspacio.SALA_COMUN),
        Rol.Investigador_contratado to setOf(TipoEspacio.AULA,TipoEspacio.SEMINARIO,TipoEspacio.LABORATORIO,TipoEspacio.SALA_COMUN),
        Rol.Docente_investigador to setOf(TipoEspacio.AULA,TipoEspacio.SEMINARIO,TipoEspacio.LABORATORIO,TipoEspacio.SALA_COMUN),
        Rol.tecnico_laboratorio to setOf(TipoEspacio.SEMINARIO,TipoEspacio.LABORATORIO,TipoEspacio.SALA_COMUN),
        Rol.Gerente to setOf(TipoEspacio.AULA,TipoEspacio.SEMINARIO,TipoEspacio.LABORATORIO,TipoEspacio.SALA_COMUN,TipoEspacio.DESPACHO),
    )

    val politicasAccesoDiferenteDPTO =  mapOf(
        Rol.Estudiante to setOf(TipoEspacio.SALA_COMUN),
        Rol.Investigador_contratado to setOf(TipoEspacio.AULA,TipoEspacio.SEMINARIO,TipoEspacio.SALA_COMUN),
        Rol.Docente_investigador to setOf(TipoEspacio.AULA,TipoEspacio.SEMINARIO,TipoEspacio.SALA_COMUN),
        Rol.tecnico_laboratorio to setOf(TipoEspacio.SEMINARIO,TipoEspacio.SALA_COMUN),
        Rol.Gerente to setOf(TipoEspacio.AULA,TipoEspacio.SEMINARIO,TipoEspacio.LABORATORIO,TipoEspacio.SALA_COMUN)
    )
    val politicaAccesoMismoDPTO = mapOf(
        Rol.Estudiante to setOf(TipoEspacio.SALA_COMUN),
        Rol.Investigador_contratado to setOf(TipoEspacio.AULA,TipoEspacio.SEMINARIO,TipoEspacio.SALA_COMUN,TipoEspacio.DESPACHO, TipoEspacio.LABORATORIO),
        Rol.Docente_investigador to setOf(TipoEspacio.AULA,TipoEspacio.SEMINARIO,TipoEspacio.SALA_COMUN,TipoEspacio.DESPACHO),
        Rol.tecnico_laboratorio to setOf(TipoEspacio.SEMINARIO,TipoEspacio.SALA_COMUN),
        Rol.Gerente to setOf(TipoEspacio.AULA,TipoEspacio.SEMINARIO,TipoEspacio.LABORATORIO,TipoEspacio.SALA_COMUN)
    )
    // Función para verificar si un usuario tiene acceso a un espacio
    fun tieneAcceso(persona: Persona,espacio: Espacio): Boolean {
        print(persona)
        print(" quiere reservar ")
        println(espacio)
        val rolPersona = if(persona.esGerente()) Rol.Gerente else persona.getRoles().first()
        val mismoDepartamento = persona.getDepartamento()?.equals(espacio.getEntidadAsignada()?.getDepartamento()?.get())
        val tipoEntidad = espacio.getEntidadAsignada()?.getTipo()
        return when {
            tipoEntidad ==  TipoEntidadAsignableEspacio.EINA -> politicasAccesoEINA[rolPersona]?.contains(espacio.getCategoriaReserva())
            mismoDepartamento == true -> politicaAccesoMismoDPTO[rolPersona]?.contains(espacio.getCategoriaReserva())
            else -> politicasAccesoDiferenteDPTO[rolPersona]?.contains(espacio.getCategoriaReserva())
        } ?: false
    }
}
