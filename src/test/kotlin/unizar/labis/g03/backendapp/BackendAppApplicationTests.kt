package unizar.labis.g03.backendapp

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import unizar.labis.g03.backendapp.model.entities.Persona
import unizar.labis.g03.backendapp.repositories.PersonaRepository

@SpringBootTest
class BackendAppApplicationTests() {
	@Autowired
	private lateinit var personaRepository: PersonaRepository

	@Test
	fun contextLoads() {
		println("HOla mundo!")
		val personas = listOf(
			Persona(email = "persona1@example.com", nombre = "Nombre1", apellido = "Apellido1"),
			Persona(email = "persona2@example.com", nombre = "Nombre2", apellido = "Apellido2"),
			Persona(email = "persona3@example.com", nombre = "Nombre3", apellido = "Apellido3"),
			Persona(email = "persona4@example.com", nombre = "Nombre4", apellido = "Apellido4"),
			Persona(email = "persona5@example.com", nombre = "Nombre5", apellido = "Apellido5")
		)
		personaRepository?.saveAll(personas)

		val personasGuardadas = personaRepository?.findAll()
		println("Personas guardadas: $personasGuardadas")
	}

}
