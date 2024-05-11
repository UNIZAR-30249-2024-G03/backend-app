package unizar.labis.g03.backendapp.domain.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.domain.model.entities.Espacio
import unizar.labis.g03.backendapp.domain.model.entities.Reserva
import unizar.labis.g03.backendapp.domain.model.valueObjects.PoliticaDeReservas
import unizar.labis.g03.backendapp.domain.repositories.ReservaRepository
import java.time.LocalDateTime

@Service
class ComprobarValidezReservas {
    companion object {
        private val FUERA_DE_HORARIO = "El espacio no está disponible en el horario seleccionado"
        private val ESPACIO_NO_RESERVABLE = "El espacio no es reservable"
        private val NO_TIENE_ACCESO = "No tiene acceso a reservar este espacio"
        private val CAPACIDAD_INSUFICIENTE = "Los espacios no tiene capacidad suficiente para albergar a todos los ocupantes"
    }

    @Autowired
    private lateinit var reservaRepository: ReservaRepository
    fun comprobarReservasdelEspacio(reservas : List<Reserva>,espacio: Espacio) :  MutableList<Reserva> {
        val reservasInvalidas : MutableList<Reserva> = mutableListOf()

        for(reserva in reservas){
            if(!reserva.infoReserva.capacidadValida() || comprobarReservaDelEspacio(espacio, reserva).equals("")){
                reservasInvalidas.add(reserva)
            }
        }
        return reservasInvalidas
    }

    fun comprobarReserva(reserva: Reserva): String{
        val conflictos = ""
        for(espacio in reserva.espacios){
            conflictos.plus(comprobarReservaDelEspacio(espacio, reserva))
        }
        if(reserva.infoReserva.capacidadValida()){
            conflictos.plus(CAPACIDAD_INSUFICIENTE)
        }
        return conflictos
    }

    // TODO: ESTO NO FUNCIONA
    fun comprobarReservaDelEspacio(espacio: Espacio, reserva: Reserva): String {
        var conflictos = espacio.getId() + "["
        if (!espacio.getReservable()) {
            conflictos += ESPACIO_NO_RESERVABLE + "\n"
        }
        if (!espacio.getHorario().estaDentro(reserva.infoReserva.getHoraInicio(), reserva.infoReserva.getHoraFinal())) {
            conflictos += FUERA_DE_HORARIO + "\n"
        }
        if (!PoliticaDeReservas().tieneAcceso(reserva.persona, espacio)) {
            conflictos += NO_TIENE_ACCESO + "\n"
        }
        if(conflictos.equals(espacio.getId() + "[" )){
            conflictos = ""
        }
        else{
            conflictos += "]"
        }
        return conflictos
    }


}
