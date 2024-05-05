package unizar.labis.g03.backendapp.domain.services;

import unizar.labis.g03.backendapp.domain.model.entities.Espacio;

import java.util.List;

public class CalculoOcupantesMaximos {

    public Integer  maximosOcupantesValido( List<Espacio> espacios){
        int suma = 0;
        for (Espacio espacio : espacios) {
            suma += espacio.getCapacidadMaxima();
        }
        return suma;
    }
}
