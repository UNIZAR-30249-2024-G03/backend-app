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

        //Persona autorizada a reserva este espacio
        return true;
    }





}
