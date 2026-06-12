package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() { return this.usuarioRepository.findAll(); }

    public Usuario findById(Integer id) { return this.usuarioRepository.findById(id).get(); }

    public List<Usuario> findCoordinador() { return usuarioRepository.findCoordinador(); }

    public List<Usuario> findCapitan() { return usuarioRepository.findCapitan(); }

}
