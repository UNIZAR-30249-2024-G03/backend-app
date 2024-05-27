package unizar.labis.g03.backendapp.domain.valueObjects

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import unizar.labis.g03.backendapp.domain.model.entities.Espacio
import unizar.labis.g03.backendapp.domain.model.entities.Persona
import unizar.labis.g03.backendapp.domain.model.valueObjects.*

class PoliticaDeReservasTests {
    val estudiante = Persona("Estudiante", "Estudiantil", "estudiante@gmail.com", mutableSetOf(Rol.Estudiante))
    val investigadorContratado = Persona("Investigador", "Contratado", "investigador@gmail.com", mutableSetOf(Rol.Investigador_contratado))
    val docenteInvestigador = Persona("Docente", "Investigador", "docente@gmail.com", mutableSetOf(Rol.Docente_investigador), Departamento.Informatica_e_Ingenieria_de_sistemas)
    val tecnicoLaboratorio = Persona("Tecnico", "Laboratorio", "tecnico@gmail.com", mutableSetOf(Rol.tecnico_laboratorio), Departamento.Informatica_e_Ingenieria_de_sistemas)
    val gerente = Persona("Gerente", "Gerenciano", "gerente@gmail.com", mutableSetOf(Rol.Gerente))

    val aula = Espacio("aula", 10f, TipoEspacio.AULA, TipoEspacio.AULA, 10, 0, true, null, null, null)
    val seminarioEina = Espacio("seminario-eina", 10f, TipoEspacio.SEMINARIO, TipoEspacio.SEMINARIO, 10, 0, true, null, null, EntidadAsignableEspacio(TipoEntidadAsignableEspacio.EINA))
    val seminarioDptoInformatica = Espacio("seminario-informatica", 10f, TipoEspacio.SEMINARIO, TipoEspacio.SEMINARIO, 10, 0, true, null, null, EntidadAsignableEspacio(TipoEntidadAsignableEspacio.DEPARTAMENTO, Departamento.Informatica_e_Ingenieria_de_sistemas))
    val laboratorioEina = Espacio("laboratorio-eina", 10f, TipoEspacio.LABORATORIO, TipoEspacio.LABORATORIO, 10, 0, true, null, null, EntidadAsignableEspacio(TipoEntidadAsignableEspacio.EINA))
    val laboratorioDptoInformatica = Espacio("laboratorio-informatica", 10f, TipoEspacio.LABORATORIO, TipoEspacio.LABORATORIO, 10, 0, true, null, null, EntidadAsignableEspacio(TipoEntidadAsignableEspacio.DEPARTAMENTO, Departamento.Informatica_e_Ingenieria_de_sistemas))
    val despachoEina = Espacio("despacho-eina", 10f, TipoEspacio.DESPACHO, TipoEspacio.DESPACHO, 10, 0, true, null, null, EntidadAsignableEspacio(TipoEntidadAsignableEspacio.EINA))
    val despachoDptoInformatica = Espacio("despacho-informatica", 10f, TipoEspacio.DESPACHO, TipoEspacio.DESPACHO, 10, 0, true, null, null, EntidadAsignableEspacio(TipoEntidadAsignableEspacio.DEPARTAMENTO, Departamento.Informatica_e_Ingenieria_de_sistemas))
    val salaComun = Espacio("sala-comun", 10f, TipoEspacio.SALA_COMUN, TipoEspacio.SALA_COMUN, 10, 0, true, null, null, EntidadAsignableEspacio(TipoEntidadAsignableEspacio.EINA))

    // Estudiante
    @Test
    fun testPoliticaReservaAulaXEstudiante (){
        assertFalse(PoliticaDeReservas().tieneAcceso(estudiante, aula))
    }

    @Test
    fun testPoliticaReservaSeminarioEinaXEstudiante (){
        assertFalse(PoliticaDeReservas().tieneAcceso(estudiante, seminarioEina))
    }

    @Test
    fun testPoliticaReservaSeminarioDptoXEstudiante (){
        assertFalse(PoliticaDeReservas().tieneAcceso(estudiante, seminarioDptoInformatica))
    }

    @Test
    fun testPoliticaReservaLaboatorioEinaXEstudiante (){
        assertFalse(PoliticaDeReservas().tieneAcceso(estudiante, laboratorioEina))
    }

    @Test
    fun testPoliticaReservaLaboratorioDptoXEstudiante (){
        assertFalse(PoliticaDeReservas().tieneAcceso(estudiante, laboratorioDptoInformatica))
    }

    @Test
    fun testPoliticaReservaDespachoEinaXEstudiante (){
        assertFalse(PoliticaDeReservas().tieneAcceso(estudiante, despachoEina))
    }

    @Test
    fun testPoliticaReservaDespachoDptoXEstudiante (){
        assertFalse(PoliticaDeReservas().tieneAcceso(estudiante, despachoDptoInformatica))
    }

    @Test
    fun testPoliticaReservaSalaComunXEstudiante (){
        assertTrue(PoliticaDeReservas().tieneAcceso(estudiante, salaComun))
    }

    // Investigador
    @Test
    fun testPoliticaReservaAulaXInvestigador (){
        assertTrue(PoliticaDeReservas().tieneAcceso(investigadorContratado, aula))
    }

    @Test
    fun testPoliticaReservaSeminarioEinaXInvestigador (){
        assertTrue(PoliticaDeReservas().tieneAcceso(investigadorContratado, seminarioEina))
    }

    @Test
    fun testPoliticaReservaSeminarioDptoXInvestigador (){
        assertTrue(PoliticaDeReservas().tieneAcceso(investigadorContratado, seminarioDptoInformatica))
    }

    @Test
    fun testPoliticaReservaLaboatorioEinaXInvestigador (){
        assertTrue(PoliticaDeReservas().tieneAcceso(investigadorContratado, laboratorioEina))
    }

    @Test
    fun testPoliticaReservaLaboratorioMismoDptoXInvestigador (){
        investigadorContratado.setDepartamento(Departamento.Informatica_e_Ingenieria_de_sistemas)
        assertEquals(investigadorContratado.getDepartamento(), Departamento.Informatica_e_Ingenieria_de_sistemas)
        assertTrue(PoliticaDeReservas().tieneAcceso(investigadorContratado, laboratorioDptoInformatica))
    }

    @Test
    fun testPoliticaReservaLaboratorioOtroDptoXInvestigador (){
        investigadorContratado.setDepartamento(Departamento.Ingenieria_electronica_y_comunicaciones)
        assertFalse(PoliticaDeReservas().tieneAcceso(investigadorContratado, laboratorioDptoInformatica))
    }

    @Test
    fun testPoliticaReservaDespachoEinaXInvestigador (){
        assertFalse(PoliticaDeReservas().tieneAcceso(investigadorContratado, despachoEina))
    }

    @Test
    fun testPoliticaReservaDespachoMismoDptoXInvestigador (){
        investigadorContratado.setDepartamento(Departamento.Informatica_e_Ingenieria_de_sistemas)
        assertTrue(PoliticaDeReservas().tieneAcceso(investigadorContratado, despachoDptoInformatica))
    }

    @Test
    fun testPoliticaReservaDespachoOtroDptoXInvestigador (){
        investigadorContratado.setDepartamento(Departamento.Ingenieria_electronica_y_comunicaciones)
        assertFalse(PoliticaDeReservas().tieneAcceso(investigadorContratado, despachoDptoInformatica))
    }

    @Test
    fun testPoliticaReservaSalaComunXInvestigador (){
        assertTrue(PoliticaDeReservas().tieneAcceso(investigadorContratado, salaComun))
    }

    // Docente
    @Test
    fun testPoliticaReservaAulaXDocente (){
        assertTrue(PoliticaDeReservas().tieneAcceso(docenteInvestigador, aula))
    }

    @Test
    fun testPoliticaReservaSeminarioEinaXDocente (){
        assertTrue(PoliticaDeReservas().tieneAcceso(docenteInvestigador, seminarioEina))
    }

    @Test
    fun testPoliticaReservaSeminarioDptoXDocente (){
        assertTrue(PoliticaDeReservas().tieneAcceso(docenteInvestigador, seminarioDptoInformatica))
    }

    @Test
    fun testPoliticaReservaLaboatorioEinaXDocente (){
        assertTrue(PoliticaDeReservas().tieneAcceso(docenteInvestigador, laboratorioEina))
    }

    @Test
    fun testPoliticaReservaLaboratorioMismoDptoXDocente (){
        docenteInvestigador.setDepartamento(Departamento.Informatica_e_Ingenieria_de_sistemas)
        assertEquals(docenteInvestigador.getDepartamento(), Departamento.Informatica_e_Ingenieria_de_sistemas)
        assertTrue(PoliticaDeReservas().tieneAcceso(docenteInvestigador, laboratorioDptoInformatica))
    }

    @Test
    fun testPoliticaReservaLaboratorioOtroDptoXDocente (){
        docenteInvestigador.setDepartamento(Departamento.Ingenieria_electronica_y_comunicaciones)
        assertFalse(PoliticaDeReservas().tieneAcceso(docenteInvestigador, laboratorioDptoInformatica))
    }

    @Test
    fun testPoliticaReservaDespachoEinaXDocente (){
        assertFalse(PoliticaDeReservas().tieneAcceso(docenteInvestigador, despachoEina))
    }

    @Test
    fun testPoliticaReservaDespachoMismoDptoXDocente (){
        docenteInvestigador.setDepartamento(Departamento.Informatica_e_Ingenieria_de_sistemas)
        assertTrue(PoliticaDeReservas().tieneAcceso(docenteInvestigador, despachoDptoInformatica))
    }

    @Test
    fun testPoliticaReservaDespachoOtroDptoXDocente (){
        docenteInvestigador.setDepartamento(Departamento.Ingenieria_electronica_y_comunicaciones)
        assertFalse(PoliticaDeReservas().tieneAcceso(docenteInvestigador, despachoDptoInformatica))
    }

    @Test
    fun testPoliticaReservaSalaComunXDocente (){
        assertTrue(PoliticaDeReservas().tieneAcceso(docenteInvestigador, salaComun))
    }

    // Tecnico de laboratorio
    @Test
    fun testPoliticaReservaAulaXTecnico (){
        assertFalse(PoliticaDeReservas().tieneAcceso(tecnicoLaboratorio, aula))
    }

    @Test
    fun testPoliticaReservaSeminarioEinaXTecnico (){
        assertTrue(PoliticaDeReservas().tieneAcceso(tecnicoLaboratorio, seminarioEina))
    }

    @Test
    fun testPoliticaReservaSeminarioDptoXTecnico (){
        assertTrue(PoliticaDeReservas().tieneAcceso(tecnicoLaboratorio, seminarioDptoInformatica))
    }

    @Test
    fun testPoliticaReservaLaboatorioEinaXTecnico (){
        assertTrue(PoliticaDeReservas().tieneAcceso(tecnicoLaboratorio, laboratorioEina))
    }

    @Test
    fun testPoliticaReservaLaboratorioMismoDptoXTecnico (){
        tecnicoLaboratorio.setDepartamento(Departamento.Informatica_e_Ingenieria_de_sistemas)
        assertEquals(tecnicoLaboratorio.getDepartamento(), Departamento.Informatica_e_Ingenieria_de_sistemas)
        assertTrue(PoliticaDeReservas().tieneAcceso(tecnicoLaboratorio, laboratorioDptoInformatica))
    }

    @Test
    fun testPoliticaReservaLaboratorioOtroDptoXTecnico (){
        tecnicoLaboratorio.setDepartamento(Departamento.Ingenieria_electronica_y_comunicaciones)
        assertFalse(PoliticaDeReservas().tieneAcceso(tecnicoLaboratorio, laboratorioDptoInformatica))
    }

    @Test
    fun testPoliticaReservaDespachoEinaXTecnico (){
        assertFalse(PoliticaDeReservas().tieneAcceso(tecnicoLaboratorio, despachoEina))
    }

    @Test
    fun testPoliticaReservaDespachoMismoDptoXTecnico (){
        tecnicoLaboratorio.setDepartamento(Departamento.Informatica_e_Ingenieria_de_sistemas)
        assertFalse(PoliticaDeReservas().tieneAcceso(tecnicoLaboratorio, despachoDptoInformatica))
    }

    @Test
    fun testPoliticaReservaDespachoOtroDptoXTecnico (){
        tecnicoLaboratorio.setDepartamento(Departamento.Ingenieria_electronica_y_comunicaciones)
        assertFalse(PoliticaDeReservas().tieneAcceso(tecnicoLaboratorio, despachoDptoInformatica))
    }

    @Test
    fun testPoliticaReservaSalaComunXTecnico (){
        assertTrue(PoliticaDeReservas().tieneAcceso(tecnicoLaboratorio, salaComun))
    }

    // Gerente
    @Test
    fun testPoliticaReservaAulaXGerente (){
        assertTrue(PoliticaDeReservas().tieneAcceso(gerente, aula))
    }

    @Test
    fun testPoliticaReservaSeminarioEinaXGerente (){
        assertTrue(PoliticaDeReservas().tieneAcceso(gerente, seminarioEina))
    }

    @Test
    fun testPoliticaReservaSeminarioDptoXGerente (){
        assertTrue(PoliticaDeReservas().tieneAcceso(gerente, seminarioDptoInformatica))
    }

    @Test
    fun testPoliticaReservaLaboatorioEinaXGerente (){
        assertTrue(PoliticaDeReservas().tieneAcceso(gerente, laboratorioEina))
    }

    @Test
    fun testPoliticaReservaLaboratorioMismoDptoXGerente (){
        assertTrue(PoliticaDeReservas().tieneAcceso(gerente, laboratorioEina))
    }

    @Test
    fun testPoliticaReservaLaboratorioOtroDptoXGerente (){
        assertTrue(PoliticaDeReservas().tieneAcceso(gerente, laboratorioDptoInformatica))
    }

    @Test
    fun testPoliticaReservaDespachoEinaXGerente (){
        assertFalse(PoliticaDeReservas().tieneAcceso(tecnicoLaboratorio, despachoEina))
    }

    @Test
    fun testPoliticaReservaDespachoMismoDptoXGerente (){
        assertFalse(PoliticaDeReservas().tieneAcceso(gerente, despachoDptoInformatica))
    }

    @Test
    fun testPoliticaReservaDespachoOtroDptoXGerente (){
        assertFalse(PoliticaDeReservas().tieneAcceso(gerente, despachoDptoInformatica))
    }

    @Test
    fun testPoliticaReservaSalaComunXGerente (){
        assertTrue(PoliticaDeReservas().tieneAcceso(gerente, salaComun))
    }
}