package ar.edu.utn.frba.dds.modelo.entidades.converters;

import ar.edu.utn.frba.dds.modelo.entidades.notificador.EstrategiaNotificacion;
import ar.edu.utn.frba.dds.modelo.entidades.notificador.NotificacionMail;
import ar.edu.utn.frba.dds.modelo.entidades.notificador.NotificacionWhatsapp;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EstrategiaNotificacionConverter implements AttributeConverter<EstrategiaNotificacion, String> {
  @Override
  public String convertToDatabaseColumn(EstrategiaNotificacion estrategia) {
    String tipoNotificacion;
    NotificacionMail email = new NotificacionMail();
    if (estrategia.getClass().equals(email.getClass())) {
      tipoNotificacion = "EMAIL";
    }
    else tipoNotificacion = "WPP";
    return tipoNotificacion;
  }

  @Override
  public EstrategiaNotificacion convertToEntityAttribute(String estrategia) {
    EstrategiaNotificacion estrategiaNotificacion;
    if (estrategia == "EMAIL"){
      estrategiaNotificacion = new NotificacionMail();
    }
    else estrategiaNotificacion = new NotificacionWhatsapp();
    return estrategiaNotificacion;
  }
}
