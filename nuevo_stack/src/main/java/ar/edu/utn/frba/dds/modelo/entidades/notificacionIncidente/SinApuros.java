package ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente;

import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;
import ar.edu.utn.frba.dds.modelo.entidades.notificador.Notificacion;

import java.util.ArrayList;
import java.util.List;


public class SinApuros implements NotificacionIncidente {
	private List<Horario> horarios = new ArrayList<>();
	private List<IncidenteComunidad> incidentesPorNotificar = new ArrayList<>();
	private Miembro miembro;
	
	public SinApuros(List<Horario> horarios, Miembro miembro) {
		this.horarios = horarios;
		this.miembro = miembro;
	}
	
	// TODO: notificar con cron segun los horarios guardados
	@Override
	public void notificarIncidentesSegunHorario() {
		if (!this.incidentesPorNotificar.isEmpty()) {
			String mensaje = this.generarMensaje();
			Notificacion notificacion = new Notificacion(this.miembro, mensaje);
			this.miembro
					.getNotificador()
					.notificar(notificacion);
			this.vaciarIncidentes();
		} else {
			System.out.println("No hay incidentes para notificar");
		}
	}
	
	@Override
	public void notificarIncidente(IncidenteComunidad incidente) {
		this.nuevoIncidente(incidente);
	}
	
	@Override
	public List<Horario> obtenerHorarios() {
		return this.horarios;
	}
	
	public String generarMensaje() {
		StringBuilder mensajeBuilder = new StringBuilder();
		
		this.incidentesPorNotificar.forEach(incidente -> {
			Boolean incidenteActivo = incidente.isEstaActivo();
			
			if (incidenteActivo) {
				mensajeBuilder.append(incidente.getMensajeAlta());
			} else {
				mensajeBuilder.append(incidente.getMensajeBaja());
			}
			
			mensajeBuilder.append("\n");
		});
		
		return mensajeBuilder.toString();
	}
	
	public void nuevoIncidente(IncidenteComunidad incidente) {
		incidentesPorNotificar.add(incidente);
	}
	
	public void vaciarIncidentes() {
		incidentesPorNotificar.clear();
	}
	
	public SinApuros(){}
	
}
