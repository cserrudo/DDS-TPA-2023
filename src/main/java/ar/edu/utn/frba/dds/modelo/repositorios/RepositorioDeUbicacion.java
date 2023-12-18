package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Ubicacion;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioDeUbicacion implements WithSimplePersistenceUnit {
  private static RepositorioDeUbicacion repositorioDeUbicacion;

  public static RepositorioDeUbicacion getInstance() {
    if (repositorioDeUbicacion == null) {
      repositorioDeUbicacion = new RepositorioDeUbicacion();
    }
    return repositorioDeUbicacion;
  }

  public void agregar(Ubicacion unaUbicacion) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(unaUbicacion);
    tx.commit();
  }

  public void eliminar(Ubicacion unaUbicacion) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(unaUbicacion);
    tx.commit();
  }

  public void modificar(Ubicacion unaUbicacion) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(unaUbicacion);
    tx.commit();
  }

  public Ubicacion buscarPorId(Long id) {
    return entityManager().find(Ubicacion.class, id);
  }

  public List<Ubicacion> buscarTodos() {
    return entityManager().createQuery("from " + Ubicacion.class.getName()).getResultList();
  }
}
