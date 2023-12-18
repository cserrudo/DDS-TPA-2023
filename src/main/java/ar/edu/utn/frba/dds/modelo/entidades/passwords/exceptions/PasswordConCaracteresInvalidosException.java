package ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions;

public class PasswordConCaracteresInvalidosException extends PasswordInvalidaException {

  public PasswordConCaracteresInvalidosException() {
    super("Solo se permiten como caracteres como !@#&%()–{}:;',?/*~$^+=<>, letras y numeros");
  }

}
