package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.dtos.ComunidadFiltrosDTO;
import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.MiembroxComunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeComunidades implements WithSimplePersistenceUnit {
  private static RepositorioDeComunidades repositorioDeComunidades;

  public static RepositorioDeComunidades getInstance() {
    if (repositorioDeComunidades == null) {
      repositorioDeComunidades = new RepositorioDeComunidades();
    }
    return repositorioDeComunidades;
  }

  public void agregar(Comunidad unaComunidad) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(unaComunidad);
    tx.commit();
  }

  public void eliminar(Comunidad unaComunidad) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(unaComunidad);
    tx.commit();
  }

  public void modificar(Comunidad unaComunidad) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(unaComunidad);
    tx.commit();
  }

  public Comunidad buscarPorId(Long id) {
    return entityManager().find(Comunidad.class, id);
  }

  public List<Comunidad> buscarTodos(ComunidadFiltrosDTO filtros) {
    CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
    CriteriaQuery<Comunidad> criteriaQuery = criteriaBuilder.createQuery(Comunidad.class);
    Root<Comunidad> root = criteriaQuery.from(Comunidad.class);

    List<Predicate> predicates = new ArrayList<>();
    predicates.add(criteriaBuilder.like(root.get("nombre"), "%" + filtros.getNombre() + "%"));

    if (filtros.getMiembroId() != null && filtros.getSoyMiembro() != null) {
      Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
      Root<MiembroxComunidad> subRoot = subquery.from(MiembroxComunidad.class);
      subquery.select(criteriaBuilder.literal(1L));

      Join<MiembroxComunidad, Miembro> miembroUsuarioJoin = subRoot.join("miembro");
      Join<MiembroxComunidad, Comunidad> miembroComunidadJoin = subRoot.join("comunidad");

      subquery.where(criteriaBuilder.and(
          criteriaBuilder.equal(miembroUsuarioJoin.get("id"), filtros.getMiembroId()),
          criteriaBuilder.equal(miembroComunidadJoin.get("id"), root.get("id"))));

      if (filtros.getSoyMiembro()) {
        predicates.add(criteriaBuilder.exists(subquery));
      } else {
        predicates.add(criteriaBuilder.not(criteriaBuilder.exists(subquery)));
      }
    }

    criteriaQuery.where(predicates.toArray(new Predicate[0]));
    criteriaQuery.select(root).distinct(true);

    TypedQuery<Comunidad> typedQuery = entityManager().createQuery(criteriaQuery);
    List<Comunidad> resultado = typedQuery.getResultList();

    return resultado;
  }

  public List<Comunidad> buscarTodos() {
    return entityManager().createQuery("from " + Comunidad.class.getName()).getResultList();
  }

}
