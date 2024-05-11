package unizar.labis.g03.backendapp.infrastructure.http

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.*
import unizar.labis.g03.backendapp.application.exceptions.UsuarioNoEncontradoException
import unizar.labis.g03.backendapp.application.services.BuscarPersonaService
import unizar.labis.g03.backendapp.infrastructure.http.types.PersonaOut
import unizar.labis.g03.backendapp.domain.model.entities.Persona
import unizar.labis.g03.backendapp.domain.model.valueObjects.Departamento
import unizar.labis.g03.backendapp.domain.model.valueObjects.Rol

@RestController
class PersonasController(
    val buscarPersonaService: BuscarPersonaService
){
    @Operation(
        summary = "Permite obtener la informacion de un usuario",
        description = "Permite obtener la informacion de un usuari con identificador 'id'.")
    @GetMapping("/personas/{id}")
    fun obtenerPersona(@PathVariable @Parameter(name = "id", description = "Email de la persona", example = "gerente@gmail.com") id : String) : Persona {
        val persona = buscarPersonaService.buscarPersona(id)

        if (persona.isPresent) return persona.get();
        else throw UsuarioNoEncontradoException("No existe el usuario con email: $id")
    }

    @Operation(
        summary = "Permite cambiar el rol o roles o el departamento a un usuario",
        description = "Permite cambiar el rol o roles o el departamento del usuario con identificador 'id'.")
    @PutMapping("/personas/{id}")
    fun updatePersona(@PathVariable id :String,
                      @RequestParam(required = false) @Parameter(name = "rol", description = "Nuevo rol o roles de la persona que se desea actualizar", example = "Estudiante") rol : List<Rol>,
                      @RequestParam(required = false) @Parameter(name = "departamento", description = "Nuevo departamento de la persona que se desea actualizar", example = "Informatica_e_Ingenieria_de_sistemas") departamento : Departamento
    ) : PersonaOut {
        val rol : Rol = Rol.Docente_investigador;
        val conjuntoRoles : MutableSet<Rol> = HashSet<Rol>();
        conjuntoRoles.add(rol);
        val persona = PersonaOut("Adrian", "Arribas", "795593@gmail.com", conjuntoRoles, Departamento.Informatica_e_Ingenieria_de_sistemas);

        return persona;
    }
}