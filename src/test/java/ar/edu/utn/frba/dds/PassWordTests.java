/*package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.passwords.Password;
import ar.edu.utn.frba.dds.passwords.RestriccionListaPeoresPasswords;
import ar.edu.utn.frba.dds.passwords.RestriccionLongitud;
import ar.edu.utn.frba.dds.passwords.ValidadorPasswords;
import ar.edu.utn.frba.dds.passwords.exceptions.PasswordConLongitudInsuficienteException;
import ar.edu.utn.frba.dds.passwords.exceptions.PasswordDentroDePeoresPasswordsException;
import ar.edu.utn.frba.dds.passwords.exceptions.PasswordInvalidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PassWordTests {
  @Test
  @DisplayName("Una password tiene que cumplir todos los requisitos para ser validada")
  public void passwordCumpleTodosLosRequisitosTest() {
    Password password = new Password("Cavan14613@dg212");
    Assertions.assertDoesNotThrow(() -> ValidadorPasswords.getInstance().validarPassword(password.getPassword()));
  }

  @Test
  @DisplayName("Una password cumple con el largo mínimo de 8 caracteres")
  public void passwordConOchoCaracteresTest() {
    Password password = new Password("Cavan14613@dg212");
    Assertions.assertDoesNotThrow(() -> new RestriccionLongitud()
        .cumpleRestriccion(password.getPassword()));
  }

  @Test
  @DisplayName("Una password NO cumple con el largo mínimo de 8 caracteres")
  public void passwordConMenosCaracteresTest() {
    String password = "Cv@dg12";
    Assertions.assertThrows(PasswordConLongitudInsuficienteException.class,
        () -> new RestriccionLongitud()
            .cumpleRestriccion(password));
  }

  @Test
  @DisplayName("Una password falla al estar entre las peores 10.000 peores")
  public void passwordEstaDentroDeLasPeoresTest() {
    String password = "123456789";
    Assertions.assertThrows(PasswordDentroDePeoresPasswordsException.class,
        () -> new RestriccionListaPeoresPasswords()
            .cumpleRestriccion(password));
  }

  @Test
  @DisplayName("Una password no tiene que estar entre las peores 10.000 peores")
  public void passwordNoEstaDentroDeLasPeoresTest() throws FileNotFoundException {
    Password password = new Password("Cavan14613@dg212");
    Assertions
        .assertDoesNotThrow(
            () -> new RestriccionListaPeoresPasswords()
                .cumpleRestriccion(password.getPassword()));
  }

  @Test
  @DisplayName("No se puede cambiar a una password invalida")
  public void noSePuedeCambiarApasswordInvalidaTest() {
    Usuario usuario = new Usuario("dds", "Cavan14613@dg212");
    String nuevaPasswordInvalida = "cc12saf";
    Assertions
        .assertThrows(PasswordInvalidaException.class,
            () -> usuario.changePassword(usuario.getPassword().getPassword(), nuevaPasswordInvalida));
  }

  @Test
  @DisplayName("Cambiar una contraseña con exito")
  public void cambiarPasswordTest() {

    Usuario usuario = new Usuario("dds", "Cavan14613@dg212");

    String passwordNueva = "Cpadgfan1463213@dg21632";

    assertDoesNotThrow(() -> usuario.changePassword(usuario.getPassword().getPassword(), passwordNueva));
  }
}
*/