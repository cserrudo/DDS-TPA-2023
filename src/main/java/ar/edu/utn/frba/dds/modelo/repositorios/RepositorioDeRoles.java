package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.usuarios.Rol;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioDeRoles implements WithSimplePersistenceUnit {
  private static RepositorioDeRoles repositorioDeRoles;

  public static RepositorioDeRoles getInstance() {
    if (repositorioDeRoles == null) {
      repositorioDeRoles = new RepositorioDeRoles();
    }
    return repositorioDeRoles;
  }

  public void agregar(Rol unRol) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(unRol);
    tx.commit();
  }

  public void eliminar(Rol unRol) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(unRol);
    tx.commit();
  }

  public void modificar(Rol unRol) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(unRol);
    tx.commit();
  }

  public Rol buscarPorId(Integer id) {
    return entityManager().find(Rol.class, id.longValue());
  }


  public List<Rol> buscarTodos() {
    return entityManager().createQuery("from " + Rol.class.getName()).getResultList();
  }
}

