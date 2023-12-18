package ar.edu.utn.frba.dds.dtos;

public class EntidadFiltrosDTO {
  private String nombre;
  private Boolean activo;
  private String servicio;
  private String comunidad;
  private String localizacion;

  public EntidadFiltrosDTO(String nombre, Boolean activo, String servicio, String comunidad, String localizacion) {
    this.nombre = nombre;
    this.activo = activo;
    this.servicio = servicio;
    this.comunidad = comunidad;
    this.localizacion = localizacion;
  }

  public String getNombre() {
    return nombre;
  }

  public Boolean getActivo() {
    return activo;
  }

  public String getServicio() {
    return servicio;
  }

  public String getComunidad() {
    return comunidad;
  }

  public String getLocalizacion() {
    return localizacion;
  }
}
