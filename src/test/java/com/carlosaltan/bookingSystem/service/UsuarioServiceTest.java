package com.carlosaltan.bookingSystem.service;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.carlosaltan.bookingSystem.model.Usuario;
import com.carlosaltan.bookingSystem.repository.UserReposiroty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

class UsuarioServiceTest {

    @Mock
    private UserReposiroty usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUsuario() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario createdUsuario = usuarioService.createUsuario(usuario);

        assertNotNull(createdUsuario);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testSearchUsuario() {
        String usuarioId = "123";
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        Optional<Usuario> foundUsuario = usuarioService.searchUsuario(usuarioId);

        assertTrue(foundUsuario.isPresent());
        assertEquals(usuarioId, foundUsuario.get().getId());
        verify(usuarioRepository, times(1)).findById(usuarioId);
    }


    @Test
    void testUpdateUsuario() {
        String usuarioId = "123";
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);

        when(usuarioRepository.existsById(usuarioId)).thenReturn(true);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario updatedUsuario = usuarioService.updateUsuario(usuarioId, usuario);

        assertEquals(usuarioId, updatedUsuario.getId());
        verify(usuarioRepository, times(1)).existsById(usuarioId);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testUpdateUsuario_NotFound() {
        String usuarioId = "123";
        Usuario usuario = new Usuario();
        when(usuarioRepository.existsById(usuarioId)).thenReturn(false);

        Usuario updatedUsuario = usuarioService.updateUsuario(usuarioId, usuario);

        assertNull(updatedUsuario);
        verify(usuarioRepository, times(1)).existsById(usuarioId);
        verify(usuarioRepository, never()).save(usuario);
    }

    @Test
    void testDeleteUsuario() {
        String usuarioId = "123";

        // No verificamos si existe, solo llamamos al método de eliminación
        usuarioService.deleteUsuario(usuarioId);

        verify(usuarioRepository, times(1)).deleteById(usuarioId);
    }

    @Test
    void testGetAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario());
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> allUsuarios = usuarioService.getAllUsuarios();

        assertEquals(1, allUsuarios.size());
        verify(usuarioRepository, times(1)).findAll();
    }
}