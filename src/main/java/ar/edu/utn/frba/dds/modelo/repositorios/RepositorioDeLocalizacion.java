package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.Localizacion;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioDeLocalizacion implements WithSimplePersistenceUnit {

  private static RepositorioDeLocalizacion repositorioDeLocalizacion;

  public static RepositorioDeLocalizacion getInstance() {
    if (repositorioDeLocalizacion == null) {
      repositorioDeLocalizacion = new RepositorioDeLocalizacion();
    }
    return repositorioDeLocalizacion;
  }

  public void agregar(Localizacion unaLocalizacion) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(unaLocalizacion);
    tx.commit();
  }

  public void eliminar(Localizacion unaLocalizacion) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(unaLocalizacion);
    tx.commit();
  }

  public void modificar(Localizacion unaLocalizacion) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(unaLocalizacion);
    tx.commit();
  }

  public Localizacion buscarPorId(Long id) {
    return entityManager().find(Localizacion.class, id);
  }

  public List<Localizacion> buscarTodos() {
    return entityManager().createQuery("from " + Localizacion.class.getName()).getResultList();
  }
}
