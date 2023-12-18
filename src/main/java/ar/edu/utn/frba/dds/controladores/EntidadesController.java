package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import io.javalin.http.Context;

import java.util.*;
import java.util.stream.Collectors;

import ar.edu.utn.frba.dds.dtos.ComboDTO;
import ar.edu.utn.frba.dds.dtos.EntidadDTO;
import ar.edu.utn.frba.dds.dtos.EntidadFiltrosDTO;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDeEntidades;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDeMiembros;

public class EntidadesController extends Controller {
  private RepositorioDeEntidades repositorioDeEntidades;

  public EntidadesController(RepositorioDeEntidades repositorioDeEntidades) {
    this.repositorioDeEntidades = repositorioDeEntidades;
  }

  public void index(Context context) throws Exception {
    Long id = context.sessionAttribute("id_miembro");
    Miembro miembroLogueado = RepositorioDeMiembros.getInstance().buscarPorId(id);

    String nombre = Optional.ofNullable(context.queryParam("nombre")).orElse("");
    String localizacionId = context.queryParam("localizacionId");
    EntidadFiltrosDTO filtros = new EntidadFiltrosDTO(nombre, null, null, null, localizacionId);

    Map<String, Object> model = new HashMap<>();

    List<ComboDTO> localizaciones = ComboController.getComboLocalizaciones();

    List<EntidadDTO> entidades = this.repositorioDeEntidades.buscarTodos(filtros).stream()
        .map(entidad -> new EntidadDTO(entidad.getId(), entidad.getDescripcion(),
            entidad.getTipo(),
            entidad.getIncidentesActivos()))
        .collect(Collectors.toList());

    model.put("entidades", entidades);
    model.put("localizaciones", localizaciones);
    model.put("usuario", miembroLogueado.getUsuario());

    context.render("Inicio.hbs", model);
  }
}