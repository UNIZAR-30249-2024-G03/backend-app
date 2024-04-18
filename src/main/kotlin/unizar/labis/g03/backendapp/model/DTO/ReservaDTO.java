package unizar.labis.g03.backendapp.model.DTO;

import lombok.Getter;
import unizar.labis.g03.backendapp.model.valueObjects.InfoReserva;

import java.time.LocalDateTime;
import java.util.List;

public class ReservaDTO {
    @Getter
    private final List<String> idEspacios;
    @Getter
    private final String emailUsuario;
    private final LocalDateTime fechaInicio;
    private final LocalDateTime fechaFinal;
    private final int numAsistentesPrevistos;
    private final String descripcion;

    // Constructor
    public ReservaDTO(List<String> idEspacios, String emailUsuario, LocalDateTime fechaInicio, LocalDateTime fechaFinal,
                      int numAsistentesPrevistos, String descripcion) {
        this.idEspacios = idEspacios;
        this.emailUsuario = emailUsuario;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.numAsistentesPrevistos = numAsistentesPrevistos;
        this.descripcion = descripcion;
    }
    public InfoReserva getInfoReserva() {
        return new InfoReserva(numAsistentesPrevistos,fechaInicio, fechaFinal, descripcion,0);
    }

}
