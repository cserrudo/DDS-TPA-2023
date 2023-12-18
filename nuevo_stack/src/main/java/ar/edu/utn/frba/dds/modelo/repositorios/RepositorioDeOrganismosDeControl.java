package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.OrganismoDeControl;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioDeOrganismosDeControl implements WithSimplePersistenceUnit {
  private static RepositorioDeOrganismosDeControl repositorioDeOrganismoDecontrol;

  public static RepositorioDeOrganismosDeControl getInstance() {
    if (repositorioDeOrganismoDecontrol == null) {
      repositorioDeOrganismoDecontrol = new RepositorioDeOrganismosDeControl();
    }
    return repositorioDeOrganismoDecontrol;
  }

  public void agregar(OrganismoDeControl unOriganismoDeControl) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(unOriganismoDeControl);
    tx.commit();
  }

  public void eliminar(OrganismoDeControl unOriganismoDeControl) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(unOriganismoDeControl);
    tx.commit();
  }

  public void modificar(OrganismoDeControl unOriganismoDeControl) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(unOriganismoDeControl);
    tx.commit();
  }

  public OrganismoDeControl buscarPorId(Long id) {
    return entityManager().find(OrganismoDeControl.class, id);
  }

  public List<OrganismoDeControl> buscarTodos() {
    return entityManager().createQuery("from " + OrganismoDeControl.class.getName()).getResultList();
  }
}
