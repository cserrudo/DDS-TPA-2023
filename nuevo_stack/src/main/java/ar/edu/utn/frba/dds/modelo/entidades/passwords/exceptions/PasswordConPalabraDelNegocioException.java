package ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions;

public class PasswordConPalabraDelNegocioException extends PasswordInvalidaException {

  public PasswordConPalabraDelNegocioException() {
    super("La contraseña NO DEBE tener palabras del negocio ni el contexto");
  }

}
