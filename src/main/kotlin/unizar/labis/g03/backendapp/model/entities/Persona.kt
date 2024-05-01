package unizar.labis.g03.backendapp.model.entities

import jakarta.persistence.*
import unizar.labis.g03.backendapp.model.valueObjects.Rol
import unizar.labis.g03.backendapp.model.valueObjects.Departamento

@Entity
@Table(name = "personas")
class Persona (
    private var nombre: String,
    private var apellido: String,
    @Id
    private var email: String,
    @Enumerated(EnumType.STRING)
    private var roles: MutableSet<Rol> = mutableSetOf(), // =
    @Enumerated(EnumType.STRING)
    private var departamentoAdscrito: Departamento? = null ) {

    fun getRoles(): Set<Rol>{
        return roles
    }
    fun getnombre(): String{
        return nombre
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
    fun esGerente(): Boolean{
        return roles.contains(Rol.Gerente)
    }

    override fun toString(): String {
        return "Persona(nombre='$nombre', apellido='$apellido', email='$email', roles=$roles, departamentoAdscrito=$departamentoAdscrito)"
    }


}