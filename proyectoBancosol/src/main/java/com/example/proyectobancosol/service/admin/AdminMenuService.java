package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dto.response.AdminMenuItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMenuService {

    public List<AdminMenuItemDTO> obtenerMenuAdministrador() {
        return List.of(
                item(
                        "Cadenas",
                        "Gestion de cadenas de supermercados",
                        "/admin/cadenas"),
                item(
                        "Campanas",
                        "Gestion de campanas y cadenas participantes",
                        "/admin/campanas"),
                item(
                        "Tiendas",
                        "Gestion de tiendas",
                        "/admin/tiendas"),
                item(
                        "Coordinadores",
                        "Gestion de coordinadores",
                        "/admin/coordinadores"),
                
                item(
                        "Colaboradores",
                        "Gestion de colaboradores",
                        "/admin/colaboradores"),

                item(
                        "Asignacion de voluntarios",
                        "Gestion de asignaciones creada en el modulo coordinador",
                        "/coordinador/asignacionVoluntarios"),
                item(
                        "Tiendas asignadas(Capitan)",
                        "Vista de tiendas creada en el modulo capitan",
                        "/capitan"),
                item(
                        "Responsable entidad",
                        "Menu creado para responsable de entidad",
                        "/resp-entidad"),
                item(
                        "Responsable tienda",
                        "Menu creado para responsable de tienda",
                        "/resp-tienda")
        );
    }

    private AdminMenuItemDTO item(String titulo, String descripcion, String url) {
        return new AdminMenuItemDTO(titulo, descripcion, url);
    }
}