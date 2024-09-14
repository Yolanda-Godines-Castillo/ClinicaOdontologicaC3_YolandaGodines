package com.example.ClinicaOdontologicaC3.service;

import com.example.ClinicaOdontologicaC3.Entity.Domicilio;
import com.example.ClinicaOdontologicaC3.Entity.Paciente;
import com.example.ClinicaOdontologicaC3.Repository.PacienteRepository;
import com.example.ClinicaOdontologicaC3.Service.PacienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarPaciente() {
        Paciente paciente = new Paciente();
        paciente.setEmail("test@example.com");

        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        Paciente result = pacienteService.registrarPaciente(paciente);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        verify(pacienteRepository, times(1)).save(paciente);
    }

    @Test
    void testActualizarPaciente() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setEmail("updated@example.com");

        pacienteService.actualizarPaciente(paciente);

        verify(pacienteRepository, times(1)).save(paciente);
    }

    @Test
    void testEliminarPaciente() {
        Long id = 1L;

        pacienteService.eliminarPaciente(id);

        verify(pacienteRepository, times(1)).deleteById(id);
    }

    @Test
    void testBuscarPorId() {
        Long id = 1L;
        Paciente paciente = new Paciente();
        paciente.setId(id);
        paciente.setEmail("test@example.com");

        when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));

        Optional<Paciente> result = pacienteService.buscarPorId(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals("test@example.com", result.get().getEmail());
        verify(pacienteRepository, times(1)).findById(id);
    }

    @Test
    void testBuscarPorIdNoEncontrado() {
        Long id = 1L;

        when(pacienteRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Paciente> result = pacienteService.buscarPorId(id);

        assertFalse(result.isPresent());
        verify(pacienteRepository, times(1)).findById(id);
    }

    @Test
    void testBuscarPorEmail() {
        String email = "test@example.com";
        Paciente paciente = new Paciente();
        paciente.setEmail(email);

        when(pacienteRepository.findByEmail(email)).thenReturn(Optional.of(paciente));

        Optional<Paciente> result = pacienteService.buscarPorEmail(email);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
        verify(pacienteRepository, times(1)).findByEmail(email);
    }

    @Test
    void testBuscarPorEmailNoEncontrado() {
        String email = "nonexistent@example.com";

        when(pacienteRepository.findByEmail(email)).thenReturn(Optional.empty());

        Optional<Paciente> result = pacienteService.buscarPorEmail(email);

        assertFalse(result.isPresent());
        verify(pacienteRepository, times(1)).findByEmail(email);
    }
}