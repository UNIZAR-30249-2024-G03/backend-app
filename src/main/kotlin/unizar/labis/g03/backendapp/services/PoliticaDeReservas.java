package unizar.labis.g03.backendapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unizar.labis.g03.backendapp.model.entities.Espacio;
import unizar.labis.g03.backendapp.model.entities.Persona;
import unizar.labis.g03.backendapp.model.entities.Reserva;
import unizar.labis.g03.backendapp.model.valueObjects.InfoReserva;
import unizar.labis.g03.backendapp.repositories.ReservaRepository;

import java.util.List;

@Service
public class PoliticaDeReservas {

    private final ReservaRepository reservaRepository;


    @Autowired
    public PoliticaDeReservas(ReservaRepository reservaRepository) {

        this.reservaRepository = reservaRepository;

    }
    public boolean comprobarPoliticaDeReservas(Reserva reserva){
        Persona persona = reserva.getPersona();
        List<Espacio> espacios = reserva.getEspacios();
        InfoReserva infoReserva = reserva.getInfoReserva();
        for(Espacio espacio : espacios){
            if(!espacio.getReservable()){
                return false;
            }
            if (espacio.getHorario().estaDentro(infoReserva.getHoraInicio(), infoReserva.getHoraFinal())) {
                return false;
            }
            List<Reserva> reservasConflictivas =  reservaRepository.encontrarReservasConflictivas
                    (espacio.getId(), infoReserva.getFechaInicio(), infoReserva.getFechaFinal());//Espacio no esta reservado
            if (!reservasConflictivas.isEmpty()) {
                //Si no es gerente
                if (persona.esGerente()) {
                    borrarReservasConflictivas(reservasConflictivas);
                    return true;
                }
            }
        }
        //Persona autorizada a reserva este espacio
        return true;
    }

    private void borrarReservasConflictivas(List<Reserva> reservasConflictivas){
        for(Reserva r : reservasConflictivas){
            reservaRepository.anularReserva(r.getId());
        }
    }



}