package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dtos.ComboDTO;
import ar.edu.utn.frba.dds.dtos.IncidenteComunidadDTO;
import ar.edu.utn.frba.dds.dtos.IncidenteFiltrosDTO;
import ar.edu.utn.frba.dds.modelo.entidades.Comunidad;
import ar.edu.utn.frba.dds.modelo.entidades.Miembro;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Entidad;
import ar.edu.utn.frba.dds.modelo.entidades.establecimientos.Establecimiento;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.Incidente;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteComunidad;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.incidentes.IncidenteForm;
import ar.edu.utn.frba.dds.modelo.entidades.servicios.Servicio;
import ar.edu.utn.frba.dds.modelo.repositorios.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class IncidentesController {

    @Autowired
    private RepositorioDeIncidentesComunidad repositorioDeIncidentes;

    @Autowired
    private RepositorioDeServicios repoServicios;

    @Autowired
    private RepositorioDeEntidades repositorioDeEntidades;

    @Autowired
    private RepositorioDeEstablecimientos repositorioDeEstablecimientos;

    @Autowired
    private RepositorioDeMiembros repositorioDeMiembros;

    private static final Logger LOGGER = LoggerFactory.getLogger(IncidentesController.class);

    @GetMapping("/incidentes")
    public String incidentes(@RequestParam(value = "nombre", defaultValue = "") String nombre,
            @RequestParam(value = "estado", required = false) String estado,
            @RequestParam(value = "comunidadId", required = false) String comunidadId,
            @RequestParam(value = "localizacionId", required = false) String localizacionId,
            @RequestParam(value = "entidadId", required = false) String entidadId,
            @RequestParam(value = "establecimientoId", required = false) String establecimientoId,
            @RequestParam(value = "miembroAltaId", required = false) String miembroAltaId,
            @RequestParam(value = "miembroBajaId", required = false) String miembroBajaId,
            Model model)
            throws Exception {

        IncidenteFiltrosDTO filtros = new IncidenteFiltrosDTO(nombre,
                estado != null ? (estado.equals("abierto")) : null, comunidadId, localizacionId, entidadId,
                establecimientoId,
                miembroAltaId, miembroBajaId);

        List<IncidenteComunidadDTO> incidentes = this.repositorioDeIncidentes.buscarTodos(filtros).stream()
                .map(incidente -> new IncidenteComunidadDTO(incidente.getEntidad(), incidente.getEstablecimiento(),
                        incidente.getServicio(),
                        incidente.getIncidente().getObservaciones(), incidente.getComunidad(), incidente.getActivo(),
                        incidente.getId()))
                .collect(Collectors.toList());

        List<ComboDTO> entidades = ComboController.getComboEntidades();
        List<ComboDTO> comunidades = ComboController.getComboComunidades();
        List<ComboDTO> localizaciones = ComboController.getComboLocalizaciones();
        List<ComboDTO> establecimientos = ComboController.getComboEstablecimientos();
        List<ComboDTO> miembros = ComboController.getComboMiembros();
        List<ComboDTO> servicios = ComboController.getComboServicios();

        model.addAttribute("incidentes", incidentes);
        model.addAttribute("entidades", entidades);
        model.addAttribute("comunidades", comunidades);
        model.addAttribute("localizaciones", localizaciones);
        model.addAttribute("establecimientos", establecimientos);
        model.addAttribute("miembros", miembros);
        model.addAttribute("servicios", servicios);
        return "incidentes";

    }

    @PostMapping("/incidentes/reportar")
    public String reportar(@RequestBody IncidenteForm incidenteForm) {
        Servicio servicio = repoServicios.buscarPorId(Long.parseLong(incidenteForm.getServicioId()));

        Entidad entidad = repositorioDeEntidades.buscarPorId(Long.valueOf(incidenteForm.getEntidadId()));

        Establecimiento establecimiento = repositorioDeEstablecimientos
                .buscarPorId(Long.valueOf(incidenteForm.getEstablecimientoId()));

        Long id = context.sessionAttribute("id_miembro");
        Miembro miembro = RepositorioDeMiembros.getInstance().buscarPorId(id);

        List<Comunidad> comunidadesDelMiembro = repositorioDeMiembros
                .obtenerComunidadesPorMiembroId(Long.valueOf(miembroId));

        comunidadesDelMiembro.forEach(comu -> {
            Incidente incidente = new Incidente(servicio, incidenteForm.getObservaciones(), miembro);
            IncidenteComunidad incidenteComunidad = new IncidenteComunidad(incidente,
                    comu);
            incidenteComunidad.setActivo(true);
            incidenteComunidad.setEntidad(entidad);
            incidenteComunidad.setEstablecimiento(establecimiento);
            incidenteComunidad.setServicio(servicio);
            repositorioDeIncidentes.agregar(incidenteComunidad);
            ;
        });
        return "incidentes";
    }

    @PostMapping("/incidentes/{id}/cerrar")
    @ResponseBody
    public String cerrar(@PathVariable String id) {
        Long idMiembro = context.sessionAttribute("id_miembro");
        Miembro miembro = RepositorioDeMiembros.getInstance().buscarPorId(idMiembro);
        IncidenteComunidad aEliminar = repositorioDeIncidentes.buscarPorId(Long.valueOf(id));
        aEliminar.cerrarIncidente(miembro);
        repositorioDeIncidentes.modificar(aEliminar);
        return null;
    }
}
