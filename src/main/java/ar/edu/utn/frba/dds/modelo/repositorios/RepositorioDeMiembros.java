package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.util.List;

public class RepositorioDeMiembros implements WithSimplePersistenceUnit {

  private static RepositorioDeMiembros repositorioDeMiembros;

  public static RepositorioDeMiembros getInstance() {
    if (repositorioDeMiembros == null) {
      repositorioDeMiembros = new RepositorioDeMiembros();
    }
    return repositorioDeMiembros;
  }

  public void agregar(Miembro unMiembro) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(unMiembro);
    tx.commit();
  }

  public void eliminar(Miembro unMiembro) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(unMiembro);
    tx.commit();
  }

  public void modificar(Miembro unMiembro) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(unMiembro);
    tx.commit();
  }

  public Miembro buscarPorId(Long id) {
    return entityManager().find(Miembro.class, id);
  }

  public Miembro buscarPorUsuarioId(Long id) {
    String jpql = "SELECT m FROM Miembro m WHERE m.usuario.id = :usuarioId";
    TypedQuery<Miembro> query = entityManager().createQuery(jpql, Miembro.class);
    query.setParameter("usuarioId", id);
    return query.getSingleResult();
  }

  public List<Miembro> buscarTodos() {
    return entityManager().createQuery("from " + Miembro.class.getName()).getResultList();
  }

  public List<Comunidad> obtenerComunidadesPorMiembroId(Long miembroId) {
    List<Comunidad> resultado = entityManager().createQuery(
        "SELECT mc.comunidad FROM Miembro m " +
            "LEFT JOIN MiembroxComunidad mc ON mc.miembro.id = m.id " +
            "LEFT JOIN Comunidad c ON c.id = mc.comunidad.id " +
            "WHERE m.id = :miembroId",
        Comunidad.class)
        .setParameter("miembroId", miembroId)
        .getResultList();

    return resultado;
  }
}
