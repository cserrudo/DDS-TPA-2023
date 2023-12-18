package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Entidad;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Establecimiento;
import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;

public class IncidenteComunidadDTO {
  private long id;
  private Entidad entidad;
  private Establecimiento establecimiento;
  private Servicio servicio;
  private String observaciones;
  private Comunidad comunidad;
  private Boolean abierto;

  public IncidenteComunidadDTO(Entidad entidad, Establecimiento establecimiento, Servicio servicio,
      String observaciones, Comunidad comunidad,
      Boolean abierto, long id) {
    this.entidad = entidad;
    this.establecimiento = establecimiento;
    this.servicio = servicio;
    this.observaciones = observaciones;
    this.comunidad = comunidad;
    this.abierto = abierto;
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public String getEstablecimiento() {
    return establecimiento.getDescripcion();
  }

  public String getEntidad() {
    return entidad.getDescripcion();
  }

  public String getServicio() {
    return servicio.getDescripcion();
  }

  public String getObservaciones() {
    return observaciones;
  }

  public String getComunidad() {
    return comunidad.getNombre();
  }

  public Boolean getAbierto() {
    return abierto;
  }

}