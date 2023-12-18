package ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades;

import ar.edu.utn.frba.dds.modelo.entidades.Persistente;
import ar.edu.utn.frba.dds.modelo.entidades.rankings.Ranking;
import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organismo_de_control")
public class OrganismoDeControl extends Persistente {
	@Column(name = "nombre")
	private String nombre;
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinTable(name = "servicios_organismo_de_control", joinColumns = @JoinColumn(name = "organismo_id"),
			inverseJoinColumns = @JoinColumn(name = "servicio_id"))
	private List<Servicio> servicios = new ArrayList<>();
	@Transient
	private List<Ranking> rankings = new ArrayList<>();
	
	public OrganismoDeControl(String datum, String datum1) {
	}

	public OrganismoDeControl() {

	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Servicio> getServicios() {
		return servicios;
	}
	
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	
	public List<Ranking> getRankings() {
		return rankings;
	}
	
	public void setRankings(List<Ranking> rankings) {
		this.rankings = rankings;
	}
}
