package ar.edu.utn.frba.dds;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import org.junit.jupiter.api.Test;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Ubicacion;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.Incidente;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;
import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.TipoLocalizacion;
import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.CuandoSuceden;
import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.Horario;
import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.NotificacionIncidente;
import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.SinApuros;
import ar.edu.utn.frba.dds.modelo.entidades.notificador.EstrategiaNotificacion;
import ar.edu.utn.frba.dds.modelo.entidades.notificador.NotificacionWhatsapp;
import ar.edu.utn.frba.dds.modelo.entidades.notificador.Notificador;
import ar.edu.utn.frba.dds.modelo.entidades.scheduleTask.ScheduelTask;
import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;

public class cronTaskTest {
	
	
	@Test
	public void testMiembroNotificacion(){
		Localizacion localizacion = new Localizacion("avellaneda", TipoLocalizacion.MUNICIPIO, new ArrayList<>());
		Miembro miembro = new Miembro("Juan", "Perez", "juan@example.com", localizacion);
		
		EstrategiaNotificacion medioNotificacion = new NotificacionWhatsapp();
		Notificador notificador = new Notificador(medioNotificacion);
		Horario horario1 = new Horario(DayOfWeek.MONDAY, "9:00");
		Horario horario2 = new Horario(DayOfWeek.FRIDAY, "9:00");
		List<Horario> horarios = new ArrayList<>();
		horarios.add(horario1);
		horarios.add(horario2);
		NotificacionIncidente formaNotificacion = new SinApuros(horarios, miembro);
		
		miembro.setFormaNotificacion(formaNotificacion);
		miembro.setNotificador(notificador);
		miembro.setCelular("5491165820111");
		
		Comunidad comunidad = new Comunidad("comunidad");
		miembro.altaComunidad(comunidad);
		
		Servicio servicio = new Servicio("Ascensor", new Ubicacion(3.42391, -41.16461));
		Incidente incidente = new Incidente(servicio, "Fuera de servicio", miembro);
		Incidente incidente2 = new Incidente(servicio, "Fuera de servicio 2", miembro);
		
		incidente.darDeBaja(miembro);
		incidente2.darDeBaja(miembro);
		
		IncidenteComunidad incidenteComunidad = new IncidenteComunidad(incidente,comunidad);
		
		ScheduelTask scheduelTask = new ScheduelTask();
		scheduelTask.setMiembroListPrueba(List.of(miembro));
		scheduelTask.setIncidentePrueba(incidenteComunidad);
		scheduelTask.run();
		
		
	}
	
	private IncidenteComunidad crearIncidentePrueba() {
		//Incidente incidente = new Incidente();
		return null;
	}
	
	private List<Miembro> crearMiembros() {
		Localizacion localizacion1 = new Localizacion("racingAfuera", TipoLocalizacion.MUNICIPIO, new ArrayList<>());
		Localizacion localizacion2 = new Localizacion("LaBocaElMasGrande", TipoLocalizacion.MUNICIPIO, new ArrayList<>());
		Miembro miembro1 = new Miembro("emi","fernandez","emfer@gmail.com",localizacion1);
		miembro1.setFormaNotificacion(new SinApuros(List.of(new Horario(DayOfWeek.TUESDAY,"00:00")),miembro1));
		Miembro miembro2 = new Miembro("joaco","bruno","joaketa@gmail.com",localizacion2);
		miembro2.setFormaNotificacion(new CuandoSuceden(miembro2));
		return List.of(miembro1,miembro2);
	}
}
