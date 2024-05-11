package unizar.labis.g03.backendapp.application.services

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.domain.model.DTO.ReservaDTO
import unizar.labis.g03.backendapp.domain.model.entities.Reserva
import unizar.labis.g03.backendapp.domain.model.valueObjects.InfoReserva
import unizar.labis.g03.backendapp.domain.repositories.EspacioRepository
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository
import unizar.labis.g03.backendapp.domain.repositories.ReservaRepository
import unizar.labis.g03.backendapp.domain.model.valueObjects.CalculoOcupantesMaximos
import unizar.labis.g03.backendapp.domain.services.ComprobarValidezReservas
import java.util.*

@Service
class ReservarEspacioService @Autowired constructor(
    private val reservaRepository: ReservaRepository,
    private val espacioRepository: EspacioRepository,
    private val personaRepository: PersonaRepository,
    private val comprobarValidezReservas: ComprobarValidezReservas,
) {
    companion object {
        private val ESPACIO_NO_DISPONIBLE = "El espacio ya esta reservado a la hora seleccionada"
    }
    @Transactional
    fun reservarEspacios(reservaDTO: ReservaDTO): Optional<Reserva> {
        val reserva = buildReserva(reservaDTO)
        val reservasConflictivas: MutableList<Reserva> = mutableListOf()
        val conflictos = comprobarValidezReservas.comprobarReserva(reserva)
        if (conflictos != "") {
            throw Exception(conflictos)
        }
        for (espacio in reserva.espacios) {
            reservasConflictivas.addAll(reservaRepository.encontrarReservasConflictivas(
                espacio.getId(), reserva.infoReserva.fechaInicio, reserva.infoReserva.fechaFinal)) //Espacio no esta reservado
            if (reservasConflictivas.isNotEmpty() && !reserva.persona.esGerente()) {
               throw Exception(ESPACIO_NO_DISPONIBLE)
            }

        }
        //Borramos las reservas conflictivas si existen.
        if(reservasConflictivas.isNotEmpty()){
            anularReservasConflictivas(reservasConflictivas)
        }
        reservaRepository.save(reserva)
        return Optional.of(reserva)
    }

    private fun buildReserva(reservaDTO: ReservaDTO): Reserva {
        val espacios = espacioRepository.findAllById(reservaDTO.getIdEspacios())
        val persona = personaRepository.findByEmail(reservaDTO.getEmailUsuario()).get()
        val infoReserva = InfoReserva(
            reservaDTO.getFechaInicio(), reservaDTO.getFechaFinal(), reservaDTO.getDescripcion(),
            CalculoOcupantesMaximos().calculo(espacios),
            reservaDTO.getNumAsistentesPrevistos()
        )
        return Reserva(null, persona, espacios, infoReserva)
    }

    private fun anularReservasConflictivas(reservasConflictivas: List<Reserva>) {
        val idsReservas: List<Int?> = reservasConflictivas.map { it.id }
        reservaRepository.anularReservas(idsReservas)
    }

}
