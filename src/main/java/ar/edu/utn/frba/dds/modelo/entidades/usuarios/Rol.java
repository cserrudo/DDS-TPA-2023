package ar.edu.utn.frba.dds.modelo.entidades.usuarios;

import ar.edu.utn.frba.dds.modelo.entidades.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rol")
@Getter
@Setter
public class Rol extends Persistente {
    @Column(name = "nombre")
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoRol tipo;
}
