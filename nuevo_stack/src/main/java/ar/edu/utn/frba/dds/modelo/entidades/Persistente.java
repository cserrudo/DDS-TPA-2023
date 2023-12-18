package ar.edu.utn.frba.dds.modelo.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class Persistente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "activo")
  private Boolean activo;

  @Column(name = "fecha_alta")
  private LocalDateTime fecha_alta;
  @Column(name = "fecha_baja")
  private LocalDateTime fecha_baja;

  public Persistente () {
    this.activo = true;
    this.fecha_alta = LocalDateTime.now();
    this.fecha_baja = null;
  }

  private void inactivar(){
    this.activo = false;
    this.fecha_baja = LocalDateTime.now();
  }

}
