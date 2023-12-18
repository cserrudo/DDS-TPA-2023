package ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente;
import java.util.List;
import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;
import ar.edu.utn.frba.dds.modelo.entidades.notificador.Notificacion;

public class CuandoSuceden implements NotificacionIncidente {
    private Miembro miembro;

    public CuandoSuceden(Miembro miembro) {
        this.miembro = miembro;
    }
	
	public CuandoSuceden() {
	}
	
	@Override
	public void notificarIncidentesSegunHorario() {
	
	}
	
	@Override
    public void notificarIncidente(IncidenteComunidad incidente) {
        String mensaje = this.generarMensaje(incidente);
        Notificacion notificacion = new Notificacion(this.miembro, mensaje);
        this.miembro.getNotificador().notificar(notificacion);
    }
	
	@Override
	public List<Horario> obtenerHorarios() {
		return null;
	}
	
	public String generarMensaje(IncidenteComunidad incidente) {
        if(incidente.isEstaActivo()) return incidente.getMensajeAlta();

        return incidente.getMensajeBaja();
    }
}
