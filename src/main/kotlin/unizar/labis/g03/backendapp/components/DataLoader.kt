package unizar.labis.g03.backendapp.components

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import unizar.labis.g03.backendapp.domain.model.entities.Espacio
import unizar.labis.g03.backendapp.domain.model.entities.Persona
import unizar.labis.g03.backendapp.domain.model.entities.Reserva
import unizar.labis.g03.backendapp.domain.model.valueObjects.*
import unizar.labis.g03.backendapp.domain.repositories.EspacioRepository
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository
import unizar.labis.g03.backendapp.domain.repositories.ReservaRepository
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
        val gerente = Persona(email = "gerente@gmail.com", nombre = "Gerente", apellido = "Gerentioso")
        gerente.setRoles(setOf(Rol.Gerente))
        val profesorInformatica = Persona(email = "profesor@gmail.com", nombre = "Profesor", apellido = "Informatica")
        profesorInformatica.setRoles(setOf(Rol.Docente_investigador))
        val tecnicoLaboratorio = Persona(email = "tecnico@gmail.com", nombre = "Tecnico", apellido = "Laboratorio")
        tecnicoLaboratorio.setRoles(setOf(Rol.tecnico_laboratorio))
        tecnicoLaboratorio.setDepartamento(Departamento.Informatica_e_Ingenieria_de_sistemas)
        val personas = listOf(
            Persona(email = "estudiante@gmail.com", roles = mutableSetOf(Rol.Estudiante), nombre = "Estudiante", apellido = "Estudiantil"),
            Persona(email = "persona2@example.com", roles = mutableSetOf(Rol.Estudiante), nombre = "Nombre2", apellido = "Apellido2"),
            Persona(email = "persona3@example.com", roles = mutableSetOf(Rol.Estudiante), nombre = "Nombre3", apellido = "Apellido3"),
            Persona(email = "persona4@example.com", roles = mutableSetOf(Rol.Estudiante), nombre = "Nombre4", apellido = "Apellido4"),
            Persona(email = "persona5@example.com", roles = mutableSetOf(Rol.Estudiante), nombre = "Nombre5", apellido = "Apellido5"),
            gerente,
            profesorInformatica,
            tecnicoLaboratorio
        )

        val espacios = listOf(
            Espacio(id = "espacio1",tamano = 4f, tipoEspacio = TipoEspacio.AULA, categoriaReserva = TipoEspacio.AULA, numMaxOcupantes = 1, reservable = true, planta = 2, horario = Horario(10,20), porcentajeUsoMaximo = 100, entidadAsignada = EntidadAsignableEspacio(
                TipoEntidadAsignableEspacio.EINA)
            ),
            Espacio(id = "espacio2",tamano = 8f, tipoEspacio = TipoEspacio.LABORATORIO, categoriaReserva = TipoEspacio.LABORATORIO, numMaxOcupantes = 25, reservable = true, planta = 1, horario = Horario(10,20), porcentajeUsoMaximo = 100, entidadAsignada = EntidadAsignableEspacio(
                TipoEntidadAsignableEspacio.EINA)
            )
        )

        val reservas = listOf(
            Reserva(id = null, persona = personas[0], espacios = mutableListOf(espacios[0]), infoReserva = InfoReserva( LocalDateTime.now(), LocalDateTime.now().plusHours(2), "esto es una reserva", 20,10)),
            Reserva(null, personas[1], mutableListOf(espacios[1]), InfoReserva( LocalDateTime.now(), LocalDateTime.now().plusHours(2), "esto es OTRA reserva", 20,10))
        )
        personaRepository?.saveAll(personas)

        //  espacioRepository?.saveAll(espacios)

        //reservaRepository?.saveAll(reservas)

        val personasGuardadas = personaRepository?.findAll()
        println("Personas guardadas: $personasGuardadas")

        val espaciosGuardados = espacioRepository?.findAll()
        println("Espacios guardadas: $espaciosGuardados")

        val reservasGuardadas = reservaRepository?.findAll()
        println("Reservas guardadas: $reservasGuardadas")
    }
}
