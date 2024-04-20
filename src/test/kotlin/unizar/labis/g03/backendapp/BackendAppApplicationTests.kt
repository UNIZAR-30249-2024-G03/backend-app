package unizar.labis.g03.backendapp

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import unizar.labis.g03.backendapp.model.entities.Persona
import unizar.labis.g03.backendapp.model.valueObjects.Departamento
import unizar.labis.g03.backendapp.model.valueObjects.Rol
import unizar.labis.g03.backendapp.repositories.PersonaRepository

@SpringBootTest
class BackendAppApplicationTests() {
	@Autowired
	private lateinit var personaRepository: PersonaRepository

	@Test
	fun contextLoads() {
		println("HOla mundo!")
		val personasGuardadas = personaRepository.findAll()
		for (persona in personasGuardadas!!) {
			if (persona != null) {
				println(persona.toString())
			}
		}
	}

}
