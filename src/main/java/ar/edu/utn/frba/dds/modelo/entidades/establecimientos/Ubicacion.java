package ar.edu.utn.frba.dds.modelo.entidades.establecimientos;

import ar.edu.utn.frba.dds.modelo.entidades.Persistente;

import javax.persistence.*;

@Entity
@Table(name = "ubicacion")
public class Ubicacion extends Persistente {
    @Column(name = "latitud")
    private double latitud;
    @Column(name = "longitud")
    private double longitud;

    public Ubicacion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Ubicacion() {

    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public Boolean ubicacionCercaDe(Ubicacion ubicacion){
        return false;
    }
}
