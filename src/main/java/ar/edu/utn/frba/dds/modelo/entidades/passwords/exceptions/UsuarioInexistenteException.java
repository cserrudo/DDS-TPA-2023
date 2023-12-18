package ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions;

public class UsuarioInexistenteException extends RuntimeException {

  public UsuarioInexistenteException() {
    super("El admin que se quiere eliminar no existe");
  }

}
