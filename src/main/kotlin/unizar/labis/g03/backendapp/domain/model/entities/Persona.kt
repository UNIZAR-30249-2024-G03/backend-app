package unizar.labis.g03.backendapp.domain.model.entities

import jakarta.persistence.*
import unizar.labis.g03.backendapp.domain.model.valueObjects.Rol
import unizar.labis.g03.backendapp.domain.model.valueObjects.Departamento

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
    private var departamentoAdscrito: Departamento? = null,
    @ElementCollection // Necesario para mapear una lista de Strings en JPA
    @Column(nullable = true)
    private var notificaciones: MutableList<String> = mutableListOf()){

    fun getRoles(): Set<Rol>{
        return roles
    }

    fun borrarNotificaciones(){
        notificaciones.clear()
    }
    fun getEmail(): String{
        return email
    }

    fun setRoles(nuevosRoles : Set<Rol>){
        // TODO: pensar si deberiamos controlar que roles se pueden poner porque ahora puedes poner la combinacion que te de la gana
        roles.clear()
        roles.addAll(nuevosRoles)
    }

    fun getDepartamento(): Departamento?{
        return departamentoAdscrito
    }

    fun setDepartamento(nuevoDepartamento: Departamento?){
        departamentoAdscrito = nuevoDepartamento
    }
    fun esGerente(): Boolean{
        return roles.contains(Rol.Gerente)
    }

    override fun toString(): String {
        return "Persona(nombre='$nombre', apellido='$apellido', email='$email', roles=$roles, departamentoAdscrito=$departamentoAdscrito)"
    }


}