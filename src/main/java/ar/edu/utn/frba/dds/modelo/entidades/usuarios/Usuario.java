package ar.edu.utn.frba.dds.modelo.entidades.usuarios;

import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.Persistente;
import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.modelo.entidades.passwords.Password;
import ar.edu.utn.frba.dds.modelo.entidades.passwords.encriptado.EncriptadoDePassword;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario extends Persistente {

  @Column(name = "nombre_usuario")
  private String username;
  @Embedded
  private Password password;
  @ManyToOne
  @JoinColumn(name = "rol_id", referencedColumnName = "id")
  private Rol rol;
  @Transient
  private String passwordEncriptada;

  public Usuario(String username, String password) {
    this.username = username;
    this.password = new Password(password);
    this.passwordEncriptada = EncriptadoDePassword.EncriptarPassword(this.password);
  }

  public Usuario() {
  }

  public String getUsername() {
    return username;
  }

  public Password getPassword() {
    return this.password;
  }

  public Rol getRol() {
    return this.rol;
  }

  public void setRol(Rol rol) {
    this.rol = rol;
  }

  public String getPasswordEncriptada() {
    return this.passwordEncriptada;
  }

  public void changePassword(String actualPassword, String newPassword) {
    if (this.password.getPassword().equals(actualPassword)) {
      this.password = new Password(newPassword);
    }
  }
}
