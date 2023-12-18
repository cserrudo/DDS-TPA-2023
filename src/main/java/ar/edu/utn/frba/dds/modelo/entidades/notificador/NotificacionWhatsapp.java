package ar.edu.utn.frba.dds.modelo.entidades.notificador;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class NotificacionWhatsapp implements EstrategiaNotificacion {
    // TODO: mover el token a una variable de entorno
    // public static final String ACCOUNT_SID = "AC4e105f4973f109c8c836c450487dba96";
   //  public static final String AUTH_TOKEN = "d7248cf4749fd1d8eeb2389e931519ae";

    // Para usar el celular de prueba, hay que mandar un mensaje al numero de twilio con el mensaje join wing-slabs para inciar el sandbox
    // Expira cada 3 días
    public NotificacionWhatsapp() {
        try ( InputStream input = NotificacionWhatsapp.class.getClassLoader().getResourceAsStream("tokens.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
			
            prop.load(input);
			
            String acount = prop.getProperty("ACCOUNT_SID");
            String token = prop.getProperty("AUTH_TOKEN");
            Twilio.init(acount, token);

        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }


    @Override
    public void enviarNotificacion(Notificacion notificacion) {
        // TODO: guardar celular twilio en variable de entorno
        enviarWpp(notificacion.getMensaje(), notificacion.getReceptor().getCelular(),"14155238886");
    }

    private void enviarWpp(String mensaje, String para, String de) {
        PhoneNumber fromPhoneNumber = new com.twilio.type.PhoneNumber("whatsapp:+" + de);

        PhoneNumber toPhoneNumber = new com.twilio.type.PhoneNumber("whatsapp:+" + para);

        String messageBody = mensaje;

        Message message = Message.creator(toPhoneNumber, fromPhoneNumber, messageBody).create();

        System.out.println("Mensaje: " + message.getBody() + " - Para: " + message.getTo() + " enviado correctamente.");
    }

}
