package ar.edu.utn.frba.dds.modelo.entidades.establecimientos;

import ar.edu.utn.frba.dds.modelo.entidades.Persistente;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;
import ar.edu.utn.frba.dds.modelo.entidades.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "entidad")
public class Entidad extends Persistente {
	@Getter
	@Column(name = "descripcion")
	private String descripcion;
	@Getter
	@Column(name = "tipo")
	private String tipo;
	@Getter
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "entidad_id", referencedColumnName = "id")
	private List<Establecimiento> establecimientos;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "localizacion_id", referencedColumnName = "id")
	private Localizacion localizacion;
	@Getter
	@Transient
	protected long cantIncidentes;

	public Entidad(String descripcion, List<Establecimiento> establecimientos, Localizacion localizacion, String tipo) {
		this.localizacion = localizacion;
		this.descripcion = descripcion;
		this.establecimientos = establecimientos;
		this.tipo = tipo;
	}

	public Entidad() {
		this.establecimientos = new ArrayList<>();
	}

	public List<IncidenteComunidad> getIncidentesActivos() {
		List<Servicio> servicios = establecimientos.stream()
				.flatMap(establecimiento -> establecimiento.getServicios().stream())
				.collect(Collectors.toList());

		List<IncidenteComunidad> incidentes = servicios.stream().flatMap(servicio -> servicio.getIncidentes().stream())
				.collect(Collectors.toList());

		return incidentes;
	}

	public void setEstablecimientos(List<Establecimiento> establecimientos) {
		this.establecimientos = establecimientos;
	}

	public List<IncidenteComunidad> obtenerIncidentes() {
		return this.establecimientos
				.stream().flatMap(establecimiento -> establecimiento
						.getServicios()
						.stream())
				.flatMap(servicio -> servicio
						.getIncidentes()
						.stream())
				.collect(Collectors.toList());
	}

	public long cantidadIncidentes() {
		return obtenerIncidentes()
				.stream()
				.count();
	}

	public Long promedioAperturaIncidentes() {
		return obtenerIncidentes()
				.stream()
				.mapToLong(IncidenteComunidad::tiempoApertura)
				.sum() / cantidadIncidentes();
	}

	public long cantidadIncidentesNoRepetidos() {
		return obtenerIncidentes()
				.stream()
				.filter(incidente -> incidente.noEstaRepetido(incidente))
				.count();
	}

}
