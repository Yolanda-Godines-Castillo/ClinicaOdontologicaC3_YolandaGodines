package com.example.ClinicaOdontologicaC3.Service;

import com.example.ClinicaOdontologicaC3.Entity.Odontologo;
import com.example.ClinicaOdontologicaC3.Repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class OdontologoService {
    private static final Logger logger = Logger.getLogger(OdontologoService.class.getName());

    @Autowired
    private OdontologoRepository odontologoRepository;

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        logger.info("Iniciando registro de nuevo odontólogo con matrícula: " + odontologo.getMatricula());
        Odontologo odontologoRegistrado = odontologoRepository.save(odontologo);
        logger.info("Odontólogo registrado exitosamente con ID: " + odontologoRegistrado.getId());
        return odontologoRegistrado;
    }

    public Optional<Odontologo> buscarPorId(Long id) {
        logger.info("Buscando odontólogo por ID: " + id);
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if (odontologo.isPresent()) {
            logger.info("Odontólogo encontrado con ID: " + id);
        } else {
            logger.warning("No se encontró odontólogo con ID: " + id);
        }
        return odontologo;
    }

    public List<Odontologo> listarTodos() {
        logger.info("Obteniendo lista de todos los odontólogos");
        List<Odontologo> odontologos = odontologoRepository.findAll();
        logger.info("Se encontraron " + odontologos.size() + " odontólogos");
        return odontologos;
    }

    public void eliminarOdontologo(Long id) {
        logger.warning("Eliminando odontólogo con ID: " + id);
        odontologoRepository.deleteById(id);
        logger.info("Odontólogo eliminado correctamente");
    }
}