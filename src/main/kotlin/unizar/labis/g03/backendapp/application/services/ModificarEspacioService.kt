package unizar.labis.g03.backendapp.application.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unizar.labis.g03.backendapp.domain.model.DTO.EspacioDTO
import unizar.labis.g03.backendapp.domain.model.entities.Espacio
import unizar.labis.g03.backendapp.domain.model.entities.Persona
import unizar.labis.g03.backendapp.domain.model.valueObjects.Horario
import unizar.labis.g03.backendapp.domain.model.valueObjects.TipoEntidadAsignableEspacio
import unizar.labis.g03.backendapp.domain.model.valueObjects.TipoEspacio
import unizar.labis.g03.backendapp.domain.repositories.EspacioRepository
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository
import unizar.labis.g03.backendapp.domain.repositories.ReservaRepository
import java.util.*
import javax.swing.text.html.Option

@Service
class ModificarEspacioService @Autowired constructor(private val espacioRepository: EspacioRepository,
                                                     private val reservaRepository: ReservaRepository,
                                                     private val personaRepository: PersonaRepository
    ) {
    @Transactional
    fun modificarEspacio(espacioDTO : EspacioDTO, emailPersona: String): Optional<Espacio> {
        val espacio: Optional<Espacio?> = espacioRepository.findById(espacioDTO.getIdEspacio())
        val persona: Optional<Persona> = personaRepository.findByEmail(emailPersona)

        if (espacio.isPresent && persona.isPresent && persona.get().esGerente()) {
            val espacioModificado = espacio.get()
            espacioModificado.setReservable(espacioDTO.getReservable())
            espacioModificado.setCategoriaReserva(getCategoria(espacioDTO.getCategoria()))
            espacioModificado.setHorario(getHorario(espacioDTO.getHoraInicio(), espacioDTO.getHoraFinal()))
            espacioModificado.setPorcentajeUsoMaximo(espacioDTO.getPorcentajeMaximoPermitido())
            when(espacioDTO.getEntidadAsignada()){
                TipoEntidadAsignableEspacio.EINA -> espacioModificado.asignarEina()
                TipoEntidadAsignableEspacio.DEPARTAMENTO -> espacioModificado.asignarDepartamento(espacioDTO.getDepartamentoAsignado()!!)
                TipoEntidadAsignableEspacio.PERSONAS -> {
                    if (espacioDTO.getPersonasAsignadas() != null){
                        val personasAsignadas : List<Persona> = espacioDTO.getPersonasAsignadas()!!
                            .mapNotNull { email ->
                                if (personaRepository.findByEmail(email).isEmpty) null
                                else personaRepository.findByEmail(email).get()
                            }
                        espacioModificado.asignarPersonas(personasAsignadas)
                    }
                }
            }

            espacioRepository.save(espacioModificado)
            return Optional.of(espacioModificado)
        }
        return Optional.empty()

    }

    fun getHorario(horaInicio: Int?, horaFinal:Int? ): Horario? {
        if (horaInicio != null && horaFinal != null) return Horario(horaInicio, horaFinal)
        else return null
    }
    fun getCategoria(categoria : String): TipoEspacio {
        return TipoEspacio.valueOf(categoria)
    }
    fun comprobarReservas(espacio: Espacio) {
        //val reservas = reservaRepository.findByEspacio(espacio);
        // TODO: comprobar si hay reservas en el horario
    }}
