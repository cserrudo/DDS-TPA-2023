package ar.edu.utn.frba.dds.modelo.entidades.passwords;


import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


public class ValidadorPasswords {
  private static final ValidadorPasswords instance = new ValidadorPasswords();

  private ValidadorPasswords() {

  }

  public static ValidadorPasswords getInstance() {
    return instance;
  }

  private final List<RestriccionesPasswords> restriccionesPasswords = Arrays.asList(
      new RestriccionCaracteresSecuencialesRepetidos(),
      new RestriccionLongitud(),
      new RestriccionCaracteresPermitidos(),
      new RestriccionesPalabrasNegocio(),
      new RestriccionListaPeoresPasswords()
  );

  public void validarPassword(String password) {
    Predicate<RestriccionesPasswords> cumpleTodaRestriccion = restriccion -> {
      restriccion.cumpleRestriccion(password);
      return true;
    };
    restriccionesPasswords.stream().allMatch(cumpleTodaRestriccion);
  }
}
