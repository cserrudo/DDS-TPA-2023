package ar.edu.utn.frba.dds.servidor.init;

import ar.edu.utn.frba.dds.modelo.entidades.usuarios.Rol;
import ar.edu.utn.frba.dds.modelo.entidades.usuarios.TipoRol;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

public class Initializer implements WithSimplePersistenceUnit {

    public static void init() {
        new Initializer()
                .iniciarTransaccion()
                .roles()
                .commitearTransaccion();
    }

    private Initializer iniciarTransaccion() {
        entityManager().getTransaction().begin();
        return this;
    }

    private Initializer commitearTransaccion() {
        entityManager().getTransaction().commit();
        return this;
    }

    private Initializer roles() {

        Rol administrador = new Rol();
        administrador.setNombre("Administrador");
        administrador.setTipo(TipoRol.ADMINISTRADOR);

        Rol institucion = new Rol();
        institucion.setNombre("Institucion");
        institucion.setTipo(TipoRol.NORMAL);

        return this;
    }
}
