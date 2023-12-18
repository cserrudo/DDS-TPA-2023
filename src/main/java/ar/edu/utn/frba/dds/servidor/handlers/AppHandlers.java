package ar.edu.utn.frba.dds.servidor.handlers;

import io.javalin.Javalin;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AppHandlers {
    private IHandler[] handlers = new IHandler[]{
            new AccessDeniedHandler(),
    };

    public static void applyHandlers(Javalin app) {
        Arrays.stream(new AppHandlers().handlers).collect(Collectors.toList()).forEach(handler -> handler.setHandle(app));
    }
}
