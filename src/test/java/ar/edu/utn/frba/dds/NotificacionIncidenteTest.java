package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Ubicacion;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.Incidente;
import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.TipoLocalizacion;
import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.CuandoSuceden;
import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.Horario;
import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.NotificacionIncidente;
import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.SinApuros;
import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.*;
import ar.edu.utn.frba.dds.modelo.entidades.notificador.EstrategiaNotificacion;
import ar.edu.utn.frba.dds.modelo.entidades.notificador.NotificacionWhatsapp;
import ar.edu.utn.frba.dds.modelo.entidades.notificador.Notificador;
import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class NotificacionIncidenteTest {
    @Test
    public void notificarCuandoSuceden() {
        Localizacion localizacion = new Localizacion("avellaneda", TipoLocalizacion.MUNICIPIO, new ArrayList<>());
        Miembro miembro = new Miembro("Juan", "Perez", "juan@example.com", localizacion);

        EstrategiaNotificacion medioNotificacion = new NotificacionWhatsapp();
        NotificacionIncidente formaNotificacion = new CuandoSuceden(miembro);
		Notificador notificador = new Notificador(medioNotificacion);

        miembro.setFormaNotificacion(formaNotificacion);
        miembro.setNotificador(notificador);
        miembro.setCelular("5491165820111");

        Comunidad comunidad = new Comunidad("comunidad");
        miembro.altaComunidad(comunidad);

        Servicio servicio = new Servicio("Ascensor", new Ubicacion(3.42391, -41.16461));
        Incidente incidente = new Incidente(servicio, "Fuera de servicio", miembro);

        incidente.darDeBaja(miembro);
    }

    @Test
    public void notificarSinApuros() {
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
		miembro.setCelular("5491168492404");
		
		Comunidad comunidad = new Comunidad("comunidad");
		miembro.altaComunidad(comunidad);
		
		Servicio servicio = new Servicio("Ascensor", new Ubicacion(3.42391, -41.16461));
		Incidente incidente = new Incidente(servicio, "Fuera de servicio", miembro);
		Incidente incidente2 = new Incidente(servicio, "Fuera de servicio 2", miembro);
		
		incidente.darDeBaja(miembro);
		incidente2.darDeBaja(miembro);
    }
}
