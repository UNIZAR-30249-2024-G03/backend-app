package unizar.labis.g03.backendapp.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unizar.labis.g03.backendapp.domain.model.entities.Persona;
import unizar.labis.g03.backendapp.domain.model.valueObjects.Departamento;
import unizar.labis.g03.backendapp.domain.model.valueObjects.Rol;
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository;

import java.util.Set;

@Service
public class ModificarPersonaService {
    private final PersonaRepository personaRepository;
    @Autowired
    public ModificarPersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public void modificarPersona(String email, Set<Rol> roles, Departamento departamento) {
        Persona persona = personaRepository.findByEmail(email);
        persona.setDepartamento(departamento);
        persona.setRoles(roles);
        personaRepository.save(persona);
    }

}