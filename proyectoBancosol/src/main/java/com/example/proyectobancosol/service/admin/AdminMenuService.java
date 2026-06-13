package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dto.response.AdminMenuItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMenuService {

    public List<AdminMenuItemDTO> obtenerMenuAdministrador() {
        return List.of(
                item(
                        "Usuarios",
                        "Gestion de usuarios del sistema",
                        "/admin/usuarios"),
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
                        "Capitanes",
                        "Gestion de capitanes",
                        "/admin/capitanes"),
                item(
                        "Responsable entidad",
                        "Menu creado para responsable de entidad",
                        "/admin/resp-entidad"),
                item(
                        "Responsable tienda",
                        "Menu creado para responsable de tienda",
                        "/admin/resp-tienda")
        );
    }

    private AdminMenuItemDTO item(String titulo, String descripcion, String url) {
        return new AdminMenuItemDTO(titulo, descripcion, url);
    }
}