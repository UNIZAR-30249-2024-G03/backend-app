package unizar.labis.g03.backendapp.domain.services

import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.domain.model.entities.Espacio

@Service
class CalculoOcupantesMaximos {
    fun maximosOcupantesValido(espacios: List<Espacio>): Int {
        var suma = 0
        for (espacio in espacios) {
            suma += espacio.getCapacidadMaxima()
        }
        return suma
    }
}
