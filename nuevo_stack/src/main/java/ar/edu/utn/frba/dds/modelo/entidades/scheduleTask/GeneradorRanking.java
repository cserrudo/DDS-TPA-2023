package ar.edu.utn.frba.dds.modelo.entidades.scheduleTask;

import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.rankings.MayorCantidadIncidentes;
import ar.edu.utn.frba.dds.modelo.entidades.rankings.MayorGradoImpacto;
import ar.edu.utn.frba.dds.modelo.entidades.rankings.Ranking;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Entidad;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class GeneradorRanking extends TimerTask {

    @Override
    public void run() {
        List<Entidad> entidadesTotales = new ArrayList<>();
        //TODO obtener entidades de algun lugar ( repositorio?)
        List<Comunidad> comunidadesTotales = new ArrayList<>();
        //TODO obtener comunidades de algun lugar ( repositorio?)
        List<Entidad> entidadesPromedio = new ArrayList<>();
        List<Entidad> entidadesIncidente = new ArrayList<>();
        List<Entidad> comunidadesProblematicas = new ArrayList<>();
		Ranking rankingMayorTiempo = new Ranking();
		Ranking rankingMayorIncidentes = new Ranking();
		Ranking comunidadesProblem = new Ranking();
		
		rankingMayorTiempo = obtenerRankingMayorTiempoDeCierre(entidadesTotales);
		rankingMayorIncidentes = obtenerRankingMayorIncidentesReportados(entidadesTotales);
		comunidadesProblem = obtenerRankingComunidadesProblematicas(comunidadesTotales);
    }

    private Ranking obtenerRankingComunidadesProblematicas(List<Comunidad> comunidadesTotales) {
		Ranking ranking = new Ranking();
        MayorGradoImpacto mayorGradoProblematicasComparator = new MayorGradoImpacto();
		ranking.setTipoRanking(mayorGradoProblematicasComparator);
		ranking.generarRanking();
		return ranking;
    }

    private Ranking obtenerRankingMayorIncidentesReportados(List<Entidad> entidadesTotales) {
		Ranking ranking = new Ranking();
		MayorCantidadIncidentes incidentesReportadosComparator = new MayorCantidadIncidentes();
		ranking.setTipoRanking(incidentesReportadosComparator);
		ranking.generarRanking();
		return ranking;
    }

    private Ranking obtenerRankingMayorTiempoDeCierre(List<Entidad> entidadesTotales) {
		Ranking ranking = new Ranking();
        MayorGradoImpacto promedioTiempoCierreComparator = new MayorGradoImpacto();
		ranking.setTipoRanking(promedioTiempoCierreComparator);
		ranking.generarRanking();
        return ranking;
    }
}
