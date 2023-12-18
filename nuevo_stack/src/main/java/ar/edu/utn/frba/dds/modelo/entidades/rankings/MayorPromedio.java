package ar.edu.utn.frba.dds.modelo.entidades.rankings;
import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Entidad;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.List;

public class MayorPromedio implements TipoRanking{

    @Override
    public List<Entidad> generarRanking(List<Entidad> entidadesAsociadas, Comunidad comunidadAsociada, int cantItemsRanking) {
        return entidadesAsociadas.stream()
                .sorted(Comparator.comparingLong(Entidad::promedioAperturaIncidentes).reversed())
                .limit(cantItemsRanking)
                .collect(Collectors.toList());
    }
}
