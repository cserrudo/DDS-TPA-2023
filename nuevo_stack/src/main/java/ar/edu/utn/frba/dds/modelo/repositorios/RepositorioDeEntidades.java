package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.dtos.EntidadFiltrosDTO;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Entidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioDeEntidades implements WithSimplePersistenceUnit {

  private static RepositorioDeEntidades repositorioDeEntidades;

  public static RepositorioDeEntidades getInstance() {
    if (repositorioDeEntidades == null) {
      repositorioDeEntidades = new RepositorioDeEntidades();
    }
    return repositorioDeEntidades;
  }

  public void agregar(Entidad unaEntidad) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(unaEntidad);
    tx.commit();
  }

  public void eliminar(Entidad unaEntidad) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(unaEntidad);
    tx.commit();
  }

  public void modificar(Entidad unaEntidad) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(unaEntidad);
    tx.commit();
  }

  public Entidad buscarPorId(Long id) {
    return entityManager().find(Entidad.class, id);
  }

  public List<Entidad> buscarTodos() {
    List<Entidad> resultado = entityManager()
        .createQuery(
            "SELECT DISTINCT c FROM Entidad c LEFT JOIN c.establecimientos",
            Entidad.class)
        .getResultList();

    return resultado;
  }

  public List<Entidad> buscarTodos(EntidadFiltrosDTO filtros) {
    CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
    CriteriaQuery<Entidad> criteriaQuery = criteriaBuilder.createQuery(Entidad.class);
    Root<Entidad> root = criteriaQuery.from(Entidad.class);

    List<Predicate> predicates = new ArrayList<>();
    predicates.add(criteriaBuilder.like(root.get("descripcion"), "%" + filtros.getNombre() + "%"));

    if (filtros.getLocalizacion() != null) {
      predicates.add(criteriaBuilder.equal(
          root.get("localizacion").get("id"),
          Long.parseLong(filtros.getLocalizacion())));
    }

    criteriaQuery.where(predicates.toArray(new Predicate[0]));
    criteriaQuery.select(root).distinct(false);

    TypedQuery<Entidad> typedQuery = entityManager().createQuery(criteriaQuery);
    List<Entidad> resultado = typedQuery.getResultList();

    return resultado;
  }
}
