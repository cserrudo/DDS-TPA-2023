package ar.edu.utn.frba.dds.modelo.entidades;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "miembro_comunidad")
public class MiembroxComunidad extends Persistente {

  @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
  @JoinColumn(name = "miembro_id", referencedColumnName = "id")
  private Miembro miembro;

  @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
  @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
  private Comunidad comunidad;

  @Column(name = "es_observador")
  private Boolean esObservador;

  public MiembroxComunidad(Miembro miembro, Comunidad comunidad, Boolean esObservador) {
    this.miembro = miembro;
    this.comunidad = comunidad;
    this.esObservador = esObservador;
    this.setFecha_alta(LocalDateTime.now());
    this.setActivo(true);
  }

  public MiembroxComunidad() {
    this.setFecha_alta(LocalDateTime.now());
    this.setActivo(true);
  }

  public Boolean getEsObservador() {
    return this.esObservador;
  }

  public void toggleEsObservador() {
    this.esObservador = this.esObservador != null ? !this.esObservador : true;
  }

}
