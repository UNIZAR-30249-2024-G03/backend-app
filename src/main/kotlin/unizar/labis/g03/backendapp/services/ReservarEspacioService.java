package unizar.labis.g03.backendapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unizar.labis.g03.backendapp.model.entities.Reserva;
import unizar.labis.g03.backendapp.repositories.EspacioRepository;
import unizar.labis.g03.backendapp.repositories.PersonaRepository;
import unizar.labis.g03.backendapp.repositories.ReservaRepository;

import java.util.List;

@Service
public class ReservarEspacioService {
    private final ReservaRepository reservaRepository;
    private  final EspacioRepository espacioRepository;
    private final PersonaRepository personaRepository;
    @Autowired
    public ReservarEspacioService(ReservaRepository reservaRepository, EspacioRepository espacioRepository, PersonaRepository personaRepository) {
        this.reservaRepository = reservaRepository;
        this.espacioRepository = espacioRepository;
        this.personaRepository = personaRepository;
    }

    public void reservarEspacios(Reserva reserva, String idPersona, List<String> idEspacios) {
        //Construimos el objeto reserva a partir del DTO recibido


        //Comprobamos que la reserva cumple con la politica de reservas

        //Guardamos la reserva en la base de datos
        reservaRepository.save(reserva);
    }
}
