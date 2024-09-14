package com.example.ClinicaOdontologicaC3.Service;

import com.example.ClinicaOdontologicaC3.Dto.TurnoDTO;
import com.example.ClinicaOdontologicaC3.Entity.Turno;
import com.example.ClinicaOdontologicaC3.Repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    private static final Logger logger = LoggerFactory.getLogger(TurnoService.class);

    public TurnoDTO registrarTurno(Turno turno) {
        logger.info("Registrando nuevo turno para paciente con ID: {} y odontólogo con ID: {}", 
                     turno.getPaciente().getId(), turno.getOdontologo().getId());

        Turno turnoGuardado = turnoRepository.save(turno);
        TurnoDTO turnoDTO = turnoATurnoDto(turnoGuardado);

        logger.info("Turno registrado exitosamente con ID: {}", turnoDTO.getId());
        return turnoDTO;
    }

    public List<TurnoDTO> listarTodos() {
        logger.info("Listando todos los turnos.");

        List<Turno> listaTurno = turnoRepository.findAll();
        List<TurnoDTO> listaDTO = new ArrayList<>();

        for (Turno turno : listaTurno) {
            listaDTO.add(turnoATurnoDto(turno));
            logger.debug("Turno añadido a la lista DTO: ID turno: {}", turno.getId());
        }

        logger.info("Se listaron un total de {} turnos.", listaDTO.size());
        return listaDTO;
    }

    private TurnoDTO turnoATurnoDto(Turno turno) {
        logger.debug("Convirtiendo Turno con ID: {} a TurnoDTO.", turno.getId());

        TurnoDTO dto = new TurnoDTO();
        dto.setId(turno.getId());
        dto.setFecha(turno.getFecha());
        dto.setOdontologoId(turno.getOdontologo().getId());
        dto.setPacienteId(turno.getPaciente().getId());

        return dto;
    }

    public void actualizarTurno(Turno turno) {
        logger.info("Actualizando turno con ID: {}", turno.getId());
        turnoRepository.save(turno);
        logger.info("Turno con ID: {} actualizado exitosamente.", turno.getId());
    }

    public void eliminarTurno(Long id) {
        logger.info("Eliminando turno con ID: {}", id);
        turnoRepository.deleteById(id);
        logger.info("Turno con ID: {} eliminado exitosamente.", id);
    }
}
