package ar.edu.utn.frba.dds.modelo.entidades.passwords;


import ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions.PasswordDentroDePeoresPasswordsException;
import ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions.ProblemasArchivoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RestriccionListaPeoresPasswords implements RestriccionesPasswords {

  public static final String pathTop10000OWASP = "src/main/resources/contrasenias.txt";

  public void cumpleRestriccion(String password) {
    List<String> peoresPasswords = obtenerPeoresPasswordsDelArchivo();
    if (passwordEstaDentroPeoresPasswords(password, peoresPasswords)) {
      throw new PasswordDentroDePeoresPasswordsException();
    }
  }

  public static List<String> obtenerPeoresPasswordsDelArchivo() {
    try (Stream<String> peoresPass = Files.lines(Paths.get(pathTop10000OWASP))) {
      return peoresPass.collect(Collectors.toList());
    } catch (IOException e) {
      throw new ProblemasArchivoException(pathTop10000OWASP);
    }
  }

  private boolean passwordEstaDentroPeoresPasswords(String password, List<String> peoresPasswords) {
    return peoresPasswords.stream().anyMatch(
        palabra -> password.toLowerCase().contains(palabra.toLowerCase()));
  }

}
