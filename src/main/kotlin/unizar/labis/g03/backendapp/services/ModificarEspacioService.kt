package unizar.labis.g03.backendapp.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unizar.labis.g03.backendapp.model.entities.Espacio
import unizar.labis.g03.backendapp.model.entities.Reserva
import unizar.labis.g03.backendapp.model.valueObjects.Horario
import unizar.labis.g03.backendapp.model.valueObjects.TipoEspacio
import unizar.labis.g03.backendapp.repositories.EspacioRepository
import unizar.labis.g03.backendapp.repositories.ReservaRepository

@Service
class ModificarEspacioService @Autowired constructor(private val espacioRepository: EspacioRepository,
                                                     private val reservaRepository: ReservaRepository) {
    @Transactional
    fun modificarEspacio(
        idEspacio: String,
        reservable: Boolean,
        categoriaReserva: TipoEspacio?,
        horaApertura: Int,
        horaCierre: Int,
        porcentajeUsoMaximo: Int
    ): Espacio? {
        val optionalEspacio = espacioRepository.findById(idEspacio)
        return if (optionalEspacio.isPresent) {
            val espacio = optionalEspacio.get()
            // Modificar los campos necesarios
            espacio.setReservable(reservable)
            espacio.setCategoriaReserva(categoriaReserva!!)
            espacio.setHorario( Horario(horaApertura,horaCierre) )
            espacio.setPorcentajeUsoMaximo(porcentajeUsoMaximo)
            //Recorrer todas las reservas del espacio

            // Guardar la entidad modificada
            espacioRepository.save(espacio)
        } else {
            null
        }
    }

    fun comprobarReservas(espacio: Espacio) {
        val reservas = reservaRepository.findByEspacio(espacio);

    }}
