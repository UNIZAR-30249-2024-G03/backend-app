package unizar.labis.g03.backendapp.application.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.domain.model.DTO.ReservaDTO
import unizar.labis.g03.backendapp.domain.model.entities.Espacio
import unizar.labis.g03.backendapp.domain.model.entities.Reserva
import unizar.labis.g03.backendapp.domain.model.valueObjects.InfoReserva
import unizar.labis.g03.backendapp.domain.repositories.EspacioRepository
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository
import unizar.labis.g03.backendapp.domain.repositories.ReservaRepository
import java.util.Optional

@Service
class ReservarEspacioService @Autowired constructor(
    private val reservaRepository: ReservaRepository,
    private val espacioRepository: EspacioRepository,
    private val personaRepository: PersonaRepository
) {
    //  Date fechaInicio, Date fechaFinal, int numAsistentesPrevistos, String descripcion
    fun reservarEspacios(reservaDTO: ReservaDTO): Optional<Reserva> {
        val reserva = buildReserva(reservaDTO)
        val infoReserva = reserva.infoReserva
        val gerente = reserva.persona.esGerente()
        val reservasConflictivas: MutableList<Reserva> = mutableListOf()

        for (espacio in reserva.espacios) {
            if (!espacio.getReservable()  &&
                !espacio.getHorario().estaDentro(infoReserva.getHoraInicio(), infoReserva.getHoraFinal())) {
                //Return no  se puede hacer la reserva.
            }
            reservasConflictivas.addAll(reservaRepository.encontrarReservasConflictivas(
                espacio.getId(), infoReserva.fechaInicio, infoReserva.fechaFinal)) //Espacio no esta reservado
            if (reservasConflictivas.isEmpty() && !gerente) {
               //Return no se puede hacer la reserva.
            }
        }
        //Comprobamos que la reserva cumple con la politica de reservas
        //Guardamos la reserva en la base de datos
        reservaRepository.save(reserva)
        return Optional.of(reserva)
    }

    private fun buildReserva(reservaDTO: ReservaDTO): Reserva {
        val espacios = espacioRepository.findAllById(reservaDTO.getIdEspacios())
        val persona = personaRepository.findByEmail(reservaDTO.getEmailUsuario()).get()
        val infoReserva = InfoReserva(
            reservaDTO.getNumAsistentesPrevistos(), reservaDTO.getFechaInicio(),
            reservaDTO.getFechaFinal(), reservaDTO.getDescripcion(), maximosOcupantesValido(espacios)
        )
        return Reserva(null, persona, espacios, infoReserva)
    }

    private fun borrarReservasConflictivas(reservasConflictivas: List<Reserva>) {
        for (r in reservasConflictivas) {
            reservaRepository.anularReserva(r.id)
        }

        //Espacio.opEspacio.maximosOcupantesValido()
    }

    private fun maximosOcupantesValido(espacios: List<Espacio?>): Int {
        var suma = 0
        for (espacio in espacios) {
            suma += espacio!!.getCapacidadMaxima()
        }
        return suma
    }
}
