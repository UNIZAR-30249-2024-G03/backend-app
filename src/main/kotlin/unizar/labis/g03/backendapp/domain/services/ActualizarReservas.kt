package unizar.labis.g03.backendapp.domain.services

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.domain.model.entities.Espacio
import unizar.labis.g03.backendapp.domain.repositories.ReservaRepository
import java.time.LocalDateTime

@Service
class ActualizarReservas {

    private lateinit var comprobarValidezReservas: ComprobarValidezReservas

    @Autowired
    private lateinit var reservaRepository: ReservaRepository
    @Transactional
    fun actualizarReservas(espacioOriginal: Espacio,espacioModificado: Espacio){
        val reservas = reservaRepository.encontrarReservasEspacio(espacioOriginal.getId(), LocalDateTime.now())
        //Si no hay reservas no se hace nada
        if(reservas.isEmpty()){
            return
        }
        val diferenciaDeCapacidad = espacioOriginal.calculaDiferenciaDeCapacidad(espacioModificado.getPorcentajeUsoMaximo())
        if(diferenciaDeCapacidad != 0){
            for(reserva in reservas){
                reserva.infoReserva.cambiarMaximaCapacidad(diferenciaDeCapacidad)
            }
            //Guardamos las reservas modificadas
            reservaRepository.saveAll(reservas)
        }
        val reservasInvalidas = comprobarValidezReservas.comprobarReservasdelEspacio(reservas,espacioModificado)
        reservaRepository.anularReservas(reservasInvalidas.map { it.id })
    }


}