package com.example.ClinicaOdontologicaC3.service;

import com.example.ClinicaOdontologicaC3.Entity.Odontologo;
import com.example.ClinicaOdontologicaC3.Repository.OdontologoRepository;
import com.example.ClinicaOdontologicaC3.Service.OdontologoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OdontologoServiceTest {

    @Mock
    private OdontologoRepository odontologoRepository;

    @InjectMocks
    private OdontologoService odontologoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarOdontologo() {
        Odontologo odontologo = new Odontologo();
        odontologo.setMatricula("12345");

        when(odontologoRepository.save(any(Odontologo.class))).thenReturn(odontologo);

        Odontologo result = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(result);
        assertEquals("12345", result.getMatricula());
        verify(odontologoRepository, times(1)).save(odontologo);
    }

    @Test
    void testBuscarPorId() {
        Long id = 1L;
        Odontologo odontologo = new Odontologo();
        odontologo.setId(id);

        when(odontologoRepository.findById(id)).thenReturn(Optional.of(odontologo));

        Optional<Odontologo> result = odontologoService.buscarPorId(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        verify(odontologoRepository, times(1)).findById(id);
    }

    @Test
    void testBuscarPorIdNoEncontrado() {
        Long id = 1L;

        when(odontologoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Odontologo> result = odontologoService.buscarPorId(id);

        assertFalse(result.isPresent());
        verify(odontologoRepository, times(1)).findById(id);
    }

    @Test
    void testListarTodos() {
        List<Odontologo> odontologos = Arrays.asList(new Odontologo(), new Odontologo());

        when(odontologoRepository.findAll()).thenReturn(odontologos);

        List<Odontologo> result = odontologoService.listarTodos();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(odontologoRepository, times(1)).findAll();
    }

    @Test
    void testEliminarOdontologo() {
        Long id = 1L;

        doNothing().when(odontologoRepository).deleteById(id);

        odontologoService.eliminarOdontologo(id);

        verify(odontologoRepository, times(1)).deleteById(id);
    }
}