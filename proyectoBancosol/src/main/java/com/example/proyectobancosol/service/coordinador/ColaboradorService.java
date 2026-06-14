package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.ColaboradorRepository;
import com.example.proyectobancosol.dao.UsuarioColaboradorRepository;
import com.example.proyectobancosol.dto.request.ColaboradorRequestDTO;
import com.example.proyectobancosol.dto.response.ColaboradorResponseDTO;
import com.example.proyectobancosol.dto.response.UsuarioColaboradorDTO;
import com.example.proyectobancosol.entity.Colaborador;
import com.example.proyectobancosol.mapper.admin.ColaboradorAdminMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase que representa la clase AsignacionTurnoService.
 *
 * Autores:
 * - IA Generatiova: 50%
 * - David Vilaseca Pareja: 50%
 *
 */

@Service
@AllArgsConstructor
public class ColaboradorService {

    private final ColaboradorAdminMapper colaboradorAdminMapper;
    private final ColaboradorRepository colaboradorRepository;
    private final UsuarioColaboradorRepository usuarioColaboradorRepository;

    public List<ColaboradorResponseDTO> findAll() {
        return colaboradorAdminMapper.toDTOList(this.colaboradorRepository.findAll());
    }

    public List<ColaboradorResponseDTO> findAllActivos() {

        return colaboradorAdminMapper.toDTOList(this.colaboradorRepository.findAllActivos());
    }

    public ColaboradorRequestDTO findById(Integer id) {
        return colaboradorAdminMapper.toRequestDTO(this.colaboradorRepository.findById(id).get());}

    public void save(ColaboradorRequestDTO dto){
        Colaborador colaborador;

        // 1. Evitamos pasar un ID nulo a findById comprobando si ya existe
        if (dto.getId() != null) {
            colaborador = this.colaboradorRepository.findById(dto.getId()).orElse(new Colaborador());
        } else {
            colaborador = new Colaborador(); // Si es nuevo, instanciamos directamente
        }

        colaborador.setNombreEntidad(dto.getNombreEntidad());
        colaborador.setEmail(dto.getEmail());
        colaborador.setContactoNom(dto.getContactoNom());
        colaborador.setContactoTlf(dto.getContactoTlf());
        colaborador.setDomicilio(dto.getDomicilio());
        colaborador.setLocalidad(dto.getLocalidad());
        colaborador.setZonaGeografica(dto.getZonaGeografica());
        colaborador.setObservaciones(dto.getObservaciones());
        colaborador.setCodPostal(dto.getCodPostal());
        colaborador.setEstado(dto.getEstado());

        // 2. Guardamos en el repositorio. Aquí Hibernate puebla el ID autogenerado en la entidad.
        this.colaboradorRepository.save(colaborador);

        // 3. Seteamos el ID asignado por la BD de vuelta en el DTO recibido por parámetro
        dto.setId(colaborador.getId());
    }
}
