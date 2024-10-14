package com.carlosaltan.bookingSystem.service;
import java.util.List;
import java.util.Optional;
import com.carlosaltan.bookingSystem.model.Usuario;

public interface IUsuarioService {

    Usuario createUsuario(Usuario usuario);
    List<Usuario> getAllUsuarios();

}
