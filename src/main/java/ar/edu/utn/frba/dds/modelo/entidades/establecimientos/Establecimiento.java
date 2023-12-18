package ar.edu.utn.frba.dds.modelo.entidades.establecimientos;

import ar.edu.utn.frba.dds.modelo.entidades.Persistente;
import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "establecimiento")
public class Establecimiento extends Persistente {
    @Column(name = "descripcion")
    private String descripcion;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ubicacion_id",referencedColumnName = "id")
    private Ubicacion ubicacion;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "establecimiento_id", referencedColumnName = "id")
    private List<Servicio> servicios;

    public Establecimiento(String descripcion, Ubicacion ubicacion, List<Servicio> servicios) {
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.servicios = servicios;
    }

    public Establecimiento() {
        this.servicios= new ArrayList<>();
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void agregarServicio(Servicio servicio) { this.servicios.add(servicio); }

    public List<Servicio> obtenerServiciosConIncidentes() {
        return this.servicios.stream().filter(servicio -> servicio.getIncidentes().isEmpty()).collect(Collectors.toList());
    }
}
