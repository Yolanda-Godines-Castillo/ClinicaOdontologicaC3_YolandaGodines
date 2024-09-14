package com.example.ClinicaOdontologicaC3.service;

import com.example.ClinicaOdontologicaC3.Dto.TurnoDTO;
import com.example.ClinicaOdontologicaC3.Entity.Turno;
import com.example.ClinicaOdontologicaC3.Entity.Paciente;
import com.example.ClinicaOdontologicaC3.Entity.Odontologo;
import com.example.ClinicaOdontologicaC3.Repository.TurnoRepository;
import com.example.ClinicaOdontologicaC3.Service.TurnoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TurnoServiceTest {

    @Mock
    private TurnoRepository turnoRepository;

    @InjectMocks
    private TurnoService turnoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarTurno() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        Odontologo odontologo = new Odontologo();
        odontologo.setId(1L);
        Turno turno = new Turno();
        turno.setId(1L);
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.now());

        when(turnoRepository.save(any(Turno.class))).thenReturn(turno);

        TurnoDTO result = turnoService.registrarTurno(turno);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getPacienteId());
        assertEquals(1L, result.getOdontologoId());
        verify(turnoRepository, times(1)).save(turno);
    }

    @Test
    void testListarTodos() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        Odontologo odontologo = new Odontologo();
        odontologo.setId(1L);
        Turno turno1 = new Turno();
        turno1.setId(1L);
        turno1.setPaciente(paciente);
        turno1.setOdontologo(odontologo);
        turno1.setFecha(LocalDate.now());
        Turno turno2 = new Turno();
        turno2.setId(2L);
        turno2.setPaciente(paciente);
        turno2.setOdontologo(odontologo);
        turno2.setFecha(LocalDate.now());

        List<Turno> turnos = Arrays.asList(turno1, turno2);

        when(turnoRepository.findAll()).thenReturn(turnos);

        List<TurnoDTO> result = turnoService.listarTodos();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
        verify(turnoRepository, times(1)).findAll();
    }

    @Test
    void testActualizarTurno() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        Odontologo odontologo = new Odontologo();
        odontologo.setId(1L);
        Turno turno = new Turno();
        turno.setId(1L);
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.now());

        turnoService.actualizarTurno(turno);

        verify(turnoRepository, times(1)).save(turno);
    }

    @Test
    void testEliminarTurno() {
        Long id = 1L;

        turnoService.eliminarTurno(id);

        verify(turnoRepository, times(1)).deleteById(id);
    }
}