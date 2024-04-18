package unizar.labis.g03.backendapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unizar.labis.g03.backendapp.model.DTO.ReservaDTO;
import unizar.labis.g03.backendapp.model.entities.Espacio;
import unizar.labis.g03.backendapp.model.entities.Persona;
import unizar.labis.g03.backendapp.model.entities.Reserva;
import unizar.labis.g03.backendapp.model.valueObjects.InfoReserva;
import unizar.labis.g03.backendapp.repositories.EspacioRepository;
import unizar.labis.g03.backendapp.repositories.PersonaRepository;
import unizar.labis.g03.backendapp.repositories.ReservaRepository;

import java.util.List;

@Service
public class ReservarEspacioService {
    private final ReservaRepository reservaRepository;
    private final EspacioRepository espacioRepository;
    private final PersonaRepository personaRepository;

    @Autowired
    public ReservarEspacioService(ReservaRepository reservaRepository, EspacioRepository espacioRepository, PersonaRepository personaRepository) {
        this.reservaRepository = reservaRepository;
        this.espacioRepository = espacioRepository;
        this.personaRepository = personaRepository;
    }

    //  Date fechaInicio, Date fechaFinal, int numAsistentesPrevistos, String descripcion
    public void reservarEspacios(Reserva reserva, String idPersona, List<String> idEspacios) {
        Persona persona = reserva.getPersona();
        List<Espacio> espacios = reserva.getEspacios();
        InfoReserva infoReserva = reserva.getInfoReserva();
        for (Espacio espacio : espacios) {
            if (!espacio.getReservable()) {
                //Espacio no reservable
            }
            if (espacio.getHorario().estaDentro(infoReserva.getHoraInicio(), infoReserva.getHoraFinal())) {
                // Horario no disponible
            }
            List<Reserva> reservasConflictivas = reservaRepository.encontrarReservasConflictivas
                    (espacio.getId(), infoReserva.getFechaInicio(), infoReserva.getFechaFinal());//Espacio no esta reservado
            if (!reservasConflictivas.isEmpty()) {
                //Si no es gerente
                if (persona.esGerente()) {
                    borrarReservasConflictivas(reservasConflictivas);
                    //save?
                }
            }
        }


        //Construimos el objeto reserva a partir del DTO recibido

        //Comprobamos que la reserva cumple con la politica de reservas

        //Guardamos la reserva en la base de datos
        reservaRepository.save(reserva);
    }

    private Reserva buildReserva(ReservaDTO reservaDTO) {
        List<Espacio> espacios = espacioRepository.findAllById(reservaDTO.getIdEspacios());
        Persona persona = personaRepository.findByEmail(reservaDTO.getEmailUsuario());
        InfoReserva infoReserva = reservaDTO.getInfoReserva();
        return new Reserva(persona, espacios, infoReserva);
    }

    private void borrarReservasConflictivas( List<Reserva> reservasConflictivas) {
        for (Reserva r : reservasConflictivas) {
            reservaRepository.anularReserva(r.getId());
        }
        //Espacio.opEspacio.maximosOcupantesValido()

    }
}
