package ar.edu.utn.frba.dds.servidor.middlewares;

import ar.edu.utn.frba.dds.controladores.LoginController;
import ar.edu.utn.frba.dds.modelo.entidades.usuarios.TipoRol;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDeUsuarios;
import ar.edu.utn.frba.dds.servidor.exceptions.AccessDeniedException;
import ar.edu.utn.frba.dds.servidor.exceptions.SessionLoginException;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class AuthMiddleware {
    public static void apply(JavalinConfig config) {
        config.accessManager(((handler, context, routeRoles) -> {
            // TipoRol userRole = getUserRoleType(context);
            if (context.sessionAttribute("id_usuario") == null && !context.path().equals("/login")) {
                throw new SessionLoginException("No existe el usuario ingresado");

            } else if (context.method().equals(HandlerType.POST) && context.path().equals("/login")) {
                // Si es una solicitud POST a /login, llama al controlador para manejar las
                // credenciales
                new LoginController(new RepositorioDeUsuarios()).iniciarSesion(context);
            } /*
               * else if (routeRoles.size() == 0 || routeRoles.contains(userRole)) {
               * // El usuario tiene el rol necesario o no se requiere un rol para esta ruta
               * handler.handle(context);
               */
            /* } else { */
            // El usuario no tiene el rol necesario
            // }
            handler.handle(context);
        }));

    }

    private static TipoRol getUserRoleType(Context context) {
        return context.sessionAttribute("tipo_rol") != null ? TipoRol.valueOf(context.sessionAttribute("tipo_rol"))
                : null;
    }
}
