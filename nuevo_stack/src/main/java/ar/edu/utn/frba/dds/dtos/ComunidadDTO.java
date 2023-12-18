package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;

import java.util.List;
import java.util.stream.Collectors;

public class ComunidadDTO {
  private String nombre;
  private int miembrosCantidad;
  private boolean esMiembro;
  private List<IncidenteComunidad> incidentes;

  public ComunidadDTO(String nombre, int miembrosCantidad, boolean esMiembro, List<IncidenteComunidad> incidentes) {
    this.nombre = nombre;
    this.miembrosCantidad = miembrosCantidad;
    this.esMiembro = esMiembro;
    this.incidentes = incidentes;
  }

  public String getNombre() {
    return nombre;
  }

  public int getMiembrosCantidad() {
    return miembrosCantidad;
  }

  public boolean esMiembro() {
    return esMiembro;
  }

  public List<IncidenteComunidadDTO> getIncidentes() {
    return incidentes.stream()
        .map(incidente -> new IncidenteComunidadDTO(incidente.getEntidad(), incidente.getEstablecimiento(),
            incidente.getServicio(),
            incidente.getIncidente().getObservaciones(), incidente.getComunidad(), incidente.getActivo(),
            incidente.getId()))
        .collect(Collectors.toList());
  }
}
