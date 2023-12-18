package ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions;

public class PasswordConCaracteresRepetidosException extends PasswordInvalidaException {

  public PasswordConCaracteresRepetidosException() {
    super("No se aceptan caracteres repetidos secuencialmente");
  }

}
