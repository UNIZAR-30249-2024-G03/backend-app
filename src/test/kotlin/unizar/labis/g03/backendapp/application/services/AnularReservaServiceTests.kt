package unizar.labis.g03.backendapp.application.services

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
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
import unizar.labis.g03.backendapp.domain.services.NotificarPersonas
import java.time.LocalDateTime
import java.util.*


@ExtendWith(MockitoExtension::class)
class AnularReservaServiceTests {
    @Mock
    private val mockReservaRepository: ReservaRepository? = null
    @Mock
    private val mockPersonaRepository: PersonaRepository? = null
    @Mock
    private val notificarPersonas: NotificarPersonas? = null

    @InjectMocks
    private val anularReservaService: AnularReservaService? = null

    val fechahComienzo = LocalDateTime.of(2024, 1, 10, 10, 0)
    val fechaFinal = fechahComienzo.plusHours(2)
    val propietarioReserva = Persona("Paco", "Martinez", "paco@gmail.com", mutableSetOf(Rol.Estudiante))
    val personaAleatoria = Persona("Paco", "Martinez", "random@gmail.com", mutableSetOf(Rol.Estudiante))
    val gerente = Persona("Gerente", "Gerencioso", "gerente@gmail.com", mutableSetOf(Rol.Gerente))
    val reserva = Reserva(id = 1, persona = propietarioReserva, infoReserva = InfoReserva( fechahComienzo, fechaFinal, "descripcion", 10,20))

    companion object {
        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            MockitoAnnotations.openMocks(AnularReservaServiceTests::class.java)
        } // Tests with the @Test notation
    }

    @Test
    fun testAnularReservaInexistente(){
        Mockito.`when`<Optional<Reserva>>(this.mockReservaRepository?.findById(any()))
            .thenReturn(Optional.empty<Reserva>())

        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.of(propietarioReserva))

        anularReservaService?.anularReserva("paco@gmail.com", 1)?.let { assertFalse(it) }

        Mockito.verify(this.mockReservaRepository, Mockito.times(0))?.anularReserva(any())
    }

    @Test
    fun testAnularReservaUsuarioInexistente(){
        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.empty())

        anularReservaService?.anularReserva("paco@gmail.com", 1)?.let { assertFalse(it) }

        Mockito.verify(this.mockReservaRepository, Mockito.times(0))?.anularReserva(any())
    }

    @Test
    fun testAnularReservaGerente(){
        Mockito.`when`<Optional<Reserva>>(this.mockReservaRepository?.findById(any()))
            .thenReturn(Optional.of<Reserva>(reserva))

        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.of(gerente))

        anularReservaService?.anularReserva("gerente@gmail.com", 1)?.let { assertTrue(it) }

        Mockito.verify(this.mockReservaRepository, Mockito.times(1))?.anularReserva(reserva.id)
    }

    @Test
    fun testAnularReservaPropietario(){
        Mockito.`when`<Optional<Reserva>>(this.mockReservaRepository?.findById(any()))
            .thenReturn(Optional.of<Reserva>(reserva))

        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.of(propietarioReserva))

        anularReservaService?.anularReserva("paco@gmail.com", 1)?.let { assertTrue(it) }

        Mockito.verify(this.mockReservaRepository, Mockito.times(1))?.anularReserva(reserva.id)
    }

    @Test
    fun testAnularReservaNoPropietario(){
        Mockito.`when`<Optional<Reserva>>(this.mockReservaRepository?.findById(any()))
            .thenReturn(Optional.of<Reserva>(reserva))

        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.of(personaAleatoria))

        anularReservaService?.anularReserva("random@gmail.com", 1)?.let { assertFalse(it) }

        Mockito.verify(this.mockReservaRepository, Mockito.times(0))?.anularReserva(reserva.id)
    }
}
