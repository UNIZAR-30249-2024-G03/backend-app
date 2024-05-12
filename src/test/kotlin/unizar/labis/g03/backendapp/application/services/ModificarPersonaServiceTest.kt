package unizar.labis.g03.backendapp.application.services

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import unizar.labis.g03.backendapp.application.exceptions.UsuarioNoEncontradoException
import unizar.labis.g03.backendapp.domain.model.entities.Persona
import unizar.labis.g03.backendapp.domain.model.valueObjects.Departamento
import unizar.labis.g03.backendapp.domain.model.valueObjects.Rol
import unizar.labis.g03.backendapp.domain.repositories.EspacioRepository
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository
import unizar.labis.g03.backendapp.domain.services.ActualizarReservas
import java.util.*

@ExtendWith(MockitoExtension::class)
class ModificarPersonaServiceTest{
    @Mock
    private val mockPersonaRepository: PersonaRepository? = null

    @InjectMocks
    private val modificarPersonaService: ModificarPersonaService? = null

    @Test
    fun testModificarPersonaNoExiste(){
        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.empty())

        assertThrows<UsuarioNoEncontradoException> {
            modificarPersonaService?.modificarPersona("noexiste@gmail.com", setOf(Rol.Docente_investigador), Departamento.Informatica_e_Ingenieria_de_sistemas)
        }
    }

    @Test
    fun testModificarPersona(){
        val estudiante = Persona("Paco", "Martinez", "paco@gmail.com", mutableSetOf(Rol.Estudiante))

        val spyEstudiante = Mockito.spy(estudiante)

        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.of(spyEstudiante))


        assertDoesNotThrow(){
            modificarPersonaService?.modificarPersona("paco@gmail.com", setOf(Rol.Docente_investigador), Departamento.Informatica_e_Ingenieria_de_sistemas)
        }

        Mockito.verify(spyEstudiante, Mockito.times(1)).setRoles(setOf(Rol.Docente_investigador))
        Mockito.verify(spyEstudiante, Mockito.times(1)).setDepartamento(Departamento.Informatica_e_Ingenieria_de_sistemas)
        Mockito.verify(this.mockPersonaRepository, Mockito.times(1))?.save(any())
    }
}