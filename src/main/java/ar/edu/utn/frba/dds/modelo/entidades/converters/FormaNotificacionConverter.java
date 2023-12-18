package ar.edu.utn.frba.dds.modelo.entidades.converters;

import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.CuandoSuceden;
import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.NotificacionIncidente;
import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.SinApuros;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class FormaNotificacionConverter implements AttributeConverter<NotificacionIncidente, String> {
  @Override
  public String convertToDatabaseColumn(NotificacionIncidente formaNotificacion) {
    String tipoNotificacion;
    CuandoSuceden cuandoSuceden = new CuandoSuceden();
    if (formaNotificacion.getClass().equals(cuandoSuceden.getClass())) {
      tipoNotificacion = "Al instante";
    }
    else tipoNotificacion = "Configurado";
    return tipoNotificacion;
  }

  @Override
  public NotificacionIncidente convertToEntityAttribute(String formNotificacion) {
    NotificacionIncidente notificacionIncidente;
    if (formNotificacion == "Al instante"){
    notificacionIncidente = new CuandoSuceden();
  }
    else notificacionIncidente = new SinApuros();
    return notificacionIncidente;
  }
}
