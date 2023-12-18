package ar.edu.utn.frba.dds.modelo.entidades.localizaciones;

import ar.edu.utn.frba.dds.modelo.entidades.Persistente;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Entidad;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "localizacion")
public class Localizacion extends Persistente {
    @Column(name = "nombre")
    private String nombre;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_localizacion")
    private TipoLocalizacion tipoLocalizacion;
    @OneToMany(mappedBy = "localizacion")
    private List<Entidad> entidades;

    public Localizacion(String nombre, TipoLocalizacion tipoLocalizacion, List<Entidad> entidades) {
        this.nombre = nombre;
        this.tipoLocalizacion = tipoLocalizacion;
        this.entidades = entidades;
    }

    public Localizacion() {
        this.entidades =  new ArrayList<>();
    }

    public String getNombre() { return nombre; }

    public TipoLocalizacion getTipoLocalizacion() { return tipoLocalizacion; }

}
