package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class RepositorioDeEstablecimientos implements WithSimplePersistenceUnit {
  private static RepositorioDeEstablecimientos repositorioDeEstablecimientos;

  public static RepositorioDeEstablecimientos getInstance() {
    if (repositorioDeEstablecimientos == null) {
      repositorioDeEstablecimientos = new RepositorioDeEstablecimientos();
    }
    return repositorioDeEstablecimientos;
  }

  public void agregar(Establecimiento unEstablecimiento) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(unEstablecimiento);
    tx.commit();
  }

  public void eliminar(Establecimiento unEstablecimiento) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(unEstablecimiento);
    tx.commit();
  }

  public void modificar(Establecimiento unEstablecimiento) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(unEstablecimiento);
    tx.commit();
  }

  public Establecimiento buscarPorId(Long id) {
    return entityManager().find(Establecimiento.class, id);
  }

  public List<Establecimiento> buscarTodos() {
    return entityManager().createQuery("from " + Establecimiento.class.getName()).getResultList();
  }
}
