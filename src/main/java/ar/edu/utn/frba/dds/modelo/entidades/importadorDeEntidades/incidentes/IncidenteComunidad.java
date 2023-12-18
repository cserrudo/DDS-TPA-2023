package ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.Persistente;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Entidad;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Establecimiento;
import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "incidente_comunidad")
public class IncidenteComunidad extends Persistente {
	@Transient
	private List<IncidenteComunidad> filtrados;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "incidente_id", referencedColumnName = "id")
	private Incidente incidente;
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "servicio_id", referencedColumnName = "id")
	private Servicio servicio;
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "miembro_cierre_id", referencedColumnName = "id")
	private Miembro miembroCierre;
	@Column(name = "fecha_cierre")
	private LocalDateTime fechaHoraCierre;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "comunidad_id", referencedColumnName = "id")
	private Comunidad comunidad;
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "entidad_id", referencedColumnName = "id")
	private Entidad entidad;
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "establecimiento_id", referencedColumnName = "id")
	private Establecimiento establecimiento;

	public IncidenteComunidad(Incidente incidente, Comunidad comunidad) {
		this.incidente = incidente;
		this.comunidad = comunidad;

		comunidad.abrirIncidente(this);
	}

	public IncidenteComunidad() {
		this.filtrados = new ArrayList<>();
	}

	public void cerrarIncidente(Miembro miembro) {
		this.fechaHoraCierre = LocalDateTime.now();
		this.miembroCierre = miembro;
		this.setActivo(false);

		comunidad.cerrarIncidente(this);
	}

	public boolean isEstaActivo() {
		return this.getActivo();
	}

	public Comunidad getComunidad() {
		return comunidad;
	}

	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio){this.servicio = servicio;}

	// A confirmar que esto este bien
	public Duration duracionIncidenteAbierto() {
		return Duration.between(this.incidente.getFecha_alta(), this.fechaHoraCierre);
	}

	public void posibleMiembroCerca(Miembro miembro) {
		if (comunidad.perteneceALaComunidad(miembro)) {
			miembro.notificarIncidente(this);
		}
	}

	public boolean pasoMasDe24hs(LocalDateTime fecha1, LocalDateTime fecha2) {
		Duration diferencia = Duration.between(fecha1, fecha2);
		return diferencia.toHours() >= 24;
	}

	public int tiempoApertura() {
		//Duration diferencia = Duration.between(incidente.getFecha_alta(), fechaHoraCierre);
		Duration diferencia = Duration.between(incidente.getFecha_alta(), LocalDateTime.now());
		return (int) diferencia.toMinutes();
	}

	public boolean condiciones(IncidenteComunidad incidenteComunidad, IncidenteComunidad incidenteFiltrado) {
		return pasoMasDe24hs(incidenteFiltrado.fechaHoraCierre, incidenteComunidad.getIncidente().getFecha_alta())
				|| (!pasoMasDe24hs(incidenteFiltrado.getFechaHoraCierre(), incidenteComunidad.getIncidente().getFecha_alta())
						&& !incidenteFiltrado.getActivo());
	}

	public boolean noEstaRepetido(IncidenteComunidad incidenteComunidad) {
		if (filtrados.isEmpty()) {
			filtrados.add(incidenteComunidad);
			return true;
		}

		return filtrados
				.stream()
				.anyMatch(filtrado -> condiciones(incidenteComunidad, filtrado));
	}

	public LocalDateTime getFechaHoraCierre() {
		return fechaHoraCierre;
	}

	public void setFechaHoraCierre() {
		this.fechaHoraCierre = LocalDateTime.now();
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEstablecimiento(Establecimiento establecimiento){this.establecimiento = establecimiento;}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public Incidente getIncidente() {
		return incidente;
	}

	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}

	public Miembro getMiembroAlta() {
		return this.getIncidente().getMiembro();
	}

	public Miembro getMiembroCierre() {
		return miembroCierre;
	}

	private String mensajeBase(String accion, Miembro miembro) {
		return "Se da de " + accion + " el incidente del servicio: "
				+ this.getIncidente().getServicio().getDescripcion()
				+ " - Observaciones: "
				+ this.getIncidente().getObservaciones()
				+ " - Comunidad: "
				+ this.getComunidad().getNombre()
				+ " - Miembro: " + miembro.getNombre();
	}

	public String getMensajeAlta() {
		return mensajeBase("alta", this.getMiembroAlta());
	}

	public String getMensajeBaja() {
		return mensajeBase("baja", this.getMiembroCierre());
	}

}
