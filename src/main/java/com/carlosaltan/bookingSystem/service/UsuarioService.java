package com.carlosaltan.bookingSystem.service;
import com.carlosaltan.bookingSystem.model.Usuario;
import com.carlosaltan.bookingSystem.repository.UserReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private UserReposiroty usuarioRepository;

    @Override
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    public Optional<Usuario> searchUsuario(String id) {
        return usuarioRepository.findById(id);
    }

    public Usuario updateUsuario(String id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        }
        return null;
    }


    public void deleteUsuario(String id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

}
