package unizar.labis.g03.backendapp.infrastructure.http

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.*
import unizar.labis.g03.backendapp.infrastructure.http.types.EspacioOut
import unizar.labis.g03.backendapp.model.entities.Espacio
import unizar.labis.g03.backendapp.model.valueObjects.TipoEspacio
import java.util.ArrayList

@RestController
class EspaciosController {
    @Operation(
        summary = "Obtiene todos los espacios del sistema con posibilidad de añadir filtros por identificador, planta, máximo de ocupantes y categoría",
        description = "Obtiene una lista con todos los espacios del sistema con la posibilidad de añadir distintos filtros.")
    @GetMapping(value = ["/espacios"], produces = ["application/json"])
    fun getEspacios(
        @Parameter(name = "id", description = "Identificador del espacio que se desea buscar", example = "72") @RequestParam(required = false) id : Int?,
        @Parameter(name = "planta", description = "Planta en la que se encuentra el espacio que se desea buscar", example = "2") @RequestParam(required = false) planta: Int?,
        @Parameter(name = "numMaxOcupantes", description = "Número de ocupantes máximos que tiene el espacio que se desea buscar", example = "150") @RequestParam(required = false) numMaxOcupantes : Int?,
        @Parameter(name = "categoriaReserva", description = "Indica la categoría de reserva del espacio que se desea buscar", example = "Aula") @RequestParam(required = false) categoriaReserva: TipoEspacio
    ): List<EspacioOut> {
        val espacio = EspacioOut("1", 25f, TipoEspacio.Despacho, TipoEspacio.Sala_comun, 2, 1, true , 10);
        val conjuntoEspacios : MutableList<EspacioOut> = ArrayList<EspacioOut>();
        conjuntoEspacios.add(espacio);

        return conjuntoEspacios;
    }

    @Operation(
        summary = "Permite a un usuario con rol de gerente modificar los siguientes atributos de un espacio: reservable, categoría de reserva de espacio" +
                "horario de reserva disponible, porcentaje de uso máximo permitido.",
        description = "Permite modificar los atributos de un espacio si el usuario con identificador 'idUsuario' tiene rol de gerente")
    @PutMapping("/espacios/{id}")
    fun updateEspacio(@Parameter(name = "idEspacio", description = "Identificador del espacio a modificar", example = "des-1") @RequestParam(required = true) @PathVariable idEspacio : String,
                      @Parameter(name = "idUsuario", description = "Identificador del usuario que desea modificar los datos del espacio", example = "795593") @RequestParam(required = true) idUsuario : Int,
                      @Parameter(name = "reservable", description = "Indica si el espacio que se desea añadir al sistema se puede reservar o no", example = "true") @RequestParam(required = true) reservable: Boolean,
                      @Parameter(name = "categoriaReserva", description = "Indica la categoría de reserva del espacio que se desea añadir al sistema en caso de que sea reservable", example = "Aula") @RequestParam(required = false) categoriaReserva: TipoEspacio,
                      @Parameter(name = "horarioReservaDisponible", description = "Indica el horario disponible de reserva que tiene el espacio que se desea añadir al sistema en caso de que sea reservable", example = "09:00-14:00") @RequestParam(required = false) horarioReservaDisponible: String,
                      @Parameter(name = "porcentajeUsoMaximo", description = "Indica el porcentaje máximo permitido del espacio que se desea añadir al sistema", example = "50.0") @RequestParam(required = false) porcentajeUsoMaximo: Double): EspacioOut {
        val espacio = EspacioOut("1", 25f, TipoEspacio.Despacho, TipoEspacio.Sala_comun, 2, 1, true , 10);
        println(idEspacio)

        return espacio;
    }

}