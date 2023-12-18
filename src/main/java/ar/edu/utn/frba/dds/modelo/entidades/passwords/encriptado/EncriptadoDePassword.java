package ar.edu.utn.frba.dds.modelo.entidades.passwords.encriptado;

import ar.edu.utn.frba.dds.modelo.entidades.passwords.Password;

import java.util.Objects;

import com.password4j.Hash;

public class EncriptadoDePassword {
  private static EncriptadoDePassword instance = null;

  public static EncriptadoDePassword getInstance() {
    if (instance == null) {
      instance = new EncriptadoDePassword();
    }
    return instance;
  }
  public static String EncriptarPassword(Password password){
    Hash hash = com.password4j.Password.hash(password.getPassword()).withPBKDF2();
    return hash.getResult();
  }

  public static Boolean desencriptado(Password password, String encriptado){
    return Objects.equals(EncriptarPassword(password), encriptado);
  }
}
