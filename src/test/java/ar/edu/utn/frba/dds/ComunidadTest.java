package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.TipoLocalizacion;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Entidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class ComunidadTest {
    public static Miembro mockMiembroConComunidad() {
        Localizacion localizacion1 = new Localizacion("avellaneda", TipoLocalizacion.MUNICIPIO, new ArrayList<>());
        Miembro miembro = new Miembro("Juan", "Perez", "juan@example.com", localizacion1);

        Comunidad comunidad = new Comunidad("comunidad");
        miembro.altaComunidad(comunidad);

        return miembro;
    }

    @Test
    public void cantidadMiembrosComunidad() {
        Localizacion localizacion1 = new Localizacion("avellaneda", TipoLocalizacion.MUNICIPIO, new ArrayList<>());
        Localizacion localizacion2 = new Localizacion("quilmes", TipoLocalizacion.MUNICIPIO, new ArrayList<>());
        Miembro miembro1 = new Miembro("Juan", "Perez", "juan@example.com", localizacion1);
        Miembro miembro2 = new Miembro("Pepe", "Lopez", "pepe@example.com", localizacion2);

        Comunidad comunidad = new Comunidad("comunidad");
        miembro1.altaComunidad(comunidad);
        miembro2.altaComunidad(comunidad);

        Assertions.assertEquals(comunidad.cantMiembros(), 2);
        Assertions.assertEquals(comunidad.perteneceALaComunidad(miembro1), true);
        Assertions.assertEquals(comunidad.perteneceALaComunidad(miembro2), true);
    }

    @Test
    public void altaYBajaComunidad() {
        Localizacion localizacion1 = new Localizacion("avellaneda", TipoLocalizacion.MUNICIPIO, new ArrayList<>());
        Localizacion localizacion2 = new Localizacion("quilmes", TipoLocalizacion.MUNICIPIO, new ArrayList<>());
        Miembro miembro1 = new Miembro("Juan", "Perez", "juan@example.com", localizacion1);
        Miembro miembro2 = new Miembro("Pepe", "Lopez", "pepe@example.com", localizacion2);

        Comunidad comunidad = new Comunidad("comunidad");
        Assertions.assertEquals(comunidad.cantMiembros(), 0);

        miembro1.altaComunidad(comunidad);
        miembro2.altaComunidad(comunidad);

        Assertions.assertEquals(comunidad.cantMiembros(), 2);

        comunidad.bajaMiembro(miembro2);

        Assertions.assertEquals(comunidad.cantMiembros(), 1);
        Assertions.assertEquals(comunidad.perteneceALaComunidad(miembro2), false);
        Assertions.assertEquals(comunidad.perteneceALaComunidad(miembro1), true);
    }

    @Test
    public void miembrosConLaMismaLocalizacion() {
        Localizacion localizacion1 = new Localizacion("quilmes", TipoLocalizacion.MUNICIPIO, new ArrayList<>());
        Localizacion localizacion2 = new Localizacion("quilmes", TipoLocalizacion.MUNICIPIO, new ArrayList<>());
        Miembro miembro1 = new Miembro("Juan", "Perez", "juan@example.com", localizacion1);
        Miembro miembro2 = new Miembro("Pepe", "Lopez", "pepe@example.com", localizacion2);
        Assertions.assertEquals(miembro1.getLocalizacion().getNombre(), miembro2.getLocalizacion().getNombre());
    }

    @Test
    public void miembrosDeOtroPartido() {
        Localizacion localizacion1 = new Localizacion("quilmes", TipoLocalizacion.MUNICIPIO, new ArrayList<Entidad>());
        Localizacion localizacion2 = new Localizacion("lanus", TipoLocalizacion.MUNICIPIO, new ArrayList<Entidad>());
        Miembro miembro1 = new Miembro("Juan", "Perez", "juan@example.com", localizacion1);
        Miembro miembro2 = new Miembro("Pepe", "Lopez", "pepe@example.com", localizacion2);
        Assertions.assertNotEquals(miembro1.getLocalizacion().getNombre(), miembro2.getLocalizacion().getNombre());
  }

}
