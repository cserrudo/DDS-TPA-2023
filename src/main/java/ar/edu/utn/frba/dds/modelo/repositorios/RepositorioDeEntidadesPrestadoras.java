package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.EntidadPrestadora;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioDeEntidadesPrestadoras implements WithSimplePersistenceUnit {
  private static RepositorioDeEntidadesPrestadoras repositorioDeEntidadesPrestadoras;

  public static RepositorioDeEntidadesPrestadoras getInstance() {
    if (repositorioDeEntidadesPrestadoras == null) {
      repositorioDeEntidadesPrestadoras = new RepositorioDeEntidadesPrestadoras();
    }
    return repositorioDeEntidadesPrestadoras;
  }

  public void agregar(EntidadPrestadora unaEntidadPrestadora) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(unaEntidadPrestadora);
    tx.commit();
  }

  public void eliminar(EntidadPrestadora unaEntidadPrestadora) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(unaEntidadPrestadora);
    tx.commit();
  }

  public void modificar(EntidadPrestadora unaEntidadPrestadora) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(unaEntidadPrestadora);
    tx.commit();
  }

  public EntidadPrestadora buscarPorId(Long id) {
    return entityManager().find(EntidadPrestadora.class, id);
  }

  public List<EntidadPrestadora> buscarTodos() {
    return entityManager().createQuery("from " + EntidadPrestadora.class.getName()).getResultList();
  }
}
