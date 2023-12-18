package ar.edu.utn.frba.dds.modelo.entidades.usuarios;

import io.javalin.security.RouteRole;

public enum TipoRol implements RouteRole {
    ADMINISTRADOR,
    NORMAL,
}
