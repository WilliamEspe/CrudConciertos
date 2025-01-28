package com.example.gestion_conciertos.service;

import com.example.gestion_conciertos.model.Cancion;
import com.example.gestion_conciertos.repository.CancionRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class CancionService {

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private Validator validator;



    public List<Cancion> obtenerTodasCanciones() {
        return cancionRepository.findAll();
    }

    public Cancion obtenerCancionPorId(Long id) {
        return cancionRepository.findById(id).orElse(null);
    }

    public Cancion crearCancion(Cancion cancion) {
        // Verificar si ya existe una canción con el mismo título
        if (cancionRepository.existsByTitulo(cancion.getTitulo())) {
            throw new RuntimeException("La canción con este título ya existe");
        }
        return cancionRepository.save(cancion);
    }

    public Cancion actualizarCancion(Long id, Cancion cancion) {
        if (cancionRepository.existsById(id)) {
            cancion.setId(id);
            // Validar manualmente antes de actualizar
            Set<ConstraintViolation<Cancion>> violations = validator.validate(cancion);
            if (!violations.isEmpty()) {
                StringBuilder errorMessage = new StringBuilder();
                for (ConstraintViolation<Cancion> violation : violations) {
                    errorMessage.append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("\n");
                }
                throw new ConstraintViolationException(errorMessage.toString(), violations);
            }
            return cancionRepository.save(cancion);
        }
        return null;
    }

    public void eliminarCancion(Long id) {
        cancionRepository.deleteById(id);
    }


}
