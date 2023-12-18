package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class RepositorioDeServicios implements WithSimplePersistenceUnit {
  private static RepositorioDeServicios repositorioDeServicios;

  public static RepositorioDeServicios getInstance() {
    if (repositorioDeServicios == null) {
      repositorioDeServicios = new RepositorioDeServicios();
    }
    return repositorioDeServicios;
  }

  public void agregar(Servicio unServicio) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(unServicio);
    tx.commit();
  }

  public void eliminar(Servicio unServicio) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(unServicio);
    tx.commit();
  }

  public void modificar(Servicio unServicio) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(unServicio);
    tx.commit();
  }

  public Servicio buscarPorId(Long id) {
    return entityManager().find(Servicio.class, id);
  }

  public List<Servicio> buscarTodos() {
    return entityManager().createQuery("from " + Servicio.class.getName()).getResultList();
  }
}
