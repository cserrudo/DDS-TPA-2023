package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Entidad;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Establecimiento;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.Incidente;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;
import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;
import ar.edu.utn.frba.dds.modelo.entidades.usuarios.Usuario;
import ar.edu.utn.frba.dds.modelo.repositorios.*;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import ar.edu.utn.frba.dds.dtos.ComboDTO;
import ar.edu.utn.frba.dds.dtos.IncidenteComunidadDTO;
import ar.edu.utn.frba.dds.dtos.IncidenteFiltrosDTO;

public class IncidentesController extends Controller {
  private RepositorioDeIncidentesComunidad repositorioDeIncidentes;
  private RepositorioDeServicios repoServicios;
  private RepositorioDeEntidades repositorioDeEntidades;
  private RepositorioDeEstablecimientos repositorioDeEstablecimientos;
  private RepositorioDeComunidades repoComunidades;
  private RepositorioDeMiembros repositorioDeMiembros;

  public IncidentesController(RepositorioDeIncidentesComunidad repositorioDeIncidentes,
      RepositorioDeComunidades repositorioDeComunidades, RepositorioDeEntidades repositorioDeEntidades,
      RepositorioDeMiembros repositorioDeMiembros, RepositorioDeEstablecimientos repositorioDeEstablecimientos,
      RepositorioDeServicios repositorioDeServicios) {
    this.repositorioDeIncidentes = repositorioDeIncidentes;
    this.repoComunidades = repositorioDeComunidades;
    this.repoServicios = repositorioDeServicios;
    this.repositorioDeMiembros = repositorioDeMiembros;
    this.repositorioDeEstablecimientos = repositorioDeEstablecimientos;
    this.repositorioDeEntidades = repositorioDeEntidades;
  }

  public void index(Context context) throws Exception {
    String nombre = Optional.ofNullable(context.queryParam("nombre")).orElse("");
    String estado = context.queryParam("estado");
    String comunidadId = context.queryParam("comunidadId");
    String localizacionId = context.queryParam("localizacionId");
    String entidadId = context.queryParam("entidadId");
    String establecimientoId = context.queryParam("establecimientoId");
    String miembroAltaId = context.queryParam("miembroAltaId");
    String miembroBajaId = context.queryParam("miembroBajaId");

    Long id = context.sessionAttribute("id_miembro");
    Long idUsuario = context.sessionAttribute("id_usuario");
    Miembro miembroLogueado = id != 0 ? RepositorioDeMiembros.getInstance().buscarPorId(id) : null;
    Usuario usuario = miembroLogueado != null ? miembroLogueado.getUsuario()
        : RepositorioDeUsuarios.getInstance().buscarPorId(idUsuario);

    IncidenteFiltrosDTO filtros = new IncidenteFiltrosDTO(nombre,
        estado != null ? (estado.equals("abierto")) : null, comunidadId, localizacionId, entidadId, establecimientoId,
        miembroAltaId, miembroBajaId);

    Map<String, Object> model = new HashMap<>();

    List<IncidenteComunidadDTO> incidentes = this.repositorioDeIncidentes.buscarTodos(filtros).stream()
        .map(incidente -> new IncidenteComunidadDTO(incidente.getEntidad(), incidente.getEstablecimiento(),
            incidente.getServicio(),
            incidente.getIncidente().getObservaciones(), incidente.getComunidad(), incidente.getActivo(),
            incidente.getId()))
        .collect(Collectors.toList());

    List<ComboDTO> entidades = ComboController.getComboEntidades();
    List<ComboDTO> comunidades = ComboController.getComboComunidades();
    List<ComboDTO> localizaciones = ComboController.getComboLocalizaciones();
    List<ComboDTO> establecimientos = ComboController.getComboEstablecimientos();
    List<ComboDTO> miembros = ComboController.getComboMiembros();
    List<ComboDTO> servicios = ComboController.getComboServicios();

    model.put("incidentes", incidentes);
    model.put("entidades", entidades);
    model.put("comunidades", comunidades);
    model.put("localizaciones", localizaciones);
    model.put("establecimientos", establecimientos);
    model.put("miembros", miembros);
    model.put("servicios", servicios);
    model.put("usuario", usuario);

    context.render("Incidentes.hbs", model);
  }

  public void reportar(Context context) {
    String servicioId = context.formParam("servicioId");
    Servicio servicio = repoServicios.buscarPorId(Long.parseLong(servicioId));

    String entidadId = context.formParam("entidadId");
    Entidad entidad = repositorioDeEntidades.buscarPorId(Long.parseLong(entidadId));

    String establecimientoId = context.formParam("establecimientoId");
    Establecimiento establecimiento = repositorioDeEstablecimientos.buscarPorId(Long.parseLong(establecimientoId));

    Long miembroId = context.sessionAttribute("id_miembro");
    Miembro miembro = RepositorioDeMiembros.getInstance().buscarPorId(miembroId);

    List<Comunidad> comunidadesDelMiembro = repositorioDeMiembros
        .obtenerComunidadesPorMiembroId(Long.valueOf(miembroId));

    comunidadesDelMiembro.forEach(comu -> {
      Incidente incidente = new Incidente(servicio, context.formParam("observaciones"), miembro);
      IncidenteComunidad incidenteComunidad = new IncidenteComunidad(incidente,
          comu);
      incidenteComunidad.setActivo(true);
      incidenteComunidad.setEntidad(entidad);
      incidenteComunidad.setEstablecimiento(establecimiento);
      incidenteComunidad.setServicio(servicio);
      repositorioDeIncidentes.agregar(incidenteComunidad);
      ;
    });

    // Lo seteo porque desde la base no levanta el servicio id, no debe estar
    // llegando bien desde incidente

    context.redirect("/incidentes");
  }

  // El incidente deberia cerrarse en todas las comunidades del miembro
  public void cerrar(Context context) {
    Long miembroId = context.sessionAttribute("id_miembro");
    Miembro miembro = RepositorioDeMiembros.getInstance().buscarPorId(miembroId);
    String idIncidente = context.pathParam("id");
    IncidenteComunidad aEliminar = repositorioDeIncidentes.buscarPorId(Long.valueOf(idIncidente));
    // aEliminar.setActivo(false);
    aEliminar.cerrarIncidente(miembro);
    // aEliminar.setFechaHoraCierre();
    repositorioDeIncidentes.modificar(aEliminar);
    context.redirect("/incidentes");
  }

}