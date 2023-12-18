package ar.edu.utn.frba.dds.dtos;

public class ComunidadFiltrosDTO {
  private String nombre;
  private Long miembroId;
  private Boolean soyMiembro;

  public ComunidadFiltrosDTO(String nombre, Long miembroId, Boolean soyMiembro) {
    this.nombre = nombre;
    this.miembroId = miembroId;
    this.soyMiembro = soyMiembro;
  }

  public String getNombre() {
    return nombre;
  }

  public Long getMiembroId() {
    return miembroId;
  }

  public Boolean getSoyMiembro() {
    return soyMiembro;
  }
}
