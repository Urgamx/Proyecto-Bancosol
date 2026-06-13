package com.example.proyectobancosol.controller.coordinador;

import com.example.proyectobancosol.dto.request.ColaboradorRequestDTO;
import com.example.proyectobancosol.dto.request.TiendaRequestDTO;
import com.example.proyectobancosol.dto.response.*;
import com.example.proyectobancosol.entity.*;
import com.example.proyectobancosol.mapper.admin.TiendaAdminMapper;
import com.example.proyectobancosol.service.capitan.AsignacionTurnoService;
import com.example.proyectobancosol.service.capitan.TiendaService;
import com.example.proyectobancosol.service.coordinador.*;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/coordinador")
public class CoordinadorController {

    private final ColaboradorService colaboradorService;
    private final UsuarioColaboradorService usuarioColaboradorService;
    private final UsuarioService usuarioService;
    private final UsuarioTiendaService usuarioTiendaService;
    private final AsignacionTurnoService asignacionTurnoService;
    private final VoluntarioService voluntarioService;
    private final TiendaService tiendaService;
    private final CadenaService cadenaService;
    private final CampanaService campanaService;
    private final TiendaAdminMapper tiendaAdminMapper;

    @GetMapping("/")
    public String home(@SessionAttribute(name = "usuario",required = false) Usuario user,
                       HttpSession session)
    {
        if (user == null){
            return "redirect:/";
        }

        return "coordinador/homeCoordinador";

    }

    @GetMapping("/colaborador")
    public String listarColaboradores(@SessionAttribute(name = "usuario",required = false) Usuario user,
                         Model model, HttpSession session)
    {

        if (user == null){
            return "redirect:/";
        }

        List<ColaboradorResponseDTO> colaboradores = this.colaboradorService.findAllActivos();
        List<UsuarioColaboradorDTO> relaciones = this.usuarioColaboradorService.findAll();
        List<UsuarioDTO> coordinadores = this.usuarioService.findCoordinador();
        model.addAttribute("colaboradores",colaboradores);
        model.addAttribute("relaciones", relaciones);
        model.addAttribute("coordinadores", coordinadores);
        return "coordinador/colaborador";
    }

    @PostMapping("/filtrarColaborador")
    public String filtrarColaborador(@SessionAttribute(name = "usuario",required = false) Usuario user,
                                     @RequestParam(value = "zonaGeografica", defaultValue = "") String zonaGeo,
                                     @RequestParam(value = "localidad", defaultValue = "") String localidad,
                                     @RequestParam(value = "coordinador", required = false) Integer coordinadorId,
                                     Model model, HttpSession session)
    {
        if (user == null){
            return "redirect:/";
        }

        List<ColaboradorResponseDTO> colaboradores = null;
        if (coordinadorId == null) {
            colaboradores = this.usuarioColaboradorService.findByZonaLocalidad(zonaGeo,localidad);
        } else {
            colaboradores = this.usuarioColaboradorService.findByZonaLocalidadCoorId(zonaGeo,localidad,coordinadorId);
        }
        List<UsuarioColaboradorDTO> relaciones = this.usuarioColaboradorService.findAll();
        List<UsuarioDTO> coordinadores = this.usuarioService.findCoordinador();
        model.addAttribute("colaboradores",colaboradores);
        model.addAttribute("relaciones", relaciones);
        model.addAttribute("coordinadores", coordinadores);
        model.addAttribute("zonaGeo",zonaGeo);
        model.addAttribute("localidad",localidad);
        model.addAttribute("coordinadorSelected", coordinadorId);


        return "/coordinador/colaborador";
    }

    @GetMapping("/editarColaborador")
    public String editarColaborador(@SessionAttribute(name = "usuario",required = false) Usuario user,
                                    @RequestParam("id") Integer id,
                                    Model model, HttpSession session)
    {
        if (user == null){
            return "redirect:/";
        }

        ColaboradorRequestDTO colaborador = this.colaboradorService.findById(id);
        model.addAttribute("colaborador",colaborador);
        return "coordinador/editarColaborador";
    }

    @GetMapping("/nuevoColaborador")
    public String nuevoColaborador(@SessionAttribute(name = "usuario",required = false) Usuario user,
                                   HttpSession session)
    {
        if (user == null){
            return "redirect:/";
        }

        return "coordinador/nuevoColaborador";
    }

    @PostMapping("/guardarColaborador")
    public String guardarColaborador(@SessionAttribute(name = "usuario",required = false) Usuario user,
                                     @RequestParam(value = "id" , required = false) Integer id,
                                     @RequestParam(value = "contactoNom",required = false) String nombre,
                                     @RequestParam(value = "contactoTlf",required = false) String tlf,
                                     @RequestParam(value = "email",required = false) String email,
                                     @RequestParam(value = "nombreEntidad",required = false) String nombreEntidad,
                                     @RequestParam(value = "domicilio",required = false) String domicilio,
                                     @RequestParam(value = "localidad",required = false) String localidad,
                                     @RequestParam(value = "zonaGeografica",required = false) String zonaGeografica,
                                     @RequestParam(value = "observaciones",required = false) String observaciones,
                                     @RequestParam(value = "codPostal",required = false) String codPostal,
                                     HttpSession session)
    {
        if (user == null){
            return "redirect:/";
        }

        ColaboradorRequestDTO colaborador = null;

        if (id != null) {
            colaborador = this.colaboradorService.findById(id);
        } else {
            colaborador = new ColaboradorRequestDTO();
        }

        colaborador.setEmail(email);
        colaborador.setContactoNom(nombre);
        colaborador.setContactoTlf(tlf);
        colaborador.setNombreEntidad(nombreEntidad);
        colaborador.setDomicilio(domicilio);
        colaborador.setLocalidad(localidad);
        colaborador.setZonaGeografica(zonaGeografica);
        colaborador.setObservaciones(observaciones);
        colaborador.setCodPostal(codPostal);
        colaborador.setEstado(2); // Esperando aprobación de un administrador
        this.colaboradorService.save(colaborador);

        if (id == null) {
            UsuarioColaboradorDTO relacion = new UsuarioColaboradorDTO();
            relacion.setColaboradorId(colaborador.getId());
            relacion.setUsuarioId(user.getId());
            this.usuarioColaboradorService.save(relacion);
        }

        return "redirect:/coordinador/colaborador";
    }

    @GetMapping("/asignacionVoluntarios")
    public String listarAsignacionTurnos(@SessionAttribute(name = "usuario",required = false) Usuario user,
                                          Model model, HttpSession session)
    {
        if (user == null){
            return "redirect:/";
        }

        List<UsuarioTiendaDTO> relaciones = this.usuarioTiendaService.findAll();
        List<AsignacionTurnoDTO> turnos = this.asignacionTurnoService.ListarAsignacionTurnos();
        List<CadenaResponseDTO> cadenas = this.cadenaService.findAll();
        List<UsuarioDTO> capitanes = this.usuarioService.findCapitan();

        model.addAttribute("turnos",turnos);
        model.addAttribute("relaciones",relaciones);
        model.addAttribute("cadenas",cadenas);
        model.addAttribute("capitanes",capitanes);

        return "coordinador/asignacionVoluntarios";
    }

    @PostMapping("/filtrarAsignacionTurnos")
    public String filtrarAsignacionTurnos(@RequestParam(value = "cadenaId",required = false) Integer cadenaId,
                                          @RequestParam(value = "localidad", defaultValue = "") String localidad,
                                          @SessionAttribute(name = "usuario",required = false) Usuario user,
                                         Model model, HttpSession session)
    {
        if (user == null){
            return "redirect:/";
        }
        List<AsignacionTurnoDTO> turnos = null;

        if (cadenaId == null) {
            turnos = this.asignacionTurnoService.findByLocalidad(localidad);
        } else {

            turnos = this.asignacionTurnoService.findByCadenaLocalidad(cadenaId,localidad);
        }
        List<UsuarioTiendaDTO> relaciones = this.usuarioTiendaService.findAll();
        List<CadenaResponseDTO> cadenas = this.cadenaService.findAll();
        List<UsuarioDTO> capitanes = this.usuarioService.findCapitan();

        model.addAttribute("cadenaSelected",cadenaId);
        model.addAttribute("localidadSelected",localidad);
        model.addAttribute("turnos",turnos);
        model.addAttribute("relaciones",relaciones);
        model.addAttribute("cadenas",cadenas);
        model.addAttribute("capitanes",capitanes);

        return "coordinador/asignacionVoluntarios";
    }

    @GetMapping("/asignacionSeleccion")
    public String seleccionarAsignacionTurnos(@RequestParam(value = "id") Integer id,
                                         @SessionAttribute(name = "usuario",required = false) Usuario user,
                                         Model model, HttpSession session)
    {
        if (user == null){
            return "redirect:/";
        }


        AsignacionTurnoDTO turno = this.asignacionTurnoService.findById(id);
        List<ColaboradorResponseDTO> colaboradores = this.colaboradorService.findAll();

        model.addAttribute("turno",turno);
        model.addAttribute("colaboradores",colaboradores);

        return "coordinador/asignacionEditar";
    }

    @PostMapping("/seleccionar")
    public String editarAsignacionTurnos(@RequestParam(value = "id") Integer id,
                                         @RequestParam(value = "colaborador") Integer colaboradorId,
                                         @SessionAttribute(name = "usuario",required = false) Usuario user,
                                         Model model, HttpSession session)
    {
        if (user == null){
            return "redirect:/";
        }


        AsignacionTurnoDTO turno = this.asignacionTurnoService.findById(id);
        ColaboradorRequestDTO colaborador = this.colaboradorService.findById(colaboradorId);
        List<ColaboradorResponseDTO> colaboradores = this.colaboradorService.findAll();
        List<VoluntarioDTO> voluntarios = this.voluntarioService.findAllByColaborador(colaboradorId);


        model.addAttribute("turno",turno);
        model.addAttribute("colaboradorSelected",colaborador);
        model.addAttribute("colaboradores",colaboradores);
        model.addAttribute("voluntarios",voluntarios);


        return "coordinador/asignacionEditar";
    }

    @PostMapping("/guardarEditar")
    public String guardarEditarAsignacionTurnos(@RequestParam(value = "id") Integer id,
                                                @RequestParam(value = "colaboradorId") Integer colaboradorId,
                                                @RequestParam(value = "voluntario") Integer voluntarioId,
                                                @RequestParam(value = "comienzo") LocalTime comienzo,
                                                @RequestParam(value = "fin") LocalTime fin,
                                                @RequestParam(value = "dia") String dia,
                                                @RequestParam(value = "franja") String franja,
                                                @SessionAttribute(name = "usuario",required = false) Usuario user,
                                                Model model, HttpSession session)
    {
        if (user == null){
            return "redirect:/";
        }


        AsignacionTurnoDTO turno = this.asignacionTurnoService.findById(id);
        VoluntarioDTO voluntario = this.voluntarioService.findById(voluntarioId);
        ColaboradorRequestDTO colaborador = this.colaboradorService.findById(colaboradorId);

        turno.setColaboradorRequestDTO(colaborador);
        turno.setVoluntarioDTO(voluntario);
        turno.setHoraInicio(String.valueOf(comienzo));
        turno.setHoraFin(String.valueOf(fin));
        turno.setDia(dia);
        turno.setFranja(franja);

        this.asignacionTurnoService.save(turno);

        return "redirect:/coordinador/asignacionVoluntarios";
    }


    @GetMapping("/nuevoTurno")
    public String crearAsignacionTurnos(@SessionAttribute(name = "usuario",required = false) Usuario user,
                                              Model model, HttpSession session)
    {
        if (user == null){
            return "redirect:/";
        }


        List<ColaboradorResponseDTO> colaboradores = this.colaboradorService.findAll();
        List<TiendaResponseDTO> tiendas = this.tiendaService.ListarTiendas();

        model.addAttribute("colaboradores",colaboradores);
        model.addAttribute("tiendas",tiendas);

        return "coordinador/nuevoTurno";
    }

    @PostMapping("/seleccionarNuevo")
    public String seleccionarNuevaAsignacionTurnos(@RequestParam(value = "colaborador") Integer colaboradorId,
                                                   @RequestParam(value = "tienda") Integer tiendaId,
                                                   @SessionAttribute(name = "usuario",required = false) Usuario user,
                                        Model model, HttpSession session)
    {
        if (user == null){
            return "redirect:/";
        }

        ColaboradorRequestDTO colaboradorSelected = this.colaboradorService.findById(colaboradorId);
        TiendaRequestDTO tiendaSelected = this.tiendaService.findTiendaById(tiendaId);
        List<ColaboradorResponseDTO> colaboradores = this.colaboradorService.findAll();
        List<TiendaResponseDTO> tiendas = this.tiendaService.ListarTiendas();
        List<VoluntarioDTO> voluntarios = this.voluntarioService.findAllByColaborador(colaboradorId);
        List<UsuarioDTO> capitanes = this.usuarioService.findCapitan();
        List<CampanaResponseDTO> campanas = this.campanaService.findByCadena(tiendaSelected.getIdCadena());

        model.addAttribute("colaboradores",colaboradores);
        model.addAttribute("tiendas",tiendas);
        model.addAttribute("colaboradorSelected",colaboradorSelected);
        model.addAttribute("voluntarios",voluntarios);
        model.addAttribute("tiendaSelected",tiendaSelected);
        model.addAttribute("capitanes",capitanes);
        model.addAttribute("campanas",campanas);

        return "coordinador/nuevoTurno";
    }



    @PostMapping("/guardarTurnoNuevo")
    public String guardarNuevaAsignacionTurnos(@RequestParam(value = "tiendaId") Integer tiendaId,
                                               @RequestParam(value = "colaboradorId") Integer colaboradorId,
                                               @RequestParam(value = "campana") Integer campanaId,
                                               @RequestParam(value = "capitan") Integer capitanId,
                                               @RequestParam(value = "voluntario") Integer voluntarioId,
                                               @RequestParam(value = "comienzo") LocalTime comienzo,
                                               @RequestParam(value = "fin") LocalTime fin,
                                               @RequestParam(value = "dia") String dia,
                                               @RequestParam(value = "franja") String franja,
                                               @SessionAttribute(name = "usuario",required = false) Usuario user,
                                               Model model, HttpSession session)
    {
        if (user == null){
            return "redirect:/";
        }

        TiendaRequestDTO tienda = this.tiendaService.findTiendaById(tiendaId);
        VoluntarioDTO voluntario = this.voluntarioService.findById(voluntarioId);
        ColaboradorRequestDTO colaborador = this.colaboradorService.findById(colaboradorId);
        UsuarioDTO capitan = this.usuarioService.findById(capitanId);
        CampanaResponseDTO campana = this.campanaService.findById(campanaId);

        AsignacionTurnoDTO turno = new AsignacionTurnoDTO();

        turno.setColaboradorRequestDTO(colaborador);
        turno.setVoluntarioDTO(voluntario);
        turno.setTiendaResponseDTO(tienda);
        turno.setCampanaResponseDTO(campana);
        turno.setHoraInicio(String.valueOf(comienzo));
        turno.setHoraFin(String.valueOf(fin));
        turno.setDia(dia);
        turno.setFranja(franja);


        UsuarioTiendaId claveCompuesta = new UsuarioTiendaId();
        claveCompuesta.setIdUsuario(capitan.getId());
        claveCompuesta.setIdTienda(tiendaId);


        UsuarioTiendaDTO relacion = new UsuarioTiendaDTO();relacion.setId(claveCompuesta);
        relacion.setUsuarioId(capitan.getId());
        relacion.setTiendaId(tiendaId);

        this.asignacionTurnoService.save(turno);
        this.usuarioTiendaService.save(relacion);

        return "redirect:/coordinador/asignacionVoluntarios";
    }

}
