package unizar.labis.g03.backendapp.infrastructure.http

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unizar.labis.g03.backendapp.infrastructure.http.types.EspacioOut
import unizar.labis.g03.backendapp.model.DTO.EspacioDTO
import unizar.labis.g03.backendapp.model.entities.Espacio
import unizar.labis.g03.backendapp.model.valueObjects.TipoEspacio
import unizar.labis.g03.backendapp.services.BuscarEspacioService
import unizar.labis.g03.backendapp.services.ModificarEspacioService
import java.util.*

@RestController
class EspaciosController(
    private val buscarEspacioService: BuscarEspacioService,
    private val modificarEspacioService: ModificarEspacioService
){
    @Operation(
        summary = "Obtiene todos los espacios del sistema con posibilidad de añadir filtros por identificador, planta, máximo de ocupantes y categoría",
        description = "Obtiene una lista con todos los espacios del sistema con la posibilidad de añadir distintos filtros.")
    @GetMapping(value = ["/espacios"], produces = ["application/json"])
    fun getEspacios(
        @Parameter(name = "id", description = "Identificador del espacio que se desea buscar", example = "72") @RequestParam(required = false) id : String?,
        @Parameter(name = "planta", description = "Planta en la que se encuentra el espacio que se desea buscar", example = "2") @RequestParam(required = false) planta: Int?,
        @Parameter(name = "numMaxOcupantes", description = "Número de ocupantes máximos que tiene el espacio que se desea buscar", example = "150") @RequestParam(required = false) numMaxOcupantes : Int?,
        @Parameter(name = "categoriaReserva", description = "Indica la categoría de reserva del espacio que se desea buscar", example = "AULA") @RequestParam(required = false) categoriaReserva: TipoEspacio?
    ): List<Espacio> {
        return buscarEspacioService.buscarEspacio(id, categoriaReserva, numMaxOcupantes, planta)
    }

    @Operation(
        summary = "Permite a un usuario con rol de gerente modificar los siguientes atributos de un espacio: reservable, categoría de reserva de espacio" +
                "horario de reserva disponible, porcentaje de uso máximo permitido.",
        description = "Permite modificar los atributos de un espacio si el usuario con identificador 'idUsuario' tiene rol de gerente")
    @PutMapping("/espacios/{id}")
    fun updateEspacio(@Parameter(name = "idEspacio", description = "Identificador del espacio a modificar", example = "des-1") @RequestParam(required = true) @PathVariable idEspacio : String,
                      @Parameter(name = "idUsuario", description = "Identificador del usuario que desea modificar los datos del espacio", example = "795593") @RequestParam(required = true) idUsuario : String,
                      @Parameter(name = "reservable", description = "Indica si el espacio que se desea añadir al sistema se puede reservar o no", example = "true") @RequestParam(required = true) reservable: Boolean,
                      @Parameter(name = "categoriaReserva", description = "Indica la categoría de reserva del espacio que se desea añadir al sistema en caso de que sea reservable", example = "Aula") @RequestParam(required = true) categoriaReserva: TipoEspacio,
                      @Parameter(name = "horarioInicioReservaDisponible", description = "Indica la hora de inicio disponible de reserva que tiene el espacio que se desea añadir al sistema en caso de que sea reservable", example = "10") @RequestParam(required = true) horarioInicioReservaDisponible: Int,
                      @Parameter(name = "horarioFinalReservaDisponible", description = "Indica la hora de finalización disponible de reserva que tiene el espacio que se desea añadir al sistema en caso de que sea reservable", example = "20") @RequestParam(required = true) horarioFinalReservaDisponible: Int,
                      @Parameter(name = "porcentajeUsoMaximo", description = "Indica el porcentaje máximo permitido del espacio que se desea añadir al sistema", example = "50") @RequestParam(required = true) porcentajeUsoMaximo: Int): ResponseEntity<Espacio> {
        val espacio = modificarEspacioService.modificarEspacio(EspacioDTO(idEspacio, reservable, categoriaReserva.toString(), horarioInicioReservaDisponible, horarioFinalReservaDisponible, porcentajeUsoMaximo),idUsuario)

        return if (espacio.isPresent) ResponseEntity.ok().body(espacio.get())
        else ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
    }

}