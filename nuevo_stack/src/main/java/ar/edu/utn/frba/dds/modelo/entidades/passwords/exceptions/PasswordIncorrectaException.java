package ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions;

public class PasswordIncorrectaException extends RuntimeException {

  public PasswordIncorrectaException() {
    super("Contraseña incorrecta");
  }

}
