package ar.edu.utn.frba.dds.controladores;

import java.util.HashMap;
import java.util.Map;

import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.usuarios.TipoRol;
import ar.edu.utn.frba.dds.modelo.entidades.usuarios.Usuario;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDeMiembros;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDeUsuarios;
import io.javalin.http.Context;

public class LoginController extends Controller {
  private RepositorioDeUsuarios repositorioDeUsuarios;

  public LoginController(RepositorioDeUsuarios repositorioDeUsuarios) {
    this.repositorioDeUsuarios = repositorioDeUsuarios;
  }

  public void index(Context ctx) {
    Map<String, Object> model = new HashMap<>();
    ctx.render("Login.hbs", model);
  }

  public void iniciarSesion(Context ctx) {
    Usuario usuario;
    String password;
    String rol;
    try {
      usuario = this.repositorioDeUsuarios.buscarPorNombre(ctx.formParam("email"));
      password = usuario.getPassword().getPassword();
      rol = usuario.getRol().getTipo().name();
      if (ctx.formParam("password").equals(password)) {
        Long miembroId = rol.equals(TipoRol.NORMAL.name())
            ? RepositorioDeMiembros.getInstance().buscarPorUsuarioId(usuario.getId()).getId()
            : Long.parseLong("0");

        ctx.sessionAttribute("logged-in", true);
        ctx.sessionAttribute("id_usuario", usuario.getId());
        ctx.sessionAttribute("id_miembro", miembroId);

        ctx.redirect(rol.equals(TipoRol.ADMINISTRADOR.name()) ? "/incidentes" : "/");
      } else {
        Map<String, Object> model = new HashMap<>();
        model.put("error", "Usuario o contraseña incorrectos");
        ctx.render("Login.hbs", model);
      }
    } catch (Exception e) {
      Map<String, Object> model = new HashMap<>();
      model.put("error", "Usuario o contraseña incorrectos");
      ctx.render("Login.hbs", model);
    }
  }

  /*
   * public void handleLogin(Context context){
   * this.iniciarSesion(context);
   * }
   */
}
