package unizar.labis.g03.backendapp

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import unizar.labis.g03.backendapp.domain.model.entities.Espacio
import unizar.labis.g03.backendapp.domain.model.entities.Persona
import unizar.labis.g03.backendapp.domain.model.entities.Reserva
import unizar.labis.g03.backendapp.domain.model.valueObjects.*
import unizar.labis.g03.backendapp.domain.repositories.EspacioRepository
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository
import unizar.labis.g03.backendapp.domain.repositories.ReservaRepository
import java.time.LocalDateTime

@SpringBootTest
class BackendAppApplicationTests() {
	@MockBean
	private lateinit var personaRepository: PersonaRepository
	/*
	@Autowired
	private lateinit var reservaRepository: ReservaRepository

	@Autowired
	private lateinit var espacioRepository: EspacioRepository

	private lateinit var personas: List<Persona>
	private lateinit var espacios: List<Espacio>
	private lateinit var reservas: List<Reserva>


	@BeforeEach
	fun loadData() {
		deleteData()
		val gerente = Persona(email = "gerente@gmail.com", nombre = "Gerente", apellido = "Gerentioso")
		gerente.setRoles(setOf(Rol.Gerente))
		personas = listOf(
			Persona(email = "persona1@example.com", nombre = "Nombre1", apellido = "Apellido1"),
			Persona(email = "persona2@example.com", nombre = "Nombre2", apellido = "Apellido2"),
			Persona(email = "persona3@example.com", nombre = "Nombre3", apellido = "Apellido3"),
			Persona(email = "persona4@example.com", nombre = "Nombre4", apellido = "Apellido4"),
			Persona(email = "persona5@example.com", nombre = "Nombre5", apellido = "Apellido5"),
			gerente
		)

		espacios = listOf(
			Espacio(id = "espacio1",tamano = 4f, tipoEspacio = TipoEspacio.AULA, categoriaReserva = TipoEspacio.AULA, numMaxOcupantes = 1, reservable = true, planta = 2, horario = Horario(10,20), porcentajeUsoMaximo = 100, entidadAsignada = EntidadAsignableEspacio(
				TipoEntidadAsignableEspacio.EINA)
			),
			Espacio(id = "espacio2",tamano = 8f, tipoEspacio = TipoEspacio.LABORATORIO, categoriaReserva = TipoEspacio.LABORATORIO, numMaxOcupantes = 25, reservable = true, planta = 1, horario = Horario(10,20), porcentajeUsoMaximo = 100, entidadAsignada = EntidadAsignableEspacio(
				TipoEntidadAsignableEspacio.EINA)
			)
		)

		reservas = listOf(
			Reserva(id = null, persona = personas[0], espacios = mutableListOf(espacios[0]), infoReserva = InfoReserva(10, LocalDateTime.now(), LocalDateTime.now().plusHours(2), "esto es una reserva", 20)),
			Reserva(null, personas[1], mutableListOf(espacios[1]), InfoReserva(10, LocalDateTime.now(), LocalDateTime.now().plusHours(2), "esto es OTRA reserva", 20))

		)
		/*
		 personaRepository?.saveAll(personas)

        espacioRepository?.saveAll(espacios)

        //
		 */
		reservaRepository?.saveAll(reservas)
	}

	fun deleteData() {
		//personaRepository.deleteAll()
		//espacioRepository.deleteAll()
		reservaRepository.deleteAll()
	}

	@Test
	fun contextLoads() {
		val reservasGuardadas = reservaRepository.encontrarReservasConflictivas(espacios[1].getId(),LocalDateTime.now(), LocalDateTime.now().plusHours(2))
		println("Reservas conflictivas: ${reservasGuardadas.size}")

	}
	 */

}
