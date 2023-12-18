package ar.edu.utn.frba.dds.main;

import java.util.ArrayList;
import java.util.List;
import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Entidad;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Establecimiento;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Ubicacion;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.Incidente;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;
import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;
import ar.edu.utn.frba.dds.modelo.entidades.usuarios.Rol;
import ar.edu.utn.frba.dds.modelo.entidades.usuarios.TipoRol;
import ar.edu.utn.frba.dds.modelo.entidades.usuarios.Usuario;
import ar.edu.utn.frba.dds.modelo.repositorios.*;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

public class MainExample implements WithSimplePersistenceUnit {

  public static void main(String[] args) {
    /*
     * Localizacion localizacion =
     * RepositorioDeLocalizacion.getInstance().buscarPorId(1);
     * 
     * Comunidad comunidad1 = RepositorioDeComunidades.getInstance().buscarPorId(1);
     * Servicio ascensor = RepositorioDeServicios.getInstance().buscarPorId(1);
     * Miembro miembro = RepositorioDeMiembros.getInstance().buscarPorId(1);
     * 
     * miembro.altaComunidad(comunidad1);
     * 
     * Servicio banio = new Servicio("Baño", new Ubicacion());
     * List<Servicio> servicios = new ArrayList<Servicio>();
     * servicios.add(ascensor);
     * servicios.add(banio);
     * 
     * List<Establecimiento> establecimientos = new ArrayList<Establecimiento>();
     * Establecimiento primeraJunta = new Establecimiento("Primera Junta", new
     * Ubicacion(3.42391, -41.16461), servicios);
     * 
     * Entidad entidad = new Entidad("Linea A", establecimientos, localizacion);
     * 
     * Incidente incidente = new Incidente(ascensor, "Fuera de servicio", miembro);
     * 
     * IncidenteComunidad incidenteComunidad = new IncidenteComunidad(incidente,
     * comunidad1);
     * 
     * RepositorioDeServicios.getInstance().agregar(ascensor);
     * RepositorioDeServicios.getInstance().agregar(banio);
     * 
     * RepositorioDeEstablecimientos.getInstance().agregar(primeraJunta);
     * 
     * RepositorioDeEntidades.getInstance().agregar(entidad);
     * 
     * RepositorioDeIncidentes.getInstance().agregar(incidente);
     * 
     * RepositorioDeIncidentesComunidad.getInstance().agregar(incidenteComunidad);
     */

    // Localizacion localizacion =
    // RepositorioDeLocalizacion.getInstance().buscarPorId(Long.parseLong("1"));
    // Usuario usuario =
    // RepositorioDeUsuarios.getInstance().buscarPorId(Long.parseLong("2"));

    // Miembro miembro = new Miembro("Gri", "Test", "abc@123.com", localizacion,
    // usuario);

    // RepositorioDeMiembros.getInstance().agregar(miembro);

    // Rol normal = new Rol();
    // normal.setNombre("normal");
    // normal.setTipo(TipoRol.NORMAL);

    // RepositorioDeRoles.getInstance().agregar(normal);

    // Rol adm = new Rol();
    // adm.setNombre("Administrador");
    // adm.setTipo(TipoRol.ADMINISTRADOR);

    // RepositorioDeRoles.getInstance().agregar(adm);

    // Usuario usuario1 = new Usuario("cloyola", "Cavan14613@dg212");
    // usuario1.setRol(adm);

    // RepositorioDeUsuarios.getInstance().agregar(usuario1);
    // Usuario usuario2 = new Usuario("cloyola2", "Cpadgfan1463213@dg21632");
    // usuario1.setRol(normal);
    // RepositorioDeUsuarios.getInstance().agregar(usuario2);

    // RepositorioDeUsuarios repositorioDeUsuarios = new RepositorioDeUsuarios();
    // Usuario usuario = repositorioDeUsuarios.buscarPorNombre("cloyola");

    // System.out.print(usuario.getUsername());
    // System.out.print(usuario.getPassword());

  }

}
