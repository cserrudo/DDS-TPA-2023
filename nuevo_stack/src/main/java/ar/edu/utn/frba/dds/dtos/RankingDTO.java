package ar.edu.utn.frba.dds.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingDTO {
    private Integer puesto;
    private String entidad;
    private long promedioTiempo;
    private long cantIncidentes;
    private Integer gradoImpacto;


    public RankingDTO(Integer puesto, String entidad, long promedioTiempo, long cantIncidentes) {
        this.puesto = puesto;
        this.entidad = entidad;
        this.promedioTiempo = promedioTiempo;
        this.cantIncidentes = cantIncidentes;

    }
}
