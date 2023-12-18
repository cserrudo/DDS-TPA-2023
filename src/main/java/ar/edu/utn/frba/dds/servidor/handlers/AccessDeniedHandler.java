package ar.edu.utn.frba.dds.servidor.handlers;

import ar.edu.utn.frba.dds.servidor.exceptions.SessionLoginException;
import io.javalin.Javalin;
import ar.edu.utn.frba.dds.servidor.exceptions.AccessDeniedException;

public class AccessDeniedHandler implements IHandler {

    @Override
    public void setHandle(Javalin app) {
        app.exception(AccessDeniedException.class, (e, context) -> {
            context.render("401.hbs");
        });
        app.exception(SessionLoginException.class,(e, context) -> {
            context.redirect("/login");;
        });
    }
}
