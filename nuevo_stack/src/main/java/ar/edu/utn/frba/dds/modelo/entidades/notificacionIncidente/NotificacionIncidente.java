package ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente;

import java.util.List;

import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;

public interface NotificacionIncidente {
	// TODO: notificar con cron segun los horarios guardados
	void notificarIncidentesSegunHorario();
	
	public void notificarIncidente(IncidenteComunidad incidente);
	
	public List<Horario> obtenerHorarios();
	
}
