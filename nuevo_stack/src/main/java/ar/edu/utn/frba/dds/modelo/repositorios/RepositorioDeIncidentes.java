package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioDeIncidentes implements WithSimplePersistenceUnit {
  private static RepositorioDeIncidentes repositorioDeIncidentes;

  public static RepositorioDeIncidentes getInstance() {
    if (repositorioDeIncidentes == null) {
      repositorioDeIncidentes = new RepositorioDeIncidentes();
    }
    return repositorioDeIncidentes;
  }

  public void agregar(Incidente unIncidente) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(unIncidente);
    tx.commit();
  }

  public void eliminar(Incidente unIncidente) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(unIncidente);
    tx.commit();
  }

  public void modificar(Incidente unIncidente) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(unIncidente);
    tx.commit();
  }

  public Incidente buscarPorId(Long id) {
    return entityManager().find(Incidente.class, id);
  }

  public List<Incidente> buscarTodos() {
    return entityManager().createQuery("from " + Incidente.class.getName()).getResultList();
  }
}
