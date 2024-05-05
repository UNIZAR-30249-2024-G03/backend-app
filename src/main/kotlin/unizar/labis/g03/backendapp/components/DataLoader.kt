package unizar.labis.g03.backendapp.components

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import unizar.labis.g03.backendapp.model.entities.Espacio
import unizar.labis.g03.backendapp.model.entities.Persona
import unizar.labis.g03.backendapp.model.entities.Reserva
import unizar.labis.g03.backendapp.model.valueObjects.*
import unizar.labis.g03.backendapp.repositories.EspacioRepository
import unizar.labis.g03.backendapp.repositories.PersonaRepository
import unizar.labis.g03.backendapp.repositories.ReservaRepository
import java.time.LocalDateTime

@Component
class DataLoader : CommandLineRunner {
    @Autowired
    private val personaRepository: PersonaRepository? = null
    @Autowired
    private val espacioRepository: EspacioRepository? = null
    @Autowired
    private val reservaRepository: ReservaRepository? = null
    @Throws(Exception::class)
    override fun run(vararg args: String) {
        cargarDatosDePrueba()
    }

    private fun cargarDatosDePrueba() {
        println("HOla mundo!")
        val gerente = Persona(email = "gerente@gmail.com", nombre = "Gerente", apellido = "Gerentioso")
        gerente.setRoles(setOf(Rol.Gerente))
        val personas = listOf(
            Persona(email = "persona1@example.com", nombre = "Nombre1", apellido = "Apellido1"),
            Persona(email = "persona2@example.com", nombre = "Nombre2", apellido = "Apellido2"),
            Persona(email = "persona3@example.com", nombre = "Nombre3", apellido = "Apellido3"),
            Persona(email = "persona4@example.com", nombre = "Nombre4", apellido = "Apellido4"),
            Persona(email = "persona5@example.com", nombre = "Nombre5", apellido = "Apellido5"),
            gerente
        )

        val espacios = listOf(
            Espacio(id = "espacio1",tamano = 4f, tipoEspacio = TipoEspacio.AULA, categoriaReserva = TipoEspacio.AULA, numMaxOcupantes = 1, reservable = true, planta = 2, horario = Horario(10,20), porcentajeUsoMaximo = 100, entidadAsignada = EntidadAsignableEspacio(TipoEntidadAsignableEspacio.EINA)),
            Espacio(id = "espacio2",tamano = 8f, tipoEspacio = TipoEspacio.LABORATORIO, categoriaReserva = TipoEspacio.LABORATORIO, numMaxOcupantes = 25, reservable = true, planta = 1, horario = Horario(10,20), porcentajeUsoMaximo = 100, entidadAsignada = EntidadAsignableEspacio(TipoEntidadAsignableEspacio.EINA))
        )

        val reservas = listOf(
            Reserva(id = null, persona = personas[0], espacios = mutableListOf(espacios[0]), infoReserva = InfoReserva(10, LocalDateTime.now(), LocalDateTime.now().plusHours(2), "esto es una reserva", 20)),
            Reserva(null, personas[1], mutableListOf(espacios[1]), InfoReserva(10, LocalDateTime.now(), LocalDateTime.now().plusHours(2), "esto es OTRA reserva", 20))
        )
        personaRepository?.saveAll(personas)

        //espacioRepository?.saveAll(espacios)

        //reservaRepository?.saveAll(reservas)

        val personasGuardadas = personaRepository?.findAll()
        println("Personas guardadas: $personasGuardadas")

        val espaciosGuardados = espacioRepository?.findAll()
        println("Espacios guardadas: $espaciosGuardados")

        val reservasGuardadas = reservaRepository?.findAll()
        println("Reservas guardadas: $reservasGuardadas")
    }
}
