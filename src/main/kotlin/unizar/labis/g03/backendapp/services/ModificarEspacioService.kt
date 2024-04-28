package unizar.labis.g03.backendapp.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unizar.labis.g03.backendapp.model.DTO.EspacioDTO
import unizar.labis.g03.backendapp.model.entities.Espacio
import unizar.labis.g03.backendapp.model.entities.Persona
import unizar.labis.g03.backendapp.model.valueObjects.Horario
import unizar.labis.g03.backendapp.model.valueObjects.TipoEspacio
import unizar.labis.g03.backendapp.repositories.EspacioRepository
import unizar.labis.g03.backendapp.repositories.PersonaRepository
import unizar.labis.g03.backendapp.repositories.ReservaRepository
import java.util.*
import javax.swing.text.html.Option

@Service
class ModificarEspacioService @Autowired constructor(private val espacioRepository: EspacioRepository,
                                                     private val reservaRepository: ReservaRepository,
                                                     private val personaRepository: PersonaRepository
    ) {
    @Transactional
    fun modificarEspacio(espacioDTO :EspacioDTO, emailPersona: String): Optional<Espacio> {
        val espacio: Optional<Espacio?> = espacioRepository.findById(espacioDTO.getIdEspacio())
        val persona: Optional<Persona> = personaRepository.findByEmail(emailPersona)

        if (espacio.isPresent && persona.isPresent && persona.get().esGerente()) {
            val espacioModificado = espacio.get()
            espacioModificado.setReservable(espacioDTO.getReservable())
            espacioModificado.setCategoriaReserva(getCategoria(espacioDTO.getCategoria()))
            espacioModificado.setHorario(getHorario(espacioDTO.getHoraInicio(), espacioDTO.getHoraFinal()))
            espacioModificado.setPorcentajeUsoMaximo(espacioDTO.getPorcentajeMaximoPermitido())
            espacioRepository.save(espacioModificado)
            return Optional.of(espacioModificado)
        }
        return Optional.empty()

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
