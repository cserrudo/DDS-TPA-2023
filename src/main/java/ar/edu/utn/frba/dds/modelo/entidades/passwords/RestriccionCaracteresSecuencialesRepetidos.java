package ar.edu.utn.frba.dds.modelo.entidades.passwords;


import ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions.PasswordConCaracteresRepetidosException;

public class RestriccionCaracteresSecuencialesRepetidos implements RestriccionesPasswords {

  private static final String regexNingunCaracterSeRepiteSecuencialmente = "^(?!.*(.)\\1).{0,}$";

  @Override
  public void cumpleRestriccion(String password) {
    boolean passwordTieneCaracteresSecuencialesRepetidos = !password
        .matches(regexNingunCaracterSeRepiteSecuencialmente);
    if (passwordTieneCaracteresSecuencialesRepetidos) {
      throw new PasswordConCaracteresRepetidosException();
    }
  }
}
