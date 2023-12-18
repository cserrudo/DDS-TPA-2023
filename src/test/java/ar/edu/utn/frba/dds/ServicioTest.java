package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Ubicacion;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.Incidente;
import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.TipoLocalizacion;
import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ServicioTest {

    @Test
    public void servicioHabilitado() {
        Servicio servicio = new Servicio("Ascensor", new Ubicacion(3.42391, -41.16461));
        Miembro miembro = ComunidadTest.mockMiembroConComunidad();
		miembro.setCelular("5491133839905");

        Assertions.assertEquals(servicio.estaHabilitado(miembro), true);

        Incidente incidente = new Incidente(servicio, "Fuera de servicio", miembro);
        Assertions.assertEquals(incidente.getServicio(), servicio);
        Assertions.assertEquals(servicio.estaHabilitado(miembro), false);
    }

    @Test
    public void servicioDeshabilitado() {
        Servicio servicio = new Servicio("Ascensor", new Ubicacion(3.42391, -41.16461));
        Miembro miembro = ComunidadTest.mockMiembroConComunidad();
		miembro.setCelular("5491133839905");
        Assertions.assertEquals(servicio.estaHabilitado(miembro), true);

        Incidente incidente = new Incidente(servicio, "Fuera de servicio", miembro);
        incidente.darDeBaja(miembro);

        Assertions.assertEquals(servicio.estaHabilitado(miembro), true);
    }

   /* @Test
    public void altaMiembroDistintoBaja() {
        Localizacion localizacion1 = new Localizacion("avellaneda", TipoLocalizacion.MUNICIPIO, new ArrayList<>());
        Localizacion localizacion2 = new Localizacion("quilmes", TipoLocalizacion.MUNICIPIO, new ArrayList<>());
        Miembro miembro1 = new Miembro("Juan", "Perez", "juan@example.com", localizacion1);
        Miembro miembro2 = new Miembro("Pepe", "Lopez", "pepe@example.com", localizacion2);
		miembro1.setCelular("5491133839905");
		miembro2.setCelular("5491168492404");
        Comunidad comunidad1 = new Comunidad("comunidad1");
        miembro1.altaComunidad(comunidad1);

        Comunidad comunidad2 = new Comunidad("comunidad2");
        miembro2.altaComunidad(comunidad2);

        Comunidad comunidad3 = new Comunidad("comunidad3");
        miembro1.altaComunidad(comunidad3);
        miembro2.altaComunidad(comunidad3);

        Servicio servicio = new Servicio("Ascensor", new Ubicacion(3.42391, -41.16461));

        Assertions.assertEquals(servicio.estaHabilitado(miembro1), true);
        Assertions.assertEquals(servicio.estaHabilitado(miembro2), true);

        Incidente incidente = new Incidente(servicio, "Fuera de servicio", miembro1);
        incidente.darDeBaja(miembro2);

        // El servicio puede estar deshabilitado para un miembro parte de una comunidad con un Incidente activo
        // Pero puede estar habilitado para un miembro parte de una comunidad sin un Incidente activo para ese Servicio
        Assertions.assertEquals(servicio.estaHabilitado(miembro1), false);
        Assertions.assertEquals(servicio.estaHabilitado(miembro2), true);
    }*/
}
