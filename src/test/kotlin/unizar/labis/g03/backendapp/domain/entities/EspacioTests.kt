package unizar.labis.g03.backendapp.domain.entities

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import unizar.labis.g03.backendapp.domain.model.entities.Espacio
import unizar.labis.g03.backendapp.domain.model.valueObjects.EntidadAsignableEspacio
import unizar.labis.g03.backendapp.domain.model.valueObjects.Horario
import unizar.labis.g03.backendapp.domain.model.valueObjects.PorcentajeUsoDefecto
import unizar.labis.g03.backendapp.domain.model.valueObjects.TipoEspacio

class EspacioTests {
    @Test
    fun testObtenerHorarioDefecto () {
        val espacioSinHorario = Espacio("id", 20f, TipoEspacio.AULA, TipoEspacio.AULA, 10, 0, true, null, 100, EntidadAsignableEspacio())
        assertEquals(Horario.horarioDefecto(), espacioSinHorario.getHorario())
    }

    @Test
    fun testObtenerPorcentajeUsoDefecto () {
        val espacioSinPorcentaje = Espacio("id", 20f, TipoEspacio.AULA, TipoEspacio.AULA, 10, 0, true, Horario(10,20), null, EntidadAsignableEspacio())
        assertEquals(PorcentajeUsoDefecto.getPorcentaje(), espacioSinPorcentaje.getPorcentajeUsoMaximo())
    }

    @Test
    fun testObtenerCapacidadMaxima50Porcentaje () {
        val numOcupantes = 10;
        val porcentajeUso = 50f;
        val espacioNormal = Espacio("id", 20f, TipoEspacio.AULA, TipoEspacio.AULA, numOcupantes, 0, true, Horario(10,20), 50, EntidadAsignableEspacio())
        assertEquals((numOcupantes * (porcentajeUso / 100)).toInt(), espacioNormal.getCapacidadMaxima())
    }

    @Test
    fun testObtenerCapacidadMaximaPorcentajeDefecto () {
        val numOcupantes = 10;
        val espacioSinPorcentaje = Espacio("id", 20f, TipoEspacio.AULA, TipoEspacio.AULA, numOcupantes, 0, true, Horario(10,20), null, EntidadAsignableEspacio())
        assertEquals((numOcupantes * (PorcentajeUsoDefecto.getPorcentaje() / 100)), espacioSinPorcentaje.getCapacidadMaxima())
    }
}