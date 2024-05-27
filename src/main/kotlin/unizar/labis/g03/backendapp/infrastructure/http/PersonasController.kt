package unizar.labis.g03.backendapp.infrastructure.http

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unizar.labis.g03.backendapp.application.exceptions.UsuarioNoEncontradoException
import unizar.labis.g03.backendapp.application.services.BorrarNotificacionesService
import unizar.labis.g03.backendapp.application.services.BuscarPersonaService
import unizar.labis.g03.backendapp.application.services.ModificarPersonaService
import unizar.labis.g03.backendapp.infrastructure.http.types.PersonaOut
import unizar.labis.g03.backendapp.domain.model.entities.Persona
import unizar.labis.g03.backendapp.domain.model.valueObjects.Departamento
import unizar.labis.g03.backendapp.domain.model.valueObjects.Rol

@CrossOrigin(origins = ["*"])
@RestController
class PersonasController(
    val buscarPersonaService: BuscarPersonaService,
    val modificarPersonaService: ModificarPersonaService,
    val borrarNotificacionesService: BorrarNotificacionesService
){
    @Operation(
        summary = "Permite obtener la informacion de un usuario",
        description = "Permite obtener la informacion de un usuari con identificador 'id'.")
    @GetMapping("/personas")
    fun obtenerPersona(@RequestParam(required = false) @Parameter(name = "email", description = "Email de la persona", example = "gerente@gmail.com", required = true) email : String = "") : Persona {
        val persona = buscarPersonaService.buscarPersona(email)

        if (persona.isPresent) return persona.get();
        else throw UsuarioNoEncontradoException("No existe el usuario con email: $email")
    }

    @Operation(
        summary = "Permite eliminar las notificaciones de un usuario",
        description = "Permite eliminar las notificaciones de un usuario con identificador 'id'.")
    @PutMapping("/eliminarNotificaciones")
    fun borrarNotificaciones(@RequestParam(required = false) @Parameter(name = "email", description = "Email de la persona", example = "gerente@gmail.com", required = true) email : String = "") {
        borrarNotificacionesService.borrarNotificaciones(email)
    }

    @Operation(
        summary = "Permite cambiar el rol o roles o el departamento a un usuario",
        description = "Permite cambiar el rol o roles o el departamento del usuario con identificador 'id'.")
    @PutMapping("/personas/{id}")
    fun updatePersona(@PathVariable id :String,
                      @RequestParam(required = false) @Parameter(name = "rol", description = "Nuevo rol o roles de la persona que se desea actualizar", allowEmptyValue = false , example = "Estudiante") rol : List<Rol>,
                      @RequestParam(required = false) @Parameter(name = "departamento", description = "Nuevo departamento de la persona que se desea actualizar", example = "Informatica_e_Ingenieria_de_sistemas") departamento : Departamento?
    ) {
        modificarPersonaService.modificarPersona(id, rol.toSet(), departamento)
    }
}