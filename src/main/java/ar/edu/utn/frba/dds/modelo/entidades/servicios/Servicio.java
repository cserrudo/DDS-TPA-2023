package ar.edu.utn.frba.dds.modelo.entidades.servicios;

import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.Persistente;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Ubicacion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servicio")
public class Servicio extends Persistente {
    @Column(name = "descrpcion")
    private String descripcion;
    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "servicio_id", referencedColumnName = "id")
    private List<IncidenteComunidad> incidentes = new ArrayList<>();
    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
    private Ubicacion ubicacion;

    public Servicio(String descripcion, Ubicacion ubicacion) {
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
    }

    public Servicio() {
        this.incidentes = new ArrayList<>();
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Ubicacion getUbicacion() {
        return this.ubicacion;
    }

    public List<IncidenteComunidad> getIncidentes() {
        return this.incidentes;
    }

    public void agregarIncidente(IncidenteComunidad incidente) {
        this.incidentes.add(incidente);
    }

    public void sacarIncidente(IncidenteComunidad incidente) {
        this.incidentes.remove(incidente);
    }

    public Boolean estaHabilitado(Miembro miembro) {
        return !this.incidentes.stream()
                .anyMatch(incidenteComunidad -> miembro.getComunidades().contains(incidenteComunidad.getComunidad()));
    }

    public void miembroCerca(Miembro miembro) {
        if (miembro.getGeolocalizacion().ubicacionCercaDe(this.ubicacion)) {
            this.incidentes.forEach(incidenteComunidad -> incidenteComunidad.posibleMiembroCerca(miembro));
        }
    }
}
