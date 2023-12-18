package ar.edu.utn.frba.dds.modelo.entidades.scheduleTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;

public class ScheduelTask {
	
	List<Miembro> miembroListPrueba = new ArrayList<>();
	
	IncidenteComunidad incidentePrueba;
	
	public void run() {
		Timer timer = new Timer();
		Calendar now = Calendar.getInstance();
		Integer desiredDayOfWeek = Calendar.MONDAY;
		Integer currentDayOfWeek = now.get(Calendar.DAY_OF_WEEK);
		long initialDelay2 = getNextHourDelay(now);
		int daysUntilNextDesiredDay = (desiredDayOfWeek - currentDayOfWeek + 7) % 7;
		long initialDelay = daysUntilNextDesiredDay * 24 * 60 * 60 * 1000;
		long period = 7 * 24 * 60 * 60 * 1000;
		long period2 = 60 * 1000; // 1 minuto en milisegundos
		//timer.schedule(new GeneradorRanking(), initialDelay, period);
		//timer.schedule(new GeneradorNotificacionSinApuros(), initialDelay2, period2);
		//TODO remover esto porque es solo para esta prueba
		timer.schedule(new GeneradorNotificacionSinApuros(miembroListPrueba),0,period2);
		
	}
	
	public void setMiembroListPrueba(List<Miembro> miembroListPrueba) {
		this.miembroListPrueba = miembroListPrueba;
	}
	
	public void setIncidentePrueba(IncidenteComunidad incidentePrueba) {
		this.incidentePrueba = incidentePrueba;
	}
	
	private static long getNextHourDelay(Calendar now) {
		int currentMinute = now.get(Calendar.MINUTE);
		int currentSecond = now.get(Calendar.SECOND);
		int currentMillisecond = now.get(Calendar.MILLISECOND);
		
		// Calcula el tiempo restante hasta la siguiente hora en milisegundos
		long remainingMillisUntilNextHour = (60 - currentMinute - 1) * 60 * 1000
				+ (60 - currentSecond) * 1000
				+ (1000 - currentMillisecond);
		
		return remainingMillisUntilNextHour;
	}
}
