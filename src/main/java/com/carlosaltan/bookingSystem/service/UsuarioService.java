package com.carlosaltan.bookingSystem.service;
import com.carlosaltan.bookingSystem.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService{
    private final Map<Long, Usuario> usuarios = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public Usuario createUsuario(Usuario usuario) {
        usuario.setId(currentId++);
        usuarios.put(usuario.getId(), usuario);
        System.out.println(usuario);
        return usuario;
    }

    @Override
    public Optional<Usuario> searchUsuario(Long id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    @Override
    public Usuario updateUsuario(Long id, Usuario usuario) {
        if (usuarios.containsKey(id)) {
            usuario.setId(id);
            usuarios.put(id, usuario);
            return usuario;
        }
        return null;
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarios.remove(id);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

}
