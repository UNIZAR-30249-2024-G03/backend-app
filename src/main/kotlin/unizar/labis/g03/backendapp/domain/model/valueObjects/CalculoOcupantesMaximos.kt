package unizar.labis.g03.backendapp.domain.model.valueObjects

import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.domain.model.entities.Espacio

class CalculoOcupantesMaximos {
    fun calculo(espacios: List<Espacio>): Int {
        var suma = 0
        for (espacio in espacios) {
            suma += espacio.getCapacidadMaxima()
        }
        return suma
    }
}
