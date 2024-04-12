package unizar.labis.g03.backendapp.model.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import unizar.labis.g03.backendapp.model.valueObjects.Rol
import unizar.labis.g03.backendapp.model.valueObjects.Departamento

@Entity
class Persona (
    private var nombre: String,
    private var apellido: String,
    @Id
    private var email: String,
    private val roles: MutableSet<Rol>,
    private var departamentoAdscrito: Departamento ) {

    fun getRoles(): Set<Rol>{
        return roles
    }

    fun setRoles(nuevosRoles : Set<Rol>){
        roles.clear()
        roles.addAll(nuevosRoles)
    }

    fun getDepartamento(): Departamento?{
        return departamentoAdscrito
    }

    fun setDepartamento(nuevoDepartamento: Departamento){
        departamentoAdscrito = nuevoDepartamento
    }
}