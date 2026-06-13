package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.ColaboradorRepository;
import com.example.proyectobancosol.dto.request.ColaboradorRequestDTO;
import com.example.proyectobancosol.dto.response.ColaboradorResponseDTO;
import com.example.proyectobancosol.entity.Colaborador;
import com.example.proyectobancosol.mapper.admin.ColaboradorAdminMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ColaboradorService {

    private final ColaboradorAdminMapper colaboradorAdminMapper;
    private final ColaboradorRepository colaboradorRepository;

    public List<ColaboradorResponseDTO> findAll() {
        return colaboradorAdminMapper.toDTOList(this.colaboradorRepository.findAll());
    }

    public List<ColaboradorResponseDTO> findAllActivos() {

        return colaboradorAdminMapper.toDTOList(this.colaboradorRepository.findAllActivos());
    }

    public ColaboradorRequestDTO findById(Integer id) {
        return colaboradorAdminMapper.toRequestDTO(this.colaboradorRepository.findById(id).get());}

    public void save(ColaboradorRequestDTO dto){
        Colaborador colaborador = this.colaboradorRepository.findById(dto.getId()).orElse(new Colaborador());

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

        this.colaboradorRepository.save(colaborador);
    }
}
