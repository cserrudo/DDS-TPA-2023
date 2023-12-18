package ar.edu.utn.frba.dds.modelo.entidades.notificador;

import ar.edu.utn.frba.dds.modelo.entidades.Miembro;

public class Notificacion {
    private Miembro receptor ;
    private String mensaje ;

    public Notificacion(Miembro receptor, String mensaje) {
        this.receptor = receptor;
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Miembro getReceptor() {
        return receptor;
    }
}
