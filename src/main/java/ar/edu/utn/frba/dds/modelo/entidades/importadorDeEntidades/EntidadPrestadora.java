package ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades;

import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "entidad_prestadora")
public class EntidadPrestadora {
    @Getter
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "nombre")
    private String nombre;
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinTable(name = "servicios_entidad_prestadora", joinColumns = @JoinColumn(name = "entidad_prestadora_id"), inverseJoinColumns = @JoinColumn(name = "servicio_id"))
    private List<Servicio> servicios = new ArrayList<>();

    public EntidadPrestadora(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.servicios = new ArrayList<>();
    }

    public EntidadPrestadora() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

}
