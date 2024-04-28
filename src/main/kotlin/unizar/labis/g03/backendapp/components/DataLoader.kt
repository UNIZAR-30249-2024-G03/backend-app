package unizar.labis.g03.backendapp.components

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import unizar.labis.g03.backendapp.model.entities.Persona
import unizar.labis.g03.backendapp.repositories.PersonaRepository

@Component
class DataLoader : CommandLineRunner {
    @Autowired
    private val personaRepository: PersonaRepository? = null
    @Throws(Exception::class)
    override fun run(vararg args: String) {
        cargarDatosDePrueba()
    }

    private fun cargarDatosDePrueba() {
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
