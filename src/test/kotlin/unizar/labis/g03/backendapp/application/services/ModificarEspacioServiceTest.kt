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
import unizar.labis.g03.backendapp.domain.model.DTO.EspacioDTO
import unizar.labis.g03.backendapp.domain.model.entities.Espacio
import unizar.labis.g03.backendapp.domain.model.entities.Persona
import unizar.labis.g03.backendapp.domain.model.valueObjects.*
import unizar.labis.g03.backendapp.domain.repositories.EspacioRepository
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository
import unizar.labis.g03.backendapp.domain.repositories.ReservaRepository
import java.util.*

@ExtendWith(MockitoExtension::class)
class ModificarEspacioServiceTest {
    @Mock
    private val mockReservaRepository: ReservaRepository? = null
    @Mock
    private val mockPersonaRepository: PersonaRepository? = null
    @Mock
    private val mockEspacioRepository: EspacioRepository? = null

    @InjectMocks
    private val modificarEspacioService: ModificarEspacioService? = null

    val estudiante = Persona("Paco", "Martinez", "paco@gmail.com", mutableSetOf(Rol.Estudiante))
    val profesor = Persona("Profe", "Profesado", "profe@gmail.com", mutableSetOf(Rol.Docente_investigador))
    val gerente = Persona("Gerente", "Gerencioso", "gerente@gmail.com", mutableSetOf(Rol.Gerente))
    val espacio = Espacio("idEspacio", 20f, TipoEspacio.AULA, TipoEspacio.AULA, 10, 0, true, Horario(), null, EntidadAsignableEspacio())
    val espacioEinaDTO = EspacioDTO("idEspacio", true, TipoEspacio.AULA.toString(), 10, 20, 100, TipoEntidadAsignableEspacio.EINA)
    val espacioDepartamentoDTO = EspacioDTO("idEspacio", true, TipoEspacio.AULA.toString(), 10, 20, 100, TipoEntidadAsignableEspacio.DEPARTAMENTO, Departamento.Informatica_e_Ingenieria_de_sistemas)
    val espacioPersonasDTO = EspacioDTO("idEspacio", true, TipoEspacio.AULA.toString(), 10, 20, 100, TipoEntidadAsignableEspacio.PERSONAS, null, listOf("profe@gmail.com"))

    companion object {
        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            MockitoAnnotations.openMocks(ModificarEspacioServiceTest::class.java)
        } // Tests with the @Test notation
    }

    @Test
    fun modificarEspacioNoGerente() {
        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.of(estudiante))

        Mockito.`when`<Optional<Espacio>>(this.mockEspacioRepository?.findById(any()))
            .thenReturn(Optional.of(espacio))


        modificarEspacioService?.modificarEspacio(espacioEinaDTO, "paco@gmail.com")?.let {
            assertTrue(it.isEmpty)
        }

        Mockito.verify(this.mockEspacioRepository, Mockito.times(0))?.save(any())
    }

    @Test
    fun modificarEspacioAsignandoEINA() {
        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.of(gerente))

        val spyEspacio = Mockito.spy(espacio)

        Mockito.`when`<Optional<Espacio>>(this.mockEspacioRepository?.findById(any()))
            .thenReturn(Optional.of(spyEspacio))


        modificarEspacioService?.modificarEspacio(espacioEinaDTO, "gerente@gmail.com")?.let {
            assertTrue(it.isPresent)
            assertEquals(TipoEntidadAsignableEspacio.EINA, it.get().getEntidadAsignada()?.getTipo())
        }

        Mockito.verify(this.mockEspacioRepository, Mockito.times(1))?.save(any())

        Mockito.verify(spyEspacio, Mockito.times(1)).asignarEina()
    }

    @Test
    fun modificarEspacioAsignandoDepartamento() {
        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.of(gerente))

        val spyEspacio = Mockito.spy(espacio)

        Mockito.`when`<Optional<Espacio>>(this.mockEspacioRepository?.findById(any()))
            .thenReturn(Optional.of(spyEspacio))


        modificarEspacioService?.modificarEspacio(espacioDepartamentoDTO, "gerente@gmail.com")?.let {
            assertTrue(it.isPresent)
            assertEquals(TipoEntidadAsignableEspacio.DEPARTAMENTO, it.get().getEntidadAsignada()?.getTipo())
            assertEquals(espacioDepartamentoDTO.getDepartamentoAsignado(), it.get().getEntidadAsignada()?.getDepartamento()?.get())
        }

        Mockito.verify(this.mockEspacioRepository, Mockito.times(1))?.save(any())

        espacioDepartamentoDTO.getDepartamentoAsignado()
            ?.let { Mockito.verify(spyEspacio, Mockito.times(1)).asignarDepartamento(it) }
    }

    @Test
    fun modificarEspacioAsignandoPersonas() {
        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.of(gerente))

        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail("profe@gmail.com"))
            .thenReturn(Optional.of(profesor))

        val spyEspacio = Mockito.spy(espacio)

        Mockito.`when`<Optional<Espacio>>(this.mockEspacioRepository?.findById(any()))
            .thenReturn(Optional.of(spyEspacio))


        modificarEspacioService?.modificarEspacio(espacioPersonasDTO, "gerente@gmail.com")?.let {
            assertTrue(it.isPresent)
            assertEquals(TipoEntidadAsignableEspacio.PERSONAS, it.get().getEntidadAsignada()?.getTipo())
            assertTrue(it.get().getEntidadAsignada()?.getPersonas()?.get()?.contains(profesor) == true)
        }

        Mockito.verify(this.mockEspacioRepository, Mockito.times(1))?.save(any())


        Mockito.verify(spyEspacio, Mockito.times(1)).asignarPersonas(listOf(profesor))
    }
}