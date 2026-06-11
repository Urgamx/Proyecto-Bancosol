package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dto.response.AdminMenuItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMenuService {

    public List<AdminMenuItemDTO> obtenerMenuAdministrador() {
        return List.of(
                new AdminMenuItemDTO(
                        "Cadenas",
                        "Gestion de cadenas de supermercados",
                        "/admin/cadenas",
                        "Disponible"
                ),
                new AdminMenuItemDTO(
                        "Campanas",
                        "Gestion de campanas y cadenas participantes",
                        "/admin/campanas",
                        "Disponible"
                ),
                new AdminMenuItemDTO(
                        "Tiendas",
                        "Gestion de tiendas",
                        "/admin/tiendas",
                        "Disponible"
                ),
                new AdminMenuItemDTO(
                        "Coordinadores",
                        "Gestion de coordinadores",
                        "/admin/coordinadores",
                        "Disponible"
                ),
                new AdminMenuItemDTO(
                        "Asignar tiendas",
                        "Asignacion de tiendas a coordinadores",
                        "/admin/asignar-tiendas",
                        "Disponible"
                ),
                new AdminMenuItemDTO(
                        "Colaboradores",
                        "Gestion de colaboradores",
                        "/admin/colaboradores",
                        "Disponible"
                ),
                new AdminMenuItemDTO(
                        "Asignar colaboradores",
                        "Asignacion de colaboradores a coordinadores",
                        "/admin/asignar-colaboradores",
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
                )
        );
    }
}