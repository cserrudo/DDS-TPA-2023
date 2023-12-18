package ar.edu.utn.frba.dds.modelo.entidades.passwords;


import ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions.PasswordConLongitudInsuficienteException;

public class RestriccionLongitud implements RestriccionesPasswords {

  private static final int longitudMinimaPassword = 8;

  @Override
  public void cumpleRestriccion(String password) {
    if (password.length() < longitudMinimaPassword) {
      throw new PasswordConLongitudInsuficienteException(longitudMinimaPassword);
    }
  }
}
