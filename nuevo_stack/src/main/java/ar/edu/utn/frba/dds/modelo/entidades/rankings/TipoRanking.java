package ar.edu.utn.frba.dds.modelo.entidades.rankings;

import java.util.List;

import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Entidad;

public interface TipoRanking {
	
	public List<Entidad> generarRanking(List<Entidad> entidadesAsociadas, Comunidad comunidadAsociada, int cantItemsRanking);
}
