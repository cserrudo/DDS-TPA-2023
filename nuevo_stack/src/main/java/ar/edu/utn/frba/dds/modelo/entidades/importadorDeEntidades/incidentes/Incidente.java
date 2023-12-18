package ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.Persistente;
import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "incidente")
public class Incidente extends Persistente {
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "servicio_id", referencedColumnName = "id")
	private Servicio servicio;
	@Column(name = "observaciones")
	private String observaciones;
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "miembro_id", referencedColumnName = "id")
	private Miembro miembro;
	@OneToMany(mappedBy = "incidente")
	private List<IncidenteComunidad> incidentesComunidad;

	public Incidente(Servicio servicio, String observaciones, Miembro miembro) {
		this.miembro = miembro;
		this.observaciones = observaciones;
		this.servicio = servicio;
		this.incidentesComunidad = new ArrayList<IncidenteComunidad>();

		miembro
				.getComunidades()
				.forEach(comunidad -> {
					IncidenteComunidad incidenteComunidad = new IncidenteComunidad(this, comunidad);
					this.incidentesComunidad.add(incidenteComunidad);
					servicio.agregarIncidente(incidenteComunidad);
					System.out.println(incidenteComunidad.getMensajeAlta());
				});
	}

	public Incidente() {

	}

	public void darDeBaja(Miembro miembro) {
		// Filtro los incidentes comunidad que tienen a el incidente actual
		List<IncidenteComunidad> incidentesADarDeBaja = miembro
				.getComunidades()
				.stream()
				.flatMap(comunidad -> comunidad
						.getIncidentes()
						.stream())
				.filter(incidenteComunidad -> incidenteComunidad
						.getIncidente()
						.equals(this))
				.collect(Collectors.toList());

		incidentesADarDeBaja.forEach(incidenteComunidad -> {
			this.incidentesComunidad.remove(incidenteComunidad);
			servicio.sacarIncidente(incidenteComunidad);
			incidenteComunidad.cerrarIncidente(miembro);
			System.out.println(incidenteComunidad.getMensajeBaja());
		});
	}

	public String agregarIncidenteComunidad(IncidenteComunidad incidenteComunidad) {
		if (esMismoIndicenteComunidad(incidenteComunidad)) {
			return "Ese incidente ya fue declarado";
		} else {
			this.incidentesComunidad.add(incidenteComunidad);
			return "El incidente fue agregado correctamente";
		}
	}

	public Servicio getServicio() {
		return servicio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public Miembro getMiembro() {
		return miembro;
	}

	// public Entidad getEntidad() {
	// // return incidentesComunidad
	// }

	private boolean esMismoIndicenteComunidad(IncidenteComunidad incidenteComunidad) {
		return this.incidentesComunidad.stream()
				.anyMatch(incidenteComunidad1 -> incidenteComunidad1.getIncidente().equals(incidenteComunidad.getIncidente())
						&& incidenteComunidad1.getComunidad().equals(incidenteComunidad.getComunidad()));
	}
}
