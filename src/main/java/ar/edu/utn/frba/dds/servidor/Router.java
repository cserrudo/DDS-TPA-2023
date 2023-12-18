package ar.edu.utn.frba.dds.servidor;

import static io.javalin.apibuilder.ApiBuilder.*;

import ar.edu.utn.frba.dds.controladores.FactoryController;
import ar.edu.utn.frba.dds.controladores.IncidentesController;
import ar.edu.utn.frba.dds.controladores.InstitucionesController;
import ar.edu.utn.frba.dds.controladores.LoginController;
import ar.edu.utn.frba.dds.controladores.RankingsController;
import ar.edu.utn.frba.dds.controladores.ComunidadesController;
import ar.edu.utn.frba.dds.controladores.EntidadesController;

public class Router {
	public static void init() {
		Server.app().routes(() -> {
			get("/", ((EntidadesController) FactoryController.controller("Entidades"))::index);

			get("/login", ((LoginController) FactoryController.controller("Login"))::index);
			post("/login", ((LoginController) FactoryController.controller("Login"))::iniciarSesion);

			get("/comunidades", ((ComunidadesController) FactoryController.controller("Comunidades"))::index);
			post("/comunidades/{id}/alta-miembro",
					((ComunidadesController) FactoryController.controller("Comunidades"))::altaMiembro);
			post("/comunidades/{id}/baja-miembro",
					((ComunidadesController) FactoryController.controller("Comunidades"))::bajaMiembro);
			post("/comunidades/{id}/toggle-es-observador",
					((ComunidadesController) FactoryController.controller("Comunidades"))::miembroEsObservador);

			get("/incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::index);
			post("/incidentes/reportar", ((IncidentesController) FactoryController.controller("Incidentes"))::reportar);
			post("/incidentes/{id}/cerrar", ((IncidentesController) FactoryController.controller("Incidentes"))::cerrar);

			get("/rankings", ((RankingsController) FactoryController.controller("Rankings"))::index);

			get("/instituciones", ((InstitucionesController) FactoryController.controller("Instituciones"))::index);
			post("/instituciones/importar-entidades-prestadoras",
					((InstitucionesController) FactoryController.controller("Instituciones"))::importarEntidadesPrestadoras);
			post("/instituciones/importar-organismos-de-control",
					((InstitucionesController) FactoryController.controller("Instituciones"))::importarOrganismosDeControl);
		});
	}
}
