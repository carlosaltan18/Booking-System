package com.carlosaltan.bookingSystem.service;
import java.util.List;
import java.util.Optional;
import com.carlosaltan.bookingSystem.model.Usuario;

public interface IUsuarioService {

    Usuario createUsuario(Usuario usuario);
    Optional<Usuario> searchUsuario(Long id);
    Usuario updateUsuario(Long id, Usuario usuario);
    void deleteUsuario(Long id);
    List<Usuario> getAllUsuarios();

}
