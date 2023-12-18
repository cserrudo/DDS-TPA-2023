package ar.edu.utn.frba.dds.servidor.handlers;

import io.javalin.Javalin;

public interface IHandler {
    void setHandle(Javalin app);
}
