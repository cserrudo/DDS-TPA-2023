package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.MiembroxComunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class RepositorioDeMiembrosXComunidad implements WithSimplePersistenceUnit {
  private static RepositorioDeMiembrosXComunidad repositorioDeMiembrosxComunidad;

  public static RepositorioDeMiembrosXComunidad getInstance() {
    if (repositorioDeMiembrosxComunidad == null) {
      repositorioDeMiembrosxComunidad = new RepositorioDeMiembrosXComunidad();
    }
    return repositorioDeMiembrosxComunidad;
  }

  public void agregar(MiembroxComunidad miembroxComunidad) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(miembroxComunidad);
    entityManager().flush();
    tx.commit();
  }

  public void eliminar(MiembroxComunidad miembroxComunidad) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(miembroxComunidad);
    tx.commit();
  }

  public void modificar(MiembroxComunidad miembroxComunidad) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(miembroxComunidad);
    entityManager().flush();
    tx.commit();
  }

  public MiembroxComunidad buscarPorMiembroComunidad(Long miembro_id, Long comunidad_id) {
    CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
    CriteriaQuery<MiembroxComunidad> criteriaQuery = criteriaBuilder.createQuery(MiembroxComunidad.class);

    Root<MiembroxComunidad> miembroRoot = criteriaQuery.from(MiembroxComunidad.class);
    Join<MiembroxComunidad, Comunidad> comunidadJoin = miembroRoot.join("comunidad", JoinType.INNER);
    Join<MiembroxComunidad, Miembro> miembroJoin = miembroRoot.join("miembro", JoinType.INNER);
    Predicate predicate = criteriaBuilder.conjunction();

    if (miembro_id != null) {
      predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(miembroJoin.get("id"), miembro_id));
    }

    if (comunidad_id != null) {
      predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(comunidadJoin.get("id"), comunidad_id));
    }

    criteriaQuery.where(predicate);

    TypedQuery<MiembroxComunidad> typedQuery = entityManager().createQuery(criteriaQuery);
    typedQuery.setMaxResults(1);

    return typedQuery.getSingleResult();
  }

  public List<MiembroxComunidad> buscarPorMiembro(Long miembro_id) {
    CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
    CriteriaQuery<MiembroxComunidad> criteriaQuery = criteriaBuilder.createQuery(MiembroxComunidad.class);

    Root<MiembroxComunidad> miembroRoot = criteriaQuery.from(MiembroxComunidad.class);
    Join<MiembroxComunidad, Miembro> miembroJoin = miembroRoot.join("miembro", JoinType.INNER);
    Predicate predicate = criteriaBuilder.conjunction();

    if (miembro_id != null) {
      predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(miembroJoin.get("id"), miembro_id));
    }

    criteriaQuery.where(predicate);

    TypedQuery<MiembroxComunidad> typedQuery = entityManager().createQuery(criteriaQuery);
    return typedQuery.getResultList();
  }

}
