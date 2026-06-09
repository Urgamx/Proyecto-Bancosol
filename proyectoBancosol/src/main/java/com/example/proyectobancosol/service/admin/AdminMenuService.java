package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dto.response.AdminMenuItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMenuService {

    public List<AdminMenuItemDTO> obtenerMenuAdministrador() {
        return List.of(
                new AdminMenuItemDTO(
                        "Colaboradores",
                        "Gestion de colaboradores creada en el modulo coordinador",
                        "/coordinador/colaborador",
                        "Disponible"
                ),
                new AdminMenuItemDTO(
                        "Asignacion de voluntarios",
                        "Gestion de asignaciones creada en el modulo coordinador",
                        "/coordinador/asignacionVoluntarios",
                        "Disponible"
                ),
                new AdminMenuItemDTO(
                        "Tiendas asignadas",
                        "Vista de tiendas creada en el modulo capitan",
                        "/capitan",
                        "Disponible"
                ),
                new AdminMenuItemDTO(
                        "Responsable entidad",
                        "Menu creado para responsable de entidad",
                        "/resp-entidad",
                        "Disponible"
                ),
                new AdminMenuItemDTO(
                        "Responsable tienda",
                        "Menu creado para responsable de tienda",
                        "/resp-tienda",
                        "Disponible"
                ),
                new AdminMenuItemDTO(
                        "Cadenas",
                        "Modulo pendiente de implementar por administrador",
                        "#",
                        "Pendiente"
                ),
                new AdminMenuItemDTO(
                        "Campanas",
                        "Modulo pendiente de implementar por administrador",
                        "#",
                        "Pendiente"
                ),
                new AdminMenuItemDTO(
                        "Coordinadores",
                        "Modulo pendiente de implementar por administrador",
                        "#",
                        "Pendiente"
                ),
                new AdminMenuItemDTO(
                        "Asignar tiendas",
                        "Modulo pendiente de implementar por administrador",
                        "#",
                        "Pendiente"
                ),
                new AdminMenuItemDTO(
                        "Validaciones",
                        "Modulo pendiente de implementar por administrador",
                        "#",
                        "Pendiente"
                )
        );
    }
}