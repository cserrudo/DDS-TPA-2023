package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.usuarios.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

public class RepositorioDeUsuarios implements WithSimplePersistenceUnit {
  private static RepositorioDeUsuarios repositorioDeUsuarios;

  public static RepositorioDeUsuarios getInstance() {
    if (repositorioDeUsuarios == null) {
      repositorioDeUsuarios = new RepositorioDeUsuarios();
    }
    return repositorioDeUsuarios;
  }

  public void agregar(Usuario unaUsuario) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(unaUsuario);
    tx.commit();
  }

  public void eliminar(Usuario unaUsuario) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(unaUsuario);
    tx.commit();
  }

  public void modificar(Usuario unaUsuario) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(unaUsuario);
    tx.commit();
  }

  public Usuario buscarPorId(Long id) {
    return entityManager().find(Usuario.class, id);
  }

  public Usuario buscarPorNombre(String username) {
    CriteriaBuilder cb = entityManager().getCriteriaBuilder();
    CriteriaQuery<Usuario> criteriaQuery = cb.createQuery(Usuario.class);
    Root<Usuario> root = criteriaQuery.from(Usuario.class);

    criteriaQuery.select(root).where(cb.equal(root.get("username"), username));

    Usuario user = entityManager().createQuery(criteriaQuery).getSingleResult();
    return user;
  }

  public List<Usuario> buscarTodos() {
    return entityManager().createQuery("from " + Usuario.class.getName()).getResultList();
  }
}
