package ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions;

public class PasswordInvalidaException extends RuntimeException {

  public PasswordInvalidaException(String msg) {
    super(msg != null ? msg : "Contraseña invalida");
  }

}
