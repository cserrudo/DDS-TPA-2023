package ar.edu.utn.frba.dds.modelo.entidades.scheduleTask;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;
import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.Horario;

public class GeneradorNotificacionSinApuros extends TimerTask {
	List<Miembro> miembrosPrueba = new ArrayList<>();
	IncidenteComunidad incidenteComunidadPrueba;
	public GeneradorNotificacionSinApuros(List<Miembro> miembroListPrueba ) {
		this.miembrosPrueba = miembroListPrueba;
	}
	
	@Override
  public void run() {
		LocalDateTime fechaActual = LocalDateTime.now();
		Horario horario = new Horario(fechaActual.getDayOfWeek(),String.valueOf(fechaActual.getHour()).concat(":00"));
	  miembrosPrueba.forEach(miembro -> miembro.notificarIncidenteEnHorario(horario));
  }

}
