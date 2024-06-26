package unizar.labis.g03.backendapp.application.services

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.application.exceptions.EspacioYaReservadoException
import unizar.labis.g03.backendapp.application.exceptions.ReservaNoValidaException
import unizar.labis.g03.backendapp.application.exceptions.UsuarioNoEncontradoException
import unizar.labis.g03.backendapp.domain.model.DTO.ReservaDTO
import unizar.labis.g03.backendapp.domain.model.entities.Reserva
import unizar.labis.g03.backendapp.domain.model.valueObjects.InfoReserva
import unizar.labis.g03.backendapp.domain.repositories.EspacioRepository
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository
import unizar.labis.g03.backendapp.domain.repositories.ReservaRepository
import unizar.labis.g03.backendapp.domain.model.valueObjects.CalculoOcupantesMaximos
import unizar.labis.g03.backendapp.domain.services.ComprobarValidezReservas
import unizar.labis.g03.backendapp.domain.services.NotificarPersonas
import java.util.*

@Service
class ReservarEspacioService @Autowired constructor(
    private val reservaRepository: ReservaRepository,
    private val espacioRepository: EspacioRepository,
    private val personaRepository: PersonaRepository,
    private val comprobarValidezReservas: ComprobarValidezReservas,
    private val notificarPersonas: NotificarPersonas

) {
    companion object {
        private val ESPACIO_NO_DISPONIBLE = "El espacio ya esta reservado a la hora seleccionada"
    }
    @Transactional
    fun reservarEspacios(reservaDTO: ReservaDTO): Optional<Reserva> {
        val reserva = buildReserva(reservaDTO)
        val reservasConflictivas: MutableList<Reserva> = mutableListOf()
        val conflictos = comprobarValidezReservas.comprobar(reserva)
        if (conflictos != "") {
            throw ReservaNoValidaException(conflictos)
        }
        for (espacio in reserva.espacios) {
            reservasConflictivas.addAll(reservaRepository.encontrarReservasConflictivas(
                espacio.getId(), reserva.infoReserva.fechaInicio, reserva.infoReserva.fechaFinal)) //Espacio no esta reservado
            if (reservasConflictivas.isNotEmpty() && !reserva.persona.esGerente()) {
               throw EspacioYaReservadoException(ESPACIO_NO_DISPONIBLE)
            }
        }
        //Borramos las reservas conflictivas si existen.
        if(reservasConflictivas.isNotEmpty()){
            print("Reservas conflictivas: " + reservasConflictivas.get(0).toString())
            anularReservasConflictivas(reservasConflictivas)
        }
        reservaRepository.save(reserva)
        return Optional.of(reserva)
    }

    private fun buildReserva(reservaDTO: ReservaDTO): Reserva {
        val espacios = espacioRepository.findAllById(reservaDTO.getIdEspacios())
        val persona = personaRepository.findByEmail(reservaDTO.getEmailUsuario())
        if (persona.isEmpty) throw UsuarioNoEncontradoException("No existe usuario de la reserva")
        val infoReserva = InfoReserva(
            reservaDTO.getFechaInicio(), reservaDTO.getFechaFinal(), reservaDTO.getDescripcion(),
            CalculoOcupantesMaximos().calculo(espacios),
            reservaDTO.getNumAsistentesPrevistos(), reservaDTO.getTipoUso()
        )
        return Reserva(null, persona.get(), espacios, infoReserva)
    }

    private fun anularReservasConflictivas(reservasConflictivas: List<Reserva>) {
        val idsReservas: List<Int?> = reservasConflictivas.map { it.id }
        reservasConflictivas.forEach { notificarPersonas.notificaAnulacion(it) }
        reservaRepository.anularReservas(idsReservas)
    }

}
