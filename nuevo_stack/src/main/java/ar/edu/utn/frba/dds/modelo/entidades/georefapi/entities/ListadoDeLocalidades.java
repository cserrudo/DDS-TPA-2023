package ar.edu.utn.frba.dds.modelo.entidades.georefapi.entities;

import java.util.List;

//las clases moldes no deben ser las entidades de dominio
public class ListadoDeLocalidades {
  public int cantidad;
  public int inicio;
  public int total;
  public List<Localidad> localidades;
  public Parametro parametros;
  private class Parametro{
    List<String> campos;
  }

}
