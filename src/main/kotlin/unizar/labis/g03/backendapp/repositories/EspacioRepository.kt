package unizar.labis.g03.backendapp.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import unizar.labis.g03.backendapp.model.entities.Espacio
import unizar.labis.g03.backendapp.model.valueObjects.TipoEspacio

interface EspacioRepository : JpaRepository<Espacio?, String?> {
    @Query(
        "SELECT e FROM Espacio e WHERE (:id is null OR e.id = :id) " +
                "AND (:categoria is null OR e.categoriaReserva = :categoriaReserva) " +
                "AND (:ocupantesMaximos is null OR e.numMaxOcupantes = :numMaxOcupantes) " +
                "AND (:planta is null OR e.planta = :planta)"
    )
    fun buscarEspacios(
        @Param("id") id: String?,
        @Param("categoriaReserva") categoriaReserva: TipoEspacio?,
        @Param("numMaxOcupantes") numMaxOcupantes: Int?,
        @Param("planta") planta: Int?
    ): List<Espacio>?
}
