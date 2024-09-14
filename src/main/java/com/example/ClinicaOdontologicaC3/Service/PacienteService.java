package com.example.ClinicaOdontologicaC3.Service;

import com.example.ClinicaOdontologicaC3.Entity.Paciente;
import com.example.ClinicaOdontologicaC3.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PacienteService {
    private static final Logger logger = Logger.getLogger(PacienteService.class.getName());

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente registrarPaciente(Paciente paciente) {
        logger.info("Iniciando registro de nuevo paciente: " + paciente.getEmail());
        Paciente pacienteRegistrado = pacienteRepository.save(paciente);
        logger.info("Paciente registrado exitosamente con ID: " + pacienteRegistrado.getId());
        return pacienteRegistrado;
    }

    public void actualizarPaciente(Paciente paciente) {
        logger.info("Actualizando información del paciente con ID: " + paciente.getId());
        pacienteRepository.save(paciente);
        logger.info("Información del paciente actualizada correctamente");
    }

    public void eliminarPaciente(Long id) {
        logger.warning("Eliminando paciente con ID: " + id);
        pacienteRepository.deleteById(id);
        logger.info("Paciente eliminado correctamente");
    }

    public Optional<Paciente> buscarPorId(Long id) {
        logger.info("Buscando paciente por ID: " + id);
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            logger.info("Paciente encontrado: " + paciente.get().getEmail());
        } else {
            logger.warning("No se encontró paciente con ID: " + id);
        }
        return paciente;
    }

    public Optional<Paciente> buscarPorEmail(String email) {
        logger.info("Buscando paciente por email: " + email);
        Optional<Paciente> paciente = pacienteRepository.findByEmail(email);
        if (paciente.isPresent()) {
            logger.info("Paciente encontrado con email: " + email);
        } else {
            logger.warning("No se encontró paciente con email: " + email);
        }
        return paciente;
    }

    public List<Paciente> listarTodos() {
        logger.info("Obteniendo lista de todos los pacientes");
        List<Paciente> pacientes = pacienteRepository.findAll();
        logger.info("Se encontraron " + pacientes.size() + " pacientes");
        return pacientes;
    }
}