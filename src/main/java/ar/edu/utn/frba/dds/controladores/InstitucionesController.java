package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.EntidadPrestadora;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.LectorCsv;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.OrganismoDeControl;
import ar.edu.utn.frba.dds.modelo.entidades.usuarios.Usuario;
import io.javalin.http.Context;

import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDeEntidadesPrestadoras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDeOrganismosDeControl;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDeUsuarios;
import io.javalin.http.UploadedFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InstitucionesController extends Controller {
  private RepositorioDeEntidadesPrestadoras repositorioDeEntidades;
  private RepositorioDeOrganismosDeControl repositorioDeOrganismos;

  public InstitucionesController(RepositorioDeEntidadesPrestadoras repositorioDeEntidades,
      RepositorioDeOrganismosDeControl repositorioDeOrganismos) {
    this.repositorioDeEntidades = repositorioDeEntidades;
    this.repositorioDeOrganismos = repositorioDeOrganismos;
  }

  public void index(Context context) throws Exception {
    Long id = context.sessionAttribute("id_usuario");
    Usuario usuario = RepositorioDeUsuarios.getInstance().buscarPorId(id);
    Map<String, Object> model = new HashMap<>();
    model.put("usuario", usuario);

    context.render("Instituciones.hbs", model);
  }

  public void importarEntidadesPrestadoras(Context context) {
    UploadedFile uploadedFile = context.uploadedFile("file");

    List<EntidadPrestadora> entidades = new LectorCsv().obtenerEntidadesPrestadoresCSV(uploadedFile.content())
        .stream()
        .collect(Collectors.toList());

    entidades.forEach(entidad -> {
      repositorioDeEntidades.agregar(entidad);
    });

    context.redirect("/instituciones");
  }

  public void importarOrganismosDeControl(Context context) {
    UploadedFile uploadedFile = context.uploadedFile("file");

    List<OrganismoDeControl> organismosDTO = new LectorCsv().obtenerOrganismoDeControlCSV(uploadedFile.content())
        .stream().collect(Collectors.toList());

    organismosDTO.forEach(organismo -> {
      repositorioDeOrganismos.agregar(organismo);
    });

    context.redirect("/instituciones");
  }
}