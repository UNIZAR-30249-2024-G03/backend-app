package unizar.labis.g03.backendapp.infrastructure.http

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import unizar.labis.g03.backendapp.infrastructure.http.types.PersonaOut
import unizar.labis.g03.backendapp.model.entities.Persona
import unizar.labis.g03.backendapp.model.valueObjects.Departamento
import unizar.labis.g03.backendapp.model.valueObjects.Rol

@RestController
class PersonasController {
    @Operation(
        summary = "Permite cambiar el rol o roles o el departamento a un usuario",
        description = "Permite cambiar el rol o roles o el departamento del usuario con identificador 'id'.")
    @PutMapping("/personas/{id}")
    fun updatePersona(@PathVariable id : Int,
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