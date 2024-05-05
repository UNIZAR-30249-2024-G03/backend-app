package unizar.labis.g03.backendapp.domain.model.valueObjects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unizar.labis.g03.backendapp.domain.model.entities.Espacio;
import unizar.labis.g03.backendapp.domain.model.entities.Persona;
import unizar.labis.g03.backendapp.domain.model.entities.Reserva;
import unizar.labis.g03.backendapp.domain.model.entities.Espacio;
import unizar.labis.g03.backendapp.domain.model.entities.Persona;
import unizar.labis.g03.backendapp.domain.model.entities.Reserva;
import unizar.labis.g03.backendapp.domain.model.entities.Espacio;
import unizar.labis.g03.backendapp.domain.model.entities.Persona;
import unizar.labis.g03.backendapp.domain.model.entities.Reserva;
import unizar.labis.g03.backendapp.domain.model.valueObjects.InfoReserva;
import unizar.labis.g03.backendapp.domain.repositories.ReservaRepository;

import java.util.List;


public class PoliticaDeReservas {

    public boolean comprobarPoliticaDeReservas(Reserva reserva){
        Persona persona = reserva.getPersona();
        List<Espacio> espacios = reserva.getEspacios();
        InfoReserva infoReserva = reserva.getInfoReserva();

        return true;
    }





}
