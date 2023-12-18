package ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidenteForm {
    private String servicioId;
    private String entidadId;
    private String establecimientoId;
    private String observaciones;


    @Override
    public String toString(){
        return "Incidente Form {" + "campo1=" + servicioId + ", campo2 = " + entidadId + ", campo3 =" + establecimientoId + ". campo4 =" + observaciones;
    }
}
