package ar.edu.utn.frba.dds.modelo.entidades.notificador;

import ar.edu.utn.frba.dds.modelo.entidades.converters.EstrategiaNotificacionConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
public class Notificador {
    @Convert(converter = EstrategiaNotificacionConverter.class)
    @Column(name = "medio_notificacion")
    private EstrategiaNotificacion medioNotificacion;

    public Notificador(EstrategiaNotificacion medioNotificacion) {
        this.medioNotificacion = medioNotificacion;
    }

    public Notificador() {

    }

    public void cambiarMedioNotificacion(EstrategiaNotificacion nuevoMedio) {
        this.medioNotificacion = nuevoMedio;
    }

    public void notificar(Notificacion notificacion) {
        medioNotificacion.enviarNotificacion(notificacion);
    }
}
