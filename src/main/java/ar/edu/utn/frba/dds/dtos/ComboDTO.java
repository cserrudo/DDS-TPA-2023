package ar.edu.utn.frba.dds.dtos;

public class ComboDTO {
  private String label;
  private String value;

  public ComboDTO(String label, String value) {
    this.label = label;
    this.value = value;
  }

  public String getLabel() {
    return label;
  }

  public String getValue() {
    return value;
  }
}
