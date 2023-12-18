package ar.edu.utn.frba.dds.modelo.entidades.rankings;

import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Entidad;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MayorGradoImpacto implements TipoRanking{
    @Override
    public List<Entidad> generarRanking(List<Entidad> entidadesAsociadas, Comunidad comunidadAsociada, int cantItemsRanking) {
        return comunidadAsociada.getIncidentes().stream()
                .map(IncidenteComunidad::getEntidad)
                .sorted(Comparator.comparingLong((Entidad entidad) -> entidad.getCantIncidentes() * comunidadAsociada.getMiembros().size())
                        .reversed())
                        .limit(cantItemsRanking)
                .collect(Collectors.toList());
    }
}
