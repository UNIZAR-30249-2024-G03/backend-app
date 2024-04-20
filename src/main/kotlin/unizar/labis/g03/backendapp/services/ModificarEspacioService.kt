package unizar.labis.g03.backendapp.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unizar.labis.g03.backendapp.model.DTO.EspacioDTO
import unizar.labis.g03.backendapp.model.entities.Espacio
import unizar.labis.g03.backendapp.model.valueObjects.Horario
import unizar.labis.g03.backendapp.model.valueObjects.TipoEspacio
import unizar.labis.g03.backendapp.repositories.EspacioRepository
import unizar.labis.g03.backendapp.repositories.ReservaRepository
import java.util.*

@Service
class ModificarEspacioService @Autowired constructor(private val espacioRepository: EspacioRepository,
                                                     private val reservaRepository: ReservaRepository) {
    @Transactional
    fun modificarEspacio(espacioDTO :EspacioDTO): Espacio? {
        val espacio: Optional<Espacio?> = espacioRepository.findById(espacioDTO.getIdEspacio())

        if (espacio.isPresent) {
            val espacioModificado = espacio.get()
            espacioModificado.setReservable(espacioDTO.getReservable())
            espacioModificado.setCategoriaReserva(getCategoria(espacioDTO.getCategoria()))
            espacioModificado.setHorario(getHorario(espacioDTO.getHoraInicio(), espacioDTO.getHoraFinal()))
            espacioRepository.save(espacioModificado)
        }
        return null

    }

    fun getHorario(horaInicio: Int, horaFinal:Int ): Horario {
        return Horario(horaInicio, horaFinal)
    }
    fun getCategoria(categoria : String): TipoEspacio {
        return TipoEspacio.valueOf(categoria)
    }
    fun comprobarReservas(espacio: Espacio) {
        //val reservas = reservaRepository.findByEspacio(espacio);
        // TODO: comprobar si hay reservas en el horario
    }}
