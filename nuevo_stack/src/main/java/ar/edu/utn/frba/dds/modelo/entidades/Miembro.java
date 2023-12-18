package ar.edu.utn.frba.dds.modelo.entidades;

import ar.edu.utn.frba.dds.modelo.entidades.converters.FormaNotificacionConverter;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Entidad;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Ubicacion;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;
import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.CuandoSuceden;
import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.Horario;
import ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente.NotificacionIncidente;
import ar.edu.utn.frba.dds.modelo.entidades.notificador.NotificacionWhatsapp;
import ar.edu.utn.frba.dds.modelo.entidades.notificador.Notificador;
import ar.edu.utn.frba.dds.modelo.entidades.passwords.Password;
import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "miembro")
public class Miembro extends Persistente {
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "email")
    private String mail;
    @Column(name = "movil")
    private String celular;
    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "localizacion_id", referencedColumnName = "id")
    private Localizacion localizacion;
    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
    private Ubicacion geolocalizacion;
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinTable(name = "servicios_miembros", joinColumns = @JoinColumn(name = "miembro_id"), inverseJoinColumns = @JoinColumn(name = "servicio_id"))
    private final List<Servicio> serviciosAsociados = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinTable(name = "miembros_entidades", joinColumns = @JoinColumn(name = "miembro_id"), inverseJoinColumns = @JoinColumn(name = "entidad_id"))
    private final List<Entidad> entidadesAsociadas = new ArrayList<>();
    @Convert(converter = FormaNotificacionConverter.class)
    @Column(name = "tipo_notificacion")
    private NotificacionIncidente formaNotificacion;
    @Embedded
    private Notificador notificador;
    @Transient
    private List<Comunidad> comunidades;
    @OneToMany
    @JoinColumn(name = "miembro_id", referencedColumnName = "id")
    private List<Horario> horarios;

    public Miembro(String nombre, String apellido, String mail, Localizacion localizacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.localizacion = localizacion;
        this.formaNotificacion = new CuandoSuceden(this);
        this.notificador = new Notificador(new NotificacionWhatsapp());
        this.comunidades = new ArrayList<>();
        this.horarios = new ArrayList<>();
    }

    public Miembro() {
        this.comunidades = new ArrayList<>();
        this.formaNotificacion = new CuandoSuceden(this);
        this.notificador = new Notificador(new NotificacionWhatsapp());
        this.horarios = new ArrayList<>();
    }

    public void validarContrasenia(String contrasenia) {
        Password password = new Password(contrasenia);
    }

    public List<Servicio> obtenerServiciosDeInteres() {
        return this.serviciosAsociados.stream()
                .filter(servicio -> servicio.estaHabilitado(this).equals(false))
                .collect(Collectors.toList());
    }

    public void notificarIncidente(IncidenteComunidad incidente) {
        formaNotificacion.notificarIncidente(incidente);
    }

    public void altaComunidad(Comunidad comunidad) {
        /*
         * Aca creo que deberiamos mapear con miembrosxComunidad y agregar como
         * parametro
         * esObservador. No lo agrego porq sino se rompe todo
         */
        MiembroxComunidad miembroxComunidad = new MiembroxComunidad(this, comunidad, false);
        this.comunidades.add(comunidad);
        comunidad.altaMiembro(this);
    }

    public void bajaComunidad(Comunidad comunidad) {
        this.comunidades.remove(comunidad);
        comunidad.bajaMiembro(this);
    }

    public void recibirUbicacionActual(Ubicacion ubicacion) {
        this.geolocalizacion = ubicacion;
    }

    public void notificarIncidenteEnHorario(Horario horario) {
        if (containsHour(horario)) {
            formaNotificacion.notificarIncidentesSegunHorario();
        }
    }

    private boolean containsHour(Horario horario) {
        return formaNotificacion
                .obtenerHorarios().stream()
                .anyMatch(hour -> hour.getDia().equals(horario.getDia()) && hour.getHora().equals(horario.getHora()));
    }
}
