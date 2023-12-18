package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dtos.ComboDTO;
import ar.edu.utn.frba.dds.modelo.repositorios.*;

import java.util.List;
import java.util.stream.Collectors;

public class ComboController {
  public static List<ComboDTO> getComboServicios() {
    return RepositorioDeServicios.getInstance().buscarTodos().stream()
        .map(servicio -> new ComboDTO(servicio.getDescripcion(), String.valueOf(servicio.getId())))
        .collect(Collectors.toList());
  }

  public static List<ComboDTO> getComboComunidades() {
    return RepositorioDeComunidades.getInstance().buscarTodos().stream()
        .map(comunidad -> new ComboDTO(comunidad.getNombre(), String.valueOf(comunidad.getId())))
        .collect(Collectors.toList());
  }

  public static List<ComboDTO> getComboLocalizaciones() {
    return RepositorioDeLocalizacion.getInstance().buscarTodos().stream()
        .map(localizacion -> new ComboDTO(localizacion.getNombre(), String.valueOf(localizacion.getId())))
        .collect(Collectors.toList());
  }

  public static List<ComboDTO> getComboEntidades() {
    return RepositorioDeEntidades.getInstance().buscarTodos().stream()
        .map(entidad -> new ComboDTO(entidad.getDescripcion(), String.valueOf(entidad.getId())))
        .collect(Collectors.toList());
  }

  public static List<ComboDTO> getComboEstablecimientos() {
    return RepositorioDeEstablecimientos.getInstance().buscarTodos().stream()
        .map(establecimiento -> new ComboDTO(establecimiento.getDescripcion(), String.valueOf(establecimiento.getId())))
        .collect(Collectors.toList());
  }

  public static List<ComboDTO> getComboMiembros() {
    return RepositorioDeMiembros.getInstance().buscarTodos().stream()
        .map(
            miembro -> new ComboDTO(miembro.getNombre() + " " + miembro.getApellido(), String.valueOf(miembro.getId())))
        .collect(Collectors.toList());
  }
}
