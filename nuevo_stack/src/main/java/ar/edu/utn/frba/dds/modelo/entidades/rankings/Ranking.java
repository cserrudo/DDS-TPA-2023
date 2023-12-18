package ar.edu.utn.frba.dds.modelo.entidades.rankings;

import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Entidad;
import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;

import java.util.ArrayList;
import java.util.List;

public class Ranking {
	private int cantItemsRanking;
	private List<Entidad> entidadesAsociadas = new ArrayList<>();
	private Comunidad comunidadAsociada;
	private TipoRanking tipoRanking;
	
	public List<Entidad> generarRanking() {
		return tipoRanking.generarRanking(entidadesAsociadas, comunidadAsociada, cantItemsRanking);
	}
	
	public int getCantItemsRanking() {
		return cantItemsRanking;
	}
	
	public void setCantItemsRanking(int cantItemsRanking) {
		this.cantItemsRanking = cantItemsRanking;
	}
	
	public List<Entidad> getEntidadesAsociadas() {
		return entidadesAsociadas;
	}
	
	public void setEntidadesAsociadas(List<Entidad> entidadesAsociadas) {
		this.entidadesAsociadas = entidadesAsociadas;
	}
	
	public Comunidad getComunidadAsociada() {
		return comunidadAsociada;
	}
	
	public void setComunidadAsociada(Comunidad comunidadAsociada) {
		this.comunidadAsociada = comunidadAsociada;
	}
	
	public TipoRanking getTipoRanking() {
		return tipoRanking;
	}
	
	public void setTipoRanking(TipoRanking tipoRanking) {
		this.tipoRanking = tipoRanking;
	}
}
