package unizar.labis.g03.backendapp.infrastructure.http

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unizar.labis.g03.backendapp.infrastructure.http.types.EspacioOut
import unizar.labis.g03.backendapp.infrastructure.http.types.PersonaOut
import unizar.labis.g03.backendapp.infrastructure.http.types.ReservaOut
import unizar.labis.g03.backendapp.model.entities.Reserva
import unizar.labis.g03.backendapp.model.valueObjects.*
import unizar.labis.g03.backendapp.services.AnularReservaService
import unizar.labis.g03.backendapp.services.ConsultarReservasService
import java.util.*
import kotlin.collections.HashSet

@RestController
class ReservasController(
    private val consultarReservasService: ConsultarReservasService,
    private val anularReservaService: AnularReservaService){
    @Operation(
        summary = "Permite a un usuario con rol de gerente obtener todas las reservas vivas del sistema",
        description = "Obtiene una lista con todas las Reservas vivas del sistema asociadas si el usuario con id 'idUsuario' tiene rol de gerente .")
    @GetMapping("/reservas")
    fun getReservasVivas(@Parameter(name = "idUsuario", description = "Identificador del usuario que desea obtener los espacios", example = "example@gmail.com") @RequestParam(required = true) idUsuario : String): List<Reserva>? {
        return consultarReservasService.consultarReservas(idUsuario)
    }


    @Operation(
        summary = "Permite que un usuario realice una reserva",
        description = "Permite que el usuario con identificador 'idUsuario' realice una reserva.")
    @PostMapping("/reservas")
    fun addReserva(@Parameter(name = "idUsuario", description = "Identificador del usuario que se desea realizar la reserva", example = "795593") @RequestParam(required = true) idUsuario : Int,
                   @Parameter(name = "idsEspacios", description = "Espacio o espacios que el usuario desea reservar", example = "espacio1, espacio2, yokse") @RequestParam(required = true) idsEspacios : List<String>,
                   @Parameter(name = "tipoUsoReserva", description = "El tipo de uso de reserva que va a tener la reserva que se va a añadir al sistema", example = "Docencia") @RequestParam(required = true) tipoUsoReserva : TipoUsoReserva,
                   @Parameter(name = "numMaxOcupantes", description = "Número de ocupantes máximos que va a tener la reserva que se va a añadir al sistema", example = "8") @RequestParam(required = true) numMaxOcupantes : Int,
                   @Parameter(name = "fechaInicio", description = "Fecha y hora de inicio a la que dará comienzo la reserva que se va a añadir al sistema", example = "2000-10-31T01:30:00.000-05:00") @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) fechaInicio: Date,
                   @Parameter(name = "fechaFinal", description = "Fecha y hora de inicio a la que dará comienzo la reserva que se va a añadir al sistema", example = "2000-10-31T01:30:00.000-05:00") @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) fechaFinal: Date,
                   @Parameter(name = "descripcion", description = "Descripción de la reserva que se va a añadir al sistema", example = "Reserva hecha por el grupo 03") @RequestParam(required = false) descripcion: String) : ReservaOut {

        val reserva = ReservaOut(
            "idReserva",
            PersonaOut("Paco", "Paquito", "paco@gmail.com", HashSet<Rol>(),Departamento.Informatica_e_Ingenieria_de_sistemas),
            EspacioOut("idEspacio", 10f, TipoEspacio.AULA, TipoEspacio.AULA, 5, 1, true, 100),
            InfoReserva()
        )

        return reserva;
    }

    @Operation(
        summary = "Permite a un usuario con rol de gerente eliminar una reserva del sistema",
        description = "Permite eliminar del sistema la reserva con identificador 'id' si el usuario con identificador 'idUsuario' tiene el rol de gerente.")
    @DeleteMapping("/reservas/{id}")
    fun eliminarReserva(@Parameter(name = "idReserva", description = "Identificador de la reserva a modificar", example = "reserva-1") @RequestParam(required = true) @PathVariable idReserva : Int,
                        @Parameter(name = "idUsuario", description = "Identificador del usuario que desea eliminar la reserva", example = "795593") @RequestParam(required = true) idUsuario : String,): ResponseEntity<String> {
        val eliminada = anularReservaService.anularReserva(idUsuario, idReserva)
        return if (eliminada) ResponseEntity.ok("Reserva \"$idReserva\" eliminada con exito")
        else ResponseEntity.notFound().build()
    }
}