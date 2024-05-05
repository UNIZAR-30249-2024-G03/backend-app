package unizar.labis.g03.backendapp.application.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.domain.model.entities.Espacio
import unizar.labis.g03.backendapp.domain.model.valueObjects.TipoEspacio
import unizar.labis.g03.backendapp.domain.repositories.EspacioRepository

@Service
class BuscarEspacioService @Autowired constructor(private val espacioRepository: EspacioRepository) {
    fun buscarEspacio(id: String?, categoria: TipoEspacio?, ocupantesMaximos: Int?, planta: Int?): List<Espacio> {
        return espacioRepository.buscarEspacios(id, categoria, ocupantesMaximos, planta) ?: emptyList()
    }
}
