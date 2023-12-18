package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.TipoLocalizacion;
import ar.edu.utn.frba.dds.modelo.entidades.notificador.*;
import ar.edu.utn.frba.dds.modelo.entidades.notificador.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class NotificadorTest {
    @Test
    public void notificarPorMail() {
        Localizacion localizacion = new Localizacion("avellaneda", TipoLocalizacion.MUNICIPIO, new ArrayList<>());
        Miembro miembro = new Miembro("Juan", "Perez", "joaquinbruno67@gmail.com", localizacion);

        EstrategiaNotificacion estrategia = new NotificacionMail();
        Notificador notificador = new Notificador(estrategia);
        Notificacion notificacion = new Notificacion(miembro, "Test mensaje mail");

        notificador.notificar(notificacion);
    }

    @Test
    public void notificarPorWhatsapp() {
        Localizacion localizacion = new Localizacion("avellaneda", TipoLocalizacion.MUNICIPIO, new ArrayList<>());
        Miembro miembro = new Miembro("Juan", "Perez", "juan@example.com", localizacion);
        miembro.setCelular("5491133839905");

        EstrategiaNotificacion estrategia = new NotificacionWhatsapp();
        Notificador notificador = new Notificador(estrategia);
        Notificacion notificacion = new Notificacion(miembro, "Test mensaje wpp");

        notificador.notificar(notificacion);
    }

}
