package ar.edu.utn.frba.dds.dtos;

public class OrganismoDTO {
  private Long id;
  private String nombre;

  public OrganismoDTO(Long id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  public Long getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }
}