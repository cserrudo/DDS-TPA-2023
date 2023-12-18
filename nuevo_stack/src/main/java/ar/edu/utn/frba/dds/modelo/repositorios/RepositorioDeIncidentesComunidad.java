package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.dtos.IncidenteFiltrosDTO;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;
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
public class RepositorioDeIncidentesComunidad implements WithSimplePersistenceUnit {

  private static RepositorioDeIncidentesComunidad repositorioDeIncidentesComunidad;

  public static RepositorioDeIncidentesComunidad getInstance() {
    if (repositorioDeIncidentesComunidad == null) {
      repositorioDeIncidentesComunidad = new RepositorioDeIncidentesComunidad();
    }
    return repositorioDeIncidentesComunidad;
  }

  public void agregar(IncidenteComunidad unIncidenteComunidad) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(unIncidenteComunidad);
    tx.commit();
  }

  public void eliminar(IncidenteComunidad unIncidenteComunidad) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(unIncidenteComunidad);
    tx.commit();
  }

  public void modificar(IncidenteComunidad unIncidenteComunidad) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(unIncidenteComunidad);
    tx.commit();
  }

  public IncidenteComunidad buscarPorId(Long id) {
    return entityManager().find(IncidenteComunidad.class, id);
  }

  public List<IncidenteComunidad> buscarTodos(IncidenteFiltrosDTO filtros) {
    CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
    CriteriaQuery<IncidenteComunidad> criteriaQuery = criteriaBuilder.createQuery(IncidenteComunidad.class);
    Root<IncidenteComunidad> root = criteriaQuery.from(IncidenteComunidad.class);

    List<Predicate> predicates = new ArrayList<>();
    predicates.add(criteriaBuilder.like(root.get("incidente").get("observaciones"), "%" + filtros.getNombre() + "%"));

    if (filtros.getActivo() != null) {
      predicates.add(criteriaBuilder.equal(root.get("activo"),
          filtros.getActivo()));
    }

    if (filtros.getComunidad() != null) {
      predicates.add(criteriaBuilder.equal(root.get("comunidad").get("id"),
          Long.parseLong(filtros.getComunidad())));
    }

    if (filtros.getLocalizacion() != null) {
      predicates.add(criteriaBuilder.equal(
          root.get("entidad").get("localizacion").get("id"),
          Long.parseLong(filtros.getLocalizacion())));
    }

    if (filtros.getEntidad() != null) {
      predicates.add(criteriaBuilder.equal(root.get("entidad").get("id"),
          Long.parseLong(filtros.getEntidad())));
    }

    if (filtros.getEstablecimiento() != null) {
      predicates.add(criteriaBuilder.equal(
          root.get("establecimiento").get("id"),
          Long.parseLong(filtros.getEstablecimiento())));
    }

    if (filtros.getMiembroAlta() != null) {
      predicates.add(criteriaBuilder.equal(
          root.get("incidente").get("miembro").get("id"),
          Long.parseLong(filtros.getMiembroAlta())));
    }

    if (filtros.getMiembroBaja() != null) {
      predicates.add(criteriaBuilder.equal(
          root.get("miembroCierre").get("id"),
          Long.parseLong(filtros.getMiembroBaja())));
    }

    criteriaQuery.where(predicates.toArray(new Predicate[0]));
    criteriaQuery.select(root).distinct(true);

    TypedQuery<IncidenteComunidad> typedQuery = entityManager().createQuery(criteriaQuery);
    List<IncidenteComunidad> resultado = typedQuery.getResultList();

    return resultado;
  }

  public List<IncidenteComunidad> getAll() {
    CriteriaBuilder builder = entityManager().getCriteriaBuilder();
    CriteriaQuery<IncidenteComunidad> critera = builder.createQuery(IncidenteComunidad.class);
    critera.from(IncidenteComunidad.class);
    List<IncidenteComunidad> entities =  entityManager().createQuery(critera).getResultList();
    return entities;
  }
}
