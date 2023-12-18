package ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions;

public class PasswordDentroDePeoresPasswordsException extends PasswordInvalidaException {

  public PasswordDentroDePeoresPasswordsException() {
    super("La contraseña no puede ser una de las peores 10.000 segun OSWAP");
  }

}
