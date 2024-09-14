package com.example.ClinicaOdontologicaC3.service;

import com.example.ClinicaOdontologicaC3.Entity.Usuario;
import com.example.ClinicaOdontologicaC3.Repository.UsuarioRepository;
import com.example.ClinicaOdontologicaC3.Service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_UsuarioExistente() {
        String email = "test@example.com";
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setPassword("password");

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

        UserDetails result = usuarioService.loadUserByUsername(email);

        assertNotNull(result);
        assertEquals(email, result.getUsername());
        verify(usuarioRepository, times(1)).findByEmail(email);
    }

    @Test
    void testLoadUserByUsername_UsuarioNoExistente() {
        String email = "nonexistent@example.com";

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            usuarioService.loadUserByUsername(email);
        });

        verify(usuarioRepository, times(1)).findByEmail(email);
    }
}