package ar.edu.utn.frba.dds;

import java.util.ArrayList;

import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Ubicacion;

import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.Incidente;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;
import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.TipoLocalizacion;
import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;

public class IncidenteTest {
	
	@Test
	public void esIncidenteDuplicado(){
		Localizacion localizacion = new Localizacion("avellaneda", TipoLocalizacion.MUNICIPIO, new ArrayList<>());
		Miembro miembro = new Miembro("Juan", "Perez", "juan@example.com", localizacion);
		miembro.setCelular("5491168492404");
		Comunidad comunidad = new Comunidad("comunidad");
		Comunidad comunidadNuevos = new Comunidad("comunidadDeNuevos");
		miembro.altaComunidad(comunidad);
		Servicio servicio = new Servicio("Ascensor", new Ubicacion(3.42391, -41.16461));
		Incidente incidente = new Incidente(servicio, "Fuera de servicio", miembro);
		IncidenteComunidad incidenteComunidad = new IncidenteComunidad(incidente,comunidad);
		IncidenteComunidad noDuplicadoIncidente = new IncidenteComunidad(incidente,comunidadNuevos);
		Assertions.assertEquals(incidente.agregarIncidenteComunidad(incidenteComunidad),"Ese incidente ya fue declarado");
		Assertions.assertEquals(incidente.agregarIncidenteComunidad(noDuplicadoIncidente),"El incidente fue agregado correctamente");
		
	}
}
