package ar.edu.utn.frba.dds.modelo.entidades.passwords;

import ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions.ProblemasArchivoException;
import ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions.PasswordConPalabraDelNegocioException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class
RestriccionesPalabrasNegocio implements RestriccionesPasswords {
  public static final String palabrasDelNegocio = "src/main/resources/palabrasDominio.txt";

  public void cumpleRestriccion(String password) {
    List<String> palabrasNegocio = obtenerPalabrasNegocioDelArhivo();
    if (passwordTienePalabrasDelNegocio(password, palabrasNegocio)) {
      throw new PasswordConPalabraDelNegocioException();
    }
  }

  public static List<String> obtenerPalabrasNegocioDelArhivo() {
    try (Stream<String> palabrasNegocio = Files.lines(Paths.get(palabrasDelNegocio))) {
      return palabrasNegocio.collect(Collectors.toList());
    } catch (IOException e) {
      throw new ProblemasArchivoException(palabrasDelNegocio);
    }
  }

  private boolean passwordTienePalabrasDelNegocio(String password, List<String> palabrasNegocio) {
    return palabrasNegocio.stream().anyMatch(
        palabra -> password.toLowerCase().contains(palabra.toLowerCase()));
  }

}

