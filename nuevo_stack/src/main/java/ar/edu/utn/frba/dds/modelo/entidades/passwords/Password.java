package ar.edu.utn.frba.dds.modelo.entidades.passwords;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Password {
  @Column(name = "contrasenia")
  private String password;

  public Password(String password) {
    ValidadorPasswords.getInstance().validarPassword(password);
    this.password = password;
  }

  public Password() {

  }

  public String getPassword() {
    return this.password;
  }

}
