package com.example.gestion_conciertos.service;

import com.example.gestion_conciertos.model.Cancion;
import com.example.gestion_conciertos.model.Concierto;
import com.example.gestion_conciertos.model.ConciertosCanciones;
import com.example.gestion_conciertos.repository.CancionRepository;
import com.example.gestion_conciertos.repository.ConciertoRepository;
import com.example.gestion_conciertos.repository.ConciertosCancionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ConciertoService {

    @Autowired
    private ConciertoRepository conciertoRepository;
    @Autowired
    private CancionRepository cancionRepository;
    @Autowired
    private ConciertosCancionesRepository conciertosCancionesRepository;

    public List<Cancion> obtenerCancionesDeConcierto(Long conciertoId) {
        List<ConciertosCanciones> conciertosCancionesList = conciertosCancionesRepository.findByConciertoId(conciertoId);
        List<Cancion> canciones = new ArrayList<>();
        for (ConciertosCanciones conciertosCanciones : conciertosCancionesList) {
            canciones.add(conciertosCanciones.getCancion());
        }
        return canciones;
    }

    public List<Concierto> obtenerTodosConciertos() {
        return conciertoRepository.findAll();
    }

    public Optional<Concierto> obtenerConciertoPorId(Long conciertoId) {
        return conciertoRepository.findById(conciertoId);
    }

    public Optional<Cancion> obtenerCancionPorId(Long cancionId) {
        return cancionRepository.findById(cancionId);
    }

    public Concierto crearConcierto(Concierto concierto) {
        
        if (conciertoRepository.existsByNombre(concierto.getNombre())) {
            throw new RuntimeException("El concierto con este nombre ya existe");
        }
        return conciertoRepository.save(concierto);
    }

    public Concierto actualizarConcierto(Long id, Concierto concierto) {
        if(conciertoRepository.existsById(id)) {
            concierto.setId(id);
            return conciertoRepository.save(concierto);
        }
        return null;
    }

    public void eliminarConcierto(Long id) {
        conciertoRepository.deleteById(id);
    }

    public void asociarCancionAConcierto(Long conciertoId, Cancion cancion) {
        ConciertosCanciones conciertosCanciones = new ConciertosCanciones();
        conciertosCanciones.setConcierto(conciertoRepository.findById(conciertoId).get());
        conciertosCanciones.setCancion(cancion);
        conciertosCancionesRepository.save(conciertosCanciones);
    }
}
