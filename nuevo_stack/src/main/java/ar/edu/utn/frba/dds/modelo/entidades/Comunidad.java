package ar.edu.utn.frba.dds.modelo.entidades;

import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;
import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comunidad")
public class Comunidad extends Persistente {
	@Column(name = "nombre")
	private String nombre;
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "miembro_comunidad", joinColumns = @JoinColumn(name = "comunidad_id"), inverseJoinColumns = @JoinColumn(name = "miembro_id"))
	private List<Miembro> usuarios;
	@Transient
	private List<Miembro> administradores;
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "servicios_comunidad", joinColumns = @JoinColumn(name = "comunidad_id"), inverseJoinColumns = @JoinColumn(name = "servicio_id"))
	private List<Servicio> serviciosDeInteres;
	@OneToMany(mappedBy = "comunidad")
	private List<IncidenteComunidad> incidentes;

	public Comunidad(String nombre) {
		this.nombre = nombre;
		this.usuarios = new ArrayList<>();
		this.administradores = new ArrayList<>();
		this.serviciosDeInteres = new ArrayList<>();
		this.incidentes = new ArrayList<>();
	}

	public Comunidad() {
		this.usuarios = new ArrayList<>();
		this.administradores = new ArrayList<>();
		this.incidentes = new ArrayList<>();
		this.serviciosDeInteres = new ArrayList<>();
	}

	public void abrirIncidente(IncidenteComunidad incidente) {
		this.incidentes.add(incidente);
		this.notificarMiembros(incidente);
	}

	public void cerrarIncidente(IncidenteComunidad incidente) {
		this.incidentes.remove(incidente);
		this.notificarMiembros(incidente);
	}

	public void notificarMiembros(IncidenteComunidad incidente) {
		// Por el momento notificaria a todos sin importar que lo reciba el mismo quien
		// lo reporto.
		this.usuarios.forEach(miembro -> miembro.notificarIncidente(incidente));
		this.administradores.forEach(miembro -> miembro.notificarIncidente(incidente));
	}

	public Boolean perteneceALaComunidad(Miembro miembro) {
		return this.usuarios.contains(miembro) || this.administradores.contains(miembro);
	}

	public void altaMiembro(Miembro miembro) {
		this.usuarios.add(miembro);
	}

	public void bajaMiembro(Miembro miembro) {
		this.usuarios.remove(miembro);
	}

	public int cantMiembros() {
		return this.usuarios.size() + this.administradores.size();
	}

	public List<Miembro> getMiembros() {
		return usuarios;
	}

	public List<IncidenteComunidad> getIncidentes() {
		return incidentes;
	}

}
