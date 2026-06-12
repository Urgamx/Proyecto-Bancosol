package com.example.proyectobancosol.mapper.admin;

import com.example.proyectobancosol.dto.response.ValidacionBasicaResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacionAdminMapper {

    public ValidacionBasicaResponseDTO toDTO(String modulo, String validacion, List<String> elementos) {
        Long total = (long) elementos.size();
        return new ValidacionBasicaResponseDTO(
                modulo,
                validacion,
                total,
                total == 0 ? "OK" : "Revisar",
                total == 0 ? "Sin incidencias" : String.join(", ", elementos)
        );
    }
}