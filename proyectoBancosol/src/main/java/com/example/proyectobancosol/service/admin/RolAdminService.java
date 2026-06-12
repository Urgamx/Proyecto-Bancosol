package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.RolRepository;
import com.example.proyectobancosol.entity.Rol;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RolAdminService {

    private final RolRepository rolRepository;

    public List<Rol> findAll() {return this.rolRepository.findAll(); }

    public Rol findById(Integer id) {return this.rolRepository.findById(id).get(); }
}
