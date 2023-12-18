package ar.edu.utn.frba.dds.controladores;

import io.javalin.http.Context;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import ar.edu.utn.frba.dds.dtos.ComboDTO;
import ar.edu.utn.frba.dds.dtos.ComunidadDTO;
import ar.edu.utn.frba.dds.dtos.ComunidadFiltrosDTO;
import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.MiembroxComunidad;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDeComunidades;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDeMiembros;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDeMiembrosXComunidad;

public class ComunidadesController extends Controller {
        private RepositorioDeComunidades repositorioDeComunidades;

        public ComunidadesController(RepositorioDeComunidades repositorioDeComunidades) {
                this.repositorioDeComunidades = repositorioDeComunidades;
        }

        public void index(Context context) throws Exception {
                Long id = context.sessionAttribute("id_miembro");
                Miembro miembroLogueado = RepositorioDeMiembros.getInstance().buscarPorId(id);

                String nombre = Optional.ofNullable(context.queryParam("nombre")).orElse("");
                String soyMiembro = context.queryParam("soyMiembro");

                ComunidadFiltrosDTO filtros = new ComunidadFiltrosDTO(nombre, miembroLogueado.getId(),
                                soyMiembro != null ? soyMiembro.equals("true") : null);

                Map<String, Object> model = new HashMap<>();

                List<ComunidadDTO> comunidades = this.repositorioDeComunidades.buscarTodos(filtros).stream()
                                .map(comunidad -> {
                                        boolean esObservador = false;

                                        if (comunidad.perteneceALaComunidad(miembroLogueado)) {
                                                try {
                                                        MiembroxComunidad miembroComunidad = RepositorioDeMiembrosXComunidad
                                                                        .getInstance()
                                                                        .buscarPorMiembroComunidad(
                                                                                        miembroLogueado.getId(),
                                                                                        comunidad.getId());

                                                        if (miembroComunidad != null
                                                                        && miembroComunidad.getEsObservador() != null) {
                                                                esObservador = miembroComunidad.getEsObservador()
                                                                                .booleanValue();
                                                        }
                                                } catch (NoResultException e) {
                                                        esObservador = false;
                                                }

                                        }

                                        return new ComunidadDTO(
                                                        comunidad.getNombre(),
                                                        comunidad.cantMiembros(),
                                                        comunidad.perteneceALaComunidad(miembroLogueado),
                                                        comunidad.getIncidentesActivos(),
                                                        comunidad.getId(),
                                                        esObservador);
                                })
                                .collect(Collectors.toList());

                List<ComboDTO> servicios = ComboController.getComboServicios();
                List<ComboDTO> comunidadesCombo = ComboController.getComboComunidades();

                model.put("comunidades", comunidades);
                model.put("servicios", servicios);
                model.put("comunidadesCombo", comunidadesCombo);
                model.put("usuario", miembroLogueado.getUsuario());

                context.render("Comunidades.hbs", model);
        }

        public void altaMiembro(Context context) {
                Long id = context.sessionAttribute("id_miembro");
                Miembro miembroLogueado = RepositorioDeMiembros.getInstance().buscarPorId(id);

                String idComunidad = context.pathParam("id");
                Comunidad comunidad = repositorioDeComunidades.buscarPorId(Long.parseLong((idComunidad)));

                comunidad.setActivo(true);
                miembroLogueado.altaComunidad(comunidad);
                repositorioDeComunidades.modificar(comunidad);

                context.redirect("/comunidades");
        }

        public void bajaMiembro(Context context) {
                Long id = context.sessionAttribute("id_miembro");
                Miembro miembroLogueado = RepositorioDeMiembros.getInstance().buscarPorId(id);

                String idComunidad = context.pathParam("id");
                Comunidad comunidad = repositorioDeComunidades.buscarPorId(Long.parseLong((idComunidad)));

                comunidad.setActivo(false);
                comunidad.setFecha_baja(LocalDateTime.now());
                miembroLogueado.bajaComunidad(comunidad);

                repositorioDeComunidades.modificar(comunidad);

                context.redirect("/comunidades");
        }

        public void miembroEsObservador(Context context) {
                Long idComunidad = Long.parseLong(context.pathParam("id"));
                Long idMiembro = context.sessionAttribute("id_miembro");

                MiembroxComunidad miembroxComunidad = RepositorioDeMiembrosXComunidad.getInstance()
                                .buscarPorMiembroComunidad(idMiembro, idComunidad);

                miembroxComunidad.toggleEsObservador();

                RepositorioDeMiembrosXComunidad.getInstance().modificar(miembroxComunidad);

                context.redirect("/comunidades");
        }

}