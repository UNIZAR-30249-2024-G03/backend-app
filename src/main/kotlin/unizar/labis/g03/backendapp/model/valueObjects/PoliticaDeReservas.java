package unizar.labis.g03.backendapp.model.valueObjects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unizar.labis.g03.backendapp.model.entities.Espacio;
import unizar.labis.g03.backendapp.model.entities.Persona;
import unizar.labis.g03.backendapp.model.entities.Reserva;
import unizar.labis.g03.backendapp.model.valueObjects.InfoReserva;
import unizar.labis.g03.backendapp.repositories.ReservaRepository;

import java.util.List;


public class PoliticaDeReservas {

    public boolean comprobarPoliticaDeReservas(Reserva reserva){
        Persona persona = reserva.getPersona();
        List<Espacio> espacios = reserva.getEspacios();
        InfoReserva infoReserva = reserva.getInfoReserva();

        return true;
    }





}
