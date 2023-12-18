package ar.edu.utn.frba.dds.dtos;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;

public class ComunidadDTO {
  private String nombre;
  private int miembrosCantidad;
  private boolean esMiembro;
  private List<IncidenteComunidad> incidentes;
  private long id;
  private boolean esObservador;

  public ComunidadDTO(String nombre, int miembrosCantidad, boolean esMiembro, List<IncidenteComunidad> incidentes,
      long id, boolean esObservador) {
    this.nombre = nombre;
    this.miembrosCantidad = miembrosCantidad;
    this.esMiembro = esMiembro;
    this.incidentes = incidentes;
    this.id = id;
    this.esObservador = esObservador;
  }

  public long getId() {
    return id;
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

  public boolean esObservador() {
    return esObservador;
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
