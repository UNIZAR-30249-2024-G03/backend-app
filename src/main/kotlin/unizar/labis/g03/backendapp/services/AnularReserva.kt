package unizar.labis.g03.backendapp.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unizar.labis.g03.backendapp.repositories.ReservaRepository

@Service
class AnularReserva @Autowired constructor(private val reservaRepository: ReservaRepository) {
    fun anularReserva(idReserva: String?) {
        reservaRepository.anularReserva(idReserva!!)
    }
}
