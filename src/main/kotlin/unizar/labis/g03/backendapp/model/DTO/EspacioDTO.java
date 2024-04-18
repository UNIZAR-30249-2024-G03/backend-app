package unizar.labis.g03.backendapp.model.DTO;

import unizar.labis.g03.backendapp.model.valueObjects.Horario;
import unizar.labis.g03.backendapp.model.valueObjects.InfoReserva;

import java.time.LocalDateTime;
import java.util.List;

public class EspacioDTO {
    private String idEspacio;
    private boolean reservable;
    private String tipoEspacio;
    private int horaInicio;
    private int horaFinal;
    private int porcentajeMaximoPermitido;

    public EspacioDTO(String idEspacio, boolean reservable, String tipoEspacio, int horaInicio, int horaFinal, int porcentajeMaximoPermitido) {
        this.idEspacio = idEspacio;
        this.reservable = reservable;
        this.tipoEspacio = tipoEspacio;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.porcentajeMaximoPermitido = porcentajeMaximoPermitido;
    }

    public Horario getHorario() {
        return new Horario(horaInicio, horaFinal);
    }


}
