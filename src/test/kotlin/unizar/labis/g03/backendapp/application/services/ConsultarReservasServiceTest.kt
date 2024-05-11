package unizar.labis.g03.backendapp.application.services

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import unizar.labis.g03.backendapp.domain.model.entities.Persona
import unizar.labis.g03.backendapp.domain.model.entities.Reserva
import unizar.labis.g03.backendapp.domain.model.valueObjects.InfoReserva
import unizar.labis.g03.backendapp.domain.model.valueObjects.Rol
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository
import unizar.labis.g03.backendapp.domain.repositories.ReservaRepository
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockitoExtension::class)
class ConsultarReservasServiceTest {

    @Mock
    private val mockReservaRepository: ReservaRepository? = null
    @Mock
    private val mockPersonaRepository: PersonaRepository? = null

    @InjectMocks
    private val consultarReservasService: ConsultarReservasService? = null

    val fechahComienzo = LocalDateTime.of(2024, 1, 10, 10, 0)
    val fechaFinal = fechahComienzo.plusHours(2)
    val propietarioReserva = Persona("Paco", "Martinez", "paco@gmail.com", mutableSetOf(Rol.Estudiante))
    val gerente = Persona("Gerente", "Gerencioso", "gerente@gmail.com", mutableSetOf(Rol.Gerente))
    val reserva = Reserva(id = 1, persona = propietarioReserva, infoReserva = InfoReserva(fechahComienzo, fechaFinal, "descripcion", 10,20))

    companion object {
        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            MockitoAnnotations.openMocks(ConsultarReservasServiceTest::class.java)
        } // Tests with the @Test notation
    }

    @Test
    fun consultarReservasPersonaInexistente() {
        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.empty())

        consultarReservasService?.consultarReservas("noexiste@gmail.com")?.let {
            assertNull(it)
        }
    }

    @Test
    fun consultarReservasPersona() {
        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.of(propietarioReserva))

        Mockito.`when`<List<Reserva>>(this.mockReservaRepository?.findByPersona(any()))
            .thenReturn(listOf(reserva))

        consultarReservasService?.consultarReservas("propietario@gmail.com")?.let {
            assertArrayEquals(it.toTypedArray(), arrayOf(reserva))
        }

        Mockito.verify(this.mockReservaRepository, Mockito.times(1))?.findByPersona(propietarioReserva)
        Mockito.verify(this.mockReservaRepository, Mockito.times(0))?.findByAnulado(any())
    }

    @Test
    fun consultarReservasGerente() {
        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.of(gerente))

        Mockito.`when`<List<Reserva>>(this.mockReservaRepository?.findByAnulado(false))
            .thenReturn(listOf(reserva))

        consultarReservasService?.consultarReservas("gerente@gmail.com")?.let {
            assertArrayEquals(it.toTypedArray(), arrayOf(reserva))
        }

        Mockito.verify(this.mockReservaRepository, Mockito.times(0))?.findByPersona(any())
        Mockito.verify(this.mockReservaRepository, Mockito.times(1))?.findByAnulado(false)
    }
}