package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.Horario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioDeHorariosNotificacion implements WithSimplePersistenceUnit {
  private static RepositorioDeHorariosNotificacion repositorioDeHorariosNotificacion;

  public static RepositorioDeHorariosNotificacion getInstance() {
    if (repositorioDeHorariosNotificacion == null) {
      repositorioDeHorariosNotificacion = new RepositorioDeHorariosNotificacion();
    }
    return repositorioDeHorariosNotificacion;
  }

  public void agregar(Horario unHorario) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(unHorario);
    tx.commit();
  }

  public void eliminar(Horario unHorario) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(unHorario);
    tx.commit();
  }

  public void modificar(Horario unHorario) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(unHorario);
    tx.commit();
  }

  public Horario buscarPorId(Long id) {
    return entityManager().find(Horario.class, id);
  }

  public List<Horario> buscarTodos() {
    return entityManager().createQuery("from " + Horario.class.getName()).getResultList();
  }
}
