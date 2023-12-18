package ar.edu.utn.frba.dds.modelo.entidades.passwords;


import ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions.PasswordConCaracteresInvalidosException;

public class RestriccionCaracteresPermitidos implements RestriccionesPasswords {

  private static final String regexCaracteresValidos = "[!@#&%()–{}:;',?/*~$^+=<>0-9a-zA-Z]{0,}";

  @Override
  public void cumpleRestriccion(String password) {
    boolean passwordTieneCaracteresInvalidos = !password.matches(regexCaracteresValidos);
    if (passwordTieneCaracteresInvalidos) {
      throw new PasswordConCaracteresInvalidosException();
    }
  }
}
