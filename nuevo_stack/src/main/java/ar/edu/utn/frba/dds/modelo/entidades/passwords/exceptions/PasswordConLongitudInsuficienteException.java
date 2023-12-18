package ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions;

public class PasswordConLongitudInsuficienteException extends PasswordInvalidaException {

  public PasswordConLongitudInsuficienteException(int longitudMinimaPassword) {
    super("Contraseña incorrecta: La longitud MINIMA de la contraseña es de "
        + longitudMinimaPassword
        + " caracteres.");
  }

}
