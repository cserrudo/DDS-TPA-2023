package ar.edu.utn.frba.dds.modelo.entidades.passwords.exceptions;

public class ProblemasArchivoException extends RuntimeException {

  public ProblemasArchivoException(String archivo) {
    super("No se pudo abrir/leer el archivo : " + archivo);
  }

}
