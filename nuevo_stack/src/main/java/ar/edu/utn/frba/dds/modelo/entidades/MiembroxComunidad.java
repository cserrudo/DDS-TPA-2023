package ar.edu.utn.frba.dds.modelo.entidades;

import javax.persistence.*;

@Entity
@Table(name = "miembro_comunidad")
public class MiembroxComunidad extends Persistente {

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
  @JoinColumn(name = "miembro_id", referencedColumnName = "id")
  private Miembro miembro;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
  @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
  private Comunidad comunidad;

  @Column(name = "es_observador")
  private Boolean esObservador;

  public MiembroxComunidad(Miembro miembro, Comunidad comunidad, Boolean esObservador){
    this.miembro = miembro;
    this.comunidad = comunidad;
    this.esObservador = esObservador;
  }

  public MiembroxComunidad() {

  }
}
