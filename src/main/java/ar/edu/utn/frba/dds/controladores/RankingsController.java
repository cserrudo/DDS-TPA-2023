package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.ComboDTO;
import ar.edu.utn.frba.dds.dtos.RankingDTO;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Entidad;
import ar.edu.utn.frba.dds.modelo.entidades.rankings.MayorCantidadIncidentes;
import ar.edu.utn.frba.dds.modelo.entidades.rankings.MayorPromedio;
import ar.edu.utn.frba.dds.modelo.entidades.usuarios.Usuario;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDeComunidades;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDeEntidades;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDeUsuarios;
import io.javalin.http.Context;

import java.util.*;
import java.util.stream.Collectors;

public class RankingsController extends Controller {

    private RepositorioDeComunidades repositorioDeComunidades;
    private RepositorioDeEntidades repositorioDeEntidades;

    public RankingsController(RepositorioDeComunidades repositorioDeIncidentes,
            RepositorioDeEntidades repositorioDeEntidades) {
        this.repositorioDeComunidades = repositorioDeIncidentes;
        this.repositorioDeEntidades = repositorioDeEntidades;
    }

    public void index(Context context) throws Exception {
        Long id = context.sessionAttribute("id_usuario");
        Usuario usuario = RepositorioDeUsuarios.getInstance().buscarPorId(id);

        String criterio = Optional.ofNullable(context.queryParam("criterio")).orElse("MAYOR_CANTIDAD");
        String cantidad = Optional.ofNullable(context.queryParam("cantidad")).orElse("5");
        List<ComboDTO> criterios = new ArrayList<>();
        Map<String, Object> model = new HashMap<>();
        List<Entidad> rankingEntidades = new ArrayList<>();
        if (criterio.equals("MAYOR_CANTIDAD")) {
            List<Entidad> entidades = repositorioDeEntidades.buscarTodos().stream()
                    .filter(entidad -> entidad.obtenerIncidentes().stream()
                            .anyMatch(incidenteComunidad -> !incidenteComunidad.isEstaActivo()))
                    .collect(Collectors.toList());
            ;
            MayorCantidadIncidentes mayorCantidadIncidentes = new MayorCantidadIncidentes();
            rankingEntidades = mayorCantidadIncidentes.generarRanking(entidades, null, Integer.parseInt(cantidad));

        } else if (criterio.equals("MAYOR_PROMEDIO")) {
            List<Entidad> entidades = repositorioDeEntidades.buscarTodos().stream()
                    .filter(entidad -> entidad.obtenerIncidentes().stream()
                            .anyMatch(incidenteComunidad -> !incidenteComunidad.isEstaActivo()))
                    .collect(Collectors.toList());
            MayorPromedio mayorPromedio = new MayorPromedio();
            rankingEntidades = mayorPromedio.generarRanking(entidades, null, Integer.parseInt(cantidad));
        }
        int i = 1;
        List<RankingDTO> rankingDTO = new ArrayList<>();
        if (!rankingEntidades.isEmpty()) {
            for (Entidad entidad : rankingEntidades) {
                RankingDTO rankDto = new RankingDTO(i++, entidad.getDescripcion(),
                        entidad.promedioAperturaIncidentes(),
                        entidad.cantidadIncidentes());
                rankingDTO.add(rankDto);
            }
        }
        criterios.add(new ComboDTO("Mayor Cantidad de Incidentes Por Semana", "MAYOR_CANTIDAD"));
        criterios.add(new ComboDTO("Mayor Promedio de Cierre de Incidentes", "MAYOR_PROMEDIO"));
        model.put("items", new ArrayList<>());
        model.put("criterios", criterios);
        model.put("items", rankingDTO);
        model.put("usuario", usuario);

        context.render("Rankings.hbs", model);
    }

}