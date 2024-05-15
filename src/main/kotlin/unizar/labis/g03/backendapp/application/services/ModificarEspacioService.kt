package unizar.labis.g03.backendapp.application.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unizar.labis.g03.backendapp.application.exceptions.EspacioNoEncontradoException
import unizar.labis.g03.backendapp.application.exceptions.UsuarioNoEncontradoException
import unizar.labis.g03.backendapp.application.exceptions.UsuarioSinPermisosException
import unizar.labis.g03.backendapp.domain.model.DTO.EspacioDTO
import unizar.labis.g03.backendapp.domain.model.entities.Espacio
import unizar.labis.g03.backendapp.domain.model.entities.Persona
import unizar.labis.g03.backendapp.domain.model.valueObjects.EntidadAsignableEspacio
import unizar.labis.g03.backendapp.domain.model.valueObjects.Horario
import unizar.labis.g03.backendapp.domain.model.valueObjects.TipoEspacio
import unizar.labis.g03.backendapp.domain.repositories.EspacioRepository
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository
import unizar.labis.g03.backendapp.domain.services.ActualizarReservas
import java.util.*

@Service
class ModificarEspacioService @Autowired constructor(private val espacioRepository: EspacioRepository,
                                                     private val personaRepository: PersonaRepository,
                                                     private val actualizarReservas: ActualizarReservas
    ) {
    @Transactional
    fun modificarEspacio(espacioDTO : EspacioDTO, emailPersona: String): Optional<Espacio> {
        val espacioOriginal: Optional<Espacio?> = espacioRepository.findById(espacioDTO.getIdEspacio())
        val persona: Optional<Persona> = personaRepository.findByEmail(emailPersona)

        if (espacioOriginal.isEmpty) throw EspacioNoEncontradoException("No existe el espacio con el identificador: ${espacioDTO.getIdEspacio()}")
        if (persona.isEmpty) throw UsuarioNoEncontradoException("No existe la persona con el email: $emailPersona")
        if (!persona.get().esGerente()) throw UsuarioSinPermisosException("El usuario con email: $emailPersona no tiene permisos para realizar esta accion")

        // Si se es una peticion valida por una persona autorizada
        val espacioModificado = espacioOriginal.get()
        espacioModificado.setReservable(espacioDTO.getReservable())
        espacioModificado.setCategoriaReserva(getCategoria(espacioDTO.getCategoria()))
        espacioModificado.setHorario(getHorario(espacioDTO.getHoraInicio(), espacioDTO.getHoraFinal()))
        espacioModificado.setPorcentajeUsoMaximo(espacioDTO.getPorcentajeMaximoPermitido())

        var personasAsignadas : List<Persona>? = null
        if(espacioDTO.getPersonasAsignadas() != null){
           personasAsignadas = personaRepository.findByEmailIn(espacioDTO.getPersonasAsignadas()!!)
        }
        espacioModificado.setEntidadAsignada(EntidadAsignableEspacio(espacioDTO.getEntidadAsignada(),
            espacioDTO.getDepartamentoAsignado(), personasAsignadas))
        espacioRepository.save(espacioModificado)
        actualizarReservas.actualizarReservas(espacioOriginal.get(), espacioModificado)
        return Optional.of(espacioModificado)


    }

    fun getHorario(horaInicio: Int?, horaFinal:Int? ): Horario? {
        return if (horaInicio != null && horaFinal != null) Horario(horaInicio, horaFinal)
        else null
    }
    fun getCategoria(categoria : String): TipoEspacio {
        return TipoEspacio.valueOf(categoria)
    }
}
