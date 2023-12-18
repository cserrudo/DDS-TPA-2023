package ar.edu.utn.frba.dds.dtos;

public class IncidenteFiltrosDTO {
  private String nombre;
  private Boolean activo;
  private String comunidad;
  private String localizacion;
  private String entidad;
  private String establecimiento;
  private String miembroAlta;
  private String miembroBaja;

  public IncidenteFiltrosDTO(String nombre, Boolean activo, String comunidad, String localizacion,
      String entidad, String establecimiento, String miembroAlta, String miembroBaja) {
    this.nombre = nombre;
    this.activo = activo;
    this.comunidad = comunidad;
    this.localizacion = localizacion;
    this.entidad = entidad;
    this.establecimiento = establecimiento;
    this.miembroAlta = miembroAlta;
    this.miembroBaja = miembroBaja;
  }

  public String getNombre() {
    return nombre;
  }

  public Boolean getActivo() {
    return activo;
  }

  public String getComunidad() {
    return comunidad;
  }

  public String getLocalizacion() {
    return localizacion;
  }

  public String getEntidad() {
    return entidad;
  }

  public String getEstablecimiento() {
    return establecimiento;
  }

  public String getMiembroAlta() {
    return miembroAlta;
  }

  public String getMiembroBaja() {
    return miembroBaja;
  }
}
