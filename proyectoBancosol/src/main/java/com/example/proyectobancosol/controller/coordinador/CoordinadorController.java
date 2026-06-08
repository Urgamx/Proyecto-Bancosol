package com.example.proyectobancosol.controller.coordinador;

import com.example.proyectobancosol.entity.Colaborador;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.entity.UsuarioColaborador;
import com.example.proyectobancosol.service.coordinador.ColaboradorService;
import com.example.proyectobancosol.service.coordinador.UsuarioColaboradorService;
import com.example.proyectobancosol.service.coordinador.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/coordinador")
public class CoordinadorController {

    private final ColaboradorService colaboradorService;
    private final UsuarioColaboradorService usuarioColaboradorService;
    private final UsuarioService usuarioService;

    @GetMapping("/")
    public String home(@SessionAttribute(name = "usuario",required = false) Usuario user,
                       HttpSession session)
    {
        if (user == null){
            return "redirect:/login";
        }

        return "coordinador/homeCoordinador";

    }

    @GetMapping("/colaborador")
    public String listarColaboradores(@SessionAttribute(name = "usuario",required = false) Usuario user,
                         Model model, HttpSession session)
    {

        if (user == null){
            return "redirect:/login";
        }

        List<Colaborador> colaboradores = this.colaboradorService.findAllActivos();
        List<UsuarioColaborador> relaciones = this.usuarioColaboradorService.findAll();
        List<Usuario> coordinadores = this.usuarioService.findCoordinador();
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
            return "redirect:/login";
        }

        List<Colaborador> colaboradores = null;
        if (coordinadorId == null) {
            colaboradores = this.usuarioColaboradorService.findByZonaLocalidad(zonaGeo,localidad);
        } else {
            colaboradores = this.usuarioColaboradorService.findByZonaLocalidadCoorId(zonaGeo,localidad,coordinadorId);
        }
        List<UsuarioColaborador> relaciones = this.usuarioColaboradorService.findAll();
        List<Usuario> coordinadores = this.usuarioService.findCoordinador();
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
            return "redirect:/login";
        }

        Colaborador colaborador = this.colaboradorService.findById(id);
        model.addAttribute("colaborador",colaborador);
        return "coordinador/editarColaborador";
    }

    @GetMapping("/nuevoColaborador")
    public String nuevoColaborador(@SessionAttribute(name = "usuario",required = false) Usuario user,
                                   HttpSession session)
    {
        if (user == null){
            return "redirect:/login";
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
            return "redirect:/login";
        }

        Colaborador colaborador = null;

        if (id != null) {
            colaborador = this.colaboradorService.findById(id);
        } else {
            colaborador = new Colaborador();
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
            UsuarioColaborador relacion = new UsuarioColaborador();
            relacion.setColaborador(colaborador);
            relacion.setUsuario(user);
            this.usuarioColaboradorService.save(relacion);
        }

        return "redirect:/coordinador/colaborador";
    }

    @GetMapping("/asignacionVoluntarios")
    public String listarAsignacionTurnos(@SessionAttribute(name = "usuario",required = false) Usuario user,
                                          Model model, HttpSession session)
    {
        if (user == null){
            return "redirect:/login";
        }

        return "coordinador/asignacionVoluntarios";
    }

}
