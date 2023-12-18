package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.repositorios.*;

public class FactoryController {

    private static final RepositorioDeUsuarios repositorioDeUsuarios = new RepositorioDeUsuarios();
    private static final RepositorioDeEntidades repositorioDeEntidades = new RepositorioDeEntidades();
    private static final RepositorioDeComunidades repositorioDeComunidades = new RepositorioDeComunidades();
    private static final RepositorioDeIncidentesComunidad repositorioDeIncidentesComunidad = new RepositorioDeIncidentesComunidad();
    private static final RepositorioDeEntidadesPrestadoras repositorioDeEntidadesPrestadoras = new RepositorioDeEntidadesPrestadoras();
    private static final RepositorioDeOrganismosDeControl repositorioDeOrganismosDeControl = new RepositorioDeOrganismosDeControl();
    private static final RepositorioDeEstablecimientos repositorioDeEstablecimientos = new RepositorioDeEstablecimientos();
    private static final RepositorioDeServicios repositorioDeServicios = new RepositorioDeServicios();
    private static final RepositorioDeMiembros repositorioDeMiembros = new RepositorioDeMiembros();
    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "Login":
                controller = new LoginController(repositorioDeUsuarios);
                break;
            case "Entidades":
                controller = new EntidadesController(repositorioDeEntidades);
                break;
            case "Comunidades":
                controller = new ComunidadesController(repositorioDeComunidades);
                break;
            case "Incidentes":
                controller = new IncidentesController(repositorioDeIncidentesComunidad,repositorioDeComunidades,repositorioDeEntidades,repositorioDeMiembros,repositorioDeEstablecimientos,repositorioDeServicios);
                break;
            case "Instituciones":
                controller = new InstitucionesController(repositorioDeEntidadesPrestadoras,repositorioDeOrganismosDeControl);
                break;
            case "Rankings":
                controller = new RankingsController(repositorioDeComunidades,repositorioDeEntidades);
                break;
        }
        return controller;
    }
}