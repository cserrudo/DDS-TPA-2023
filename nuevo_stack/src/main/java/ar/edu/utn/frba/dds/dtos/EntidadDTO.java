package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;

import java.util.List;
import java.util.stream.Collectors;

public class EntidadDTO {
  private Long id;
  private String nombre;
  private String tipo;
  private List<IncidenteComunidad> incidentes;

  public EntidadDTO(Long id, String nombre, String tipo, List<IncidenteComunidad> incidentes) {
    this.id = id;
    this.nombre = nombre;
    this.tipo = tipo;
    this.incidentes = incidentes;
  }

  public Long getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getTipo() {
    return tipo;
  }

  public List<IncidenteComunidadDTO> getIncidentes() {
    return incidentes.stream()
        .map(incidente -> new IncidenteComunidadDTO(incidente.getEntidad(),
            incidente.getEstablecimiento(),
            incidente.getServicio(),
            incidente.getIncidente().getObservaciones(), incidente.getComunidad(),
            incidente.getActivo(),
            incidente.getId()))
        .collect(Collectors.toList());
  }
}
