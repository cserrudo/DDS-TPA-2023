package ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions;

public class UsuarioYaExisteException extends RuntimeException {

  public UsuarioYaExisteException() {
    super("El usuario ya existe");
  }

}
