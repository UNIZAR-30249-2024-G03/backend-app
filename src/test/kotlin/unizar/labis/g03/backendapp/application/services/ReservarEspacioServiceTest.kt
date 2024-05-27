package unizar.labis.g03.backendapp.application.services

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import unizar.labis.g03.backendapp.application.exceptions.ReservaNoValidaException
import unizar.labis.g03.backendapp.domain.model.DTO.ReservaDTO
import unizar.labis.g03.backendapp.domain.model.entities.Espacio
import unizar.labis.g03.backendapp.domain.model.entities.Persona
import unizar.labis.g03.backendapp.domain.model.valueObjects.*
import unizar.labis.g03.backendapp.domain.repositories.EspacioRepository
import unizar.labis.g03.backendapp.domain.repositories.PersonaRepository
import unizar.labis.g03.backendapp.domain.repositories.ReservaRepository
import unizar.labis.g03.backendapp.domain.services.ComprobarValidezReservas
import unizar.labis.g03.backendapp.domain.services.NotificarPersonas
import java.time.LocalDateTime
import java.util.*

@ExtendWith(SpringExtension::class)
class ReservarEspacioServiceTest(){
    @Mock
    private val mockPersonaRepository: PersonaRepository? = null
    @Mock
    private val mockEspacioRepository: EspacioRepository? = null
    @Mock
    private val mockReservaRepository: ReservaRepository? = null
    @Mock
    private val mockNotificarPersonas: NotificarPersonas? = null


    private var reservarEspacioService: ReservarEspacioService? = null

    @BeforeEach
    fun setMock(){
        reservarEspacioService = ReservarEspacioService(mockReservaRepository!!, mockEspacioRepository!!, mockPersonaRepository!!, ComprobarValidezReservas(), mockNotificarPersonas!!)
    }

    val aula = Espacio("aula", 10f, TipoEspacio.AULA, TipoEspacio.AULA, 10, 0, true, null, null, null)
    val seminarioEina = Espacio("seminario-eina", 10f, TipoEspacio.SEMINARIO, TipoEspacio.SEMINARIO, 10, 0, true, null, null, EntidadAsignableEspacio(
        TipoEntidadAsignableEspacio.EINA)
    )
    val seminarioDptoInformatica = Espacio("seminario-informatica", 10f, TipoEspacio.SEMINARIO, TipoEspacio.SEMINARIO, 10, 0, true, null, null, EntidadAsignableEspacio(
        TipoEntidadAsignableEspacio.DEPARTAMENTO, Departamento.Informatica_e_Ingenieria_de_sistemas)
    )
    val laboratorioEina = Espacio("laboratorio-eina", 10f, TipoEspacio.LABORATORIO, TipoEspacio.LABORATORIO, 10, 0, true, null, null, EntidadAsignableEspacio(
        TipoEntidadAsignableEspacio.EINA)
    )
    val laboratorioDptoInformatica = Espacio("laboratorio-informatica", 10f, TipoEspacio.LABORATORIO, TipoEspacio.LABORATORIO, 10, 0, true, null, null, EntidadAsignableEspacio(
        TipoEntidadAsignableEspacio.DEPARTAMENTO, Departamento.Informatica_e_Ingenieria_de_sistemas)
    )
    val despachoEina = Espacio("despacho-eina", 10f, TipoEspacio.DESPACHO, TipoEspacio.DESPACHO, 10, 0, true, null, null, EntidadAsignableEspacio(
        TipoEntidadAsignableEspacio.EINA)
    )
    val despachoDptoInformatica = Espacio("despacho-informatica", 10f, TipoEspacio.DESPACHO, TipoEspacio.DESPACHO, 10, 0, true, null, null, EntidadAsignableEspacio(
        TipoEntidadAsignableEspacio.DEPARTAMENTO, Departamento.Informatica_e_Ingenieria_de_sistemas)
    )
    val salaComunNoReservable = Espacio("sala-comun", 10f, TipoEspacio.SALA_COMUN, TipoEspacio.SALA_COMUN, 10, 0, false, null, null, EntidadAsignableEspacio(
        TipoEntidadAsignableEspacio.EINA)
    )
    val salaComunReservable = Espacio("sala-comun", 10f, TipoEspacio.SALA_COMUN, TipoEspacio.SALA_COMUN, 10, 0, true, null, null, EntidadAsignableEspacio(
        TipoEntidadAsignableEspacio.EINA)
    )

    @Test
    fun testReservarEspacioNoReservable(){
        val estudiante = Persona("Paco", "Martinez", "paco@gmail.com", mutableSetOf(Rol.Estudiante))
        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.of(estudiante))
        Mockito.`when`<List<Espacio>>(this.mockEspacioRepository?.findAllById(any()))
            .thenReturn(listOf(salaComunNoReservable))

        val fechaComienzo = LocalDateTime.of(2024, 12, 1, 10, 0)
        val fechaFin = LocalDateTime.of(2024, 12, 1, 12, 0)
        val reservaDTO = ReservaDTO(listOf("id"), "email@gmail.com", fechaComienzo, fechaFin, 2, "Descripcion")
        assertNotNull(reservarEspacioService)
        org.junit.jupiter.api.assertThrows<ReservaNoValidaException> {reservarEspacioService?.reservarEspacios(reservaDTO)}
    }

    @Test
    fun testReservarEspacioHoraNoValida(){
        val estudiante = Persona("Paco", "Martinez", "paco@gmail.com", mutableSetOf(Rol.Estudiante))
        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.of(estudiante))
        Mockito.`when`<List<Espacio>>(this.mockEspacioRepository?.findAllById(any()))
            .thenReturn(listOf(salaComunReservable))

        val fechaComienzo = LocalDateTime.of(2024, 12, 1, 0, 0)
        val fechaFin = LocalDateTime.of(2024, 12, 1, 2, 0)
        val reservaDTO = ReservaDTO(listOf("id"), "email@gmail.com", fechaComienzo, fechaFin, 2, "Descripcion")
        assertNotNull(reservarEspacioService)
        org.junit.jupiter.api.assertThrows<ReservaNoValidaException> {reservarEspacioService?.reservarEspacios(reservaDTO)}
    }

    @Test
    fun testReservarEspacioHoraNoPermiso(){
        val estudiante = Persona("Paco", "Martinez", "paco@gmail.com", mutableSetOf(Rol.Estudiante))
        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.of(estudiante))
        Mockito.`when`<List<Espacio>>(this.mockEspacioRepository?.findAllById(any()))
            .thenReturn(listOf(aula))

        val fechaComienzo = LocalDateTime.of(2024, 12, 1, 0, 0)
        val fechaFin = LocalDateTime.of(2024, 12, 1, 2, 0)
        val reservaDTO = ReservaDTO(listOf("id"), "email@gmail.com", fechaComienzo, fechaFin, 2, "Descripcion")
        assertNotNull(reservarEspacioService)
        org.junit.jupiter.api.assertThrows<ReservaNoValidaException> {reservarEspacioService?.reservarEspacios(reservaDTO)}
    }

    @Test
    fun testReservarEspacioValida(){
        val estudiante = Persona("Paco", "Martinez", "paco@gmail.com", mutableSetOf(Rol.Estudiante))
        Mockito.`when`<Optional<Persona>>(this.mockPersonaRepository?.findByEmail(any()))
            .thenReturn(Optional.of(estudiante))
        Mockito.`when`<List<Espacio>>(this.mockEspacioRepository?.findAllById(any()))
            .thenReturn(listOf(salaComunReservable))

        val fechaComienzo = LocalDateTime.of(2024, 12, 1, 10, 0)
        val fechaFin = LocalDateTime.of(2024, 12, 1, 12, 0)
        val reservaDTO = ReservaDTO(listOf("id"), "email@gmail.com", fechaComienzo, fechaFin, 2, "Descripcion")
        assertNotNull(reservarEspacioService)
        val reserva = reservarEspacioService?.reservarEspacios(reservaDTO)
        assertTrue(reserva?.isPresent == true)
    }
}