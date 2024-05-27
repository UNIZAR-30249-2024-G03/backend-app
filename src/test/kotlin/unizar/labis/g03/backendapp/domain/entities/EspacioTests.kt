package unizar.labis.g03.backendapp.domain.entities

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import unizar.labis.g03.backendapp.domain.model.entities.Espacio
import unizar.labis.g03.backendapp.domain.model.valueObjects.*

class EspacioTests {
    @Test
    fun testObtenerHorarioDefecto () {
        val espacioSinHorario = Espacio("id", 20f, TipoEspacio.AULA, TipoEspacio.AULA, 10, 0, true, null, 100, EntidadAsignableEspacio(TipoEntidadAsignableEspacio.EINA))
        assertEquals(Horario.horarioDefecto(), espacioSinHorario.getHorario())
    }

    @Test
    fun testObtenerPorcentajeUsoDefecto () {
        val espacioSinPorcentaje = Espacio("id", 20f, TipoEspacio.AULA, TipoEspacio.AULA, 10, 0, true, Horario(10,20), null, EntidadAsignableEspacio(TipoEntidadAsignableEspacio.EINA))
        assertEquals(PorcentajeUsoDefecto.getPorcentaje(), espacioSinPorcentaje.getPorcentajeUsoMaximo())
    }

    @Test
    fun testObtenerCapacidadMaxima50Porcentaje () {
        val numOcupantes = 10;
        val porcentajeUso = 50f;
        val espacioNormal = Espacio("id", 20f, TipoEspacio.AULA, TipoEspacio.AULA, numOcupantes, 0, true, Horario(10,20), 50, EntidadAsignableEspacio(
            TipoEntidadAsignableEspacio.EINA))
        assertEquals((numOcupantes * (porcentajeUso / 100)).toInt(), espacioNormal.getCapacidadMaxima())
    }

    @Test
    fun testObtenerCapacidadMaximaPorcentajeDefecto () {
        val numOcupantes = 10;
        val espacioSinPorcentaje = Espacio("id", 20f, TipoEspacio.AULA, TipoEspacio.AULA, numOcupantes, 0, true, Horario(10,20), null, EntidadAsignableEspacio(TipoEntidadAsignableEspacio.EINA))
        assertEquals((numOcupantes * (PorcentajeUsoDefecto.getPorcentaje() / 100)), espacioSinPorcentaje.getCapacidadMaxima())
    }
}