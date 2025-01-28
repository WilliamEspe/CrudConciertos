package com.example.gestion_conciertos.service;

import com.example.gestion_conciertos.model.ConciertosCanciones;
import com.example.gestion_conciertos.model.Concierto;
import com.example.gestion_conciertos.repository.ConciertosCancionesRepository;
import com.example.gestion_conciertos.model.Cancion;
import com.example.gestion_conciertos.repository.ConciertoRepository;
import com.example.gestion_conciertos.repository.CancionRepository;
import com.example.gestion_conciertos.model.ConciertosCancionesPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConciertosCancionesService {

    @Autowired
    private ConciertosCancionesRepository conciertosCancionesRepository;

    @Autowired
    private ConciertoRepository conciertoRepository;

    @Autowired
    private CancionRepository cancionRepository;

    public ConciertosCanciones asociarCancionAConcierto(Long conciertoId, Long cancionId) {
        // Buscar el concierto por el ID
        Concierto concierto = conciertoRepository.findById(conciertoId)
                .orElseThrow(() -> new RuntimeException("Concierto no encontrado"));

        // Buscar la canción por el ID
        Cancion cancion = cancionRepository.findById(cancionId)
                .orElseThrow(() -> new RuntimeException("Canción no encontrada"));

        // Crear la clave primaria compuesta para la relación
        ConciertosCancionesPK pk = new ConciertosCancionesPK(conciertoId, cancionId);

        // Crear la nueva relación
        ConciertosCanciones nuevaRelacion = new ConciertosCanciones();
        nuevaRelacion.setId(pk); // Asignar la clave primaria compuesta
        nuevaRelacion.setConcierto(concierto); // Asignar el concierto
        nuevaRelacion.setCancion(cancion); // Asignar la canción

        // Guardar la relación en la base de datos
        return conciertosCancionesRepository.save(nuevaRelacion);
    }




    public void eliminarCancionDeConcierto(Long conciertoId, Long cancionId) {
        ConciertosCancionesPK pk = new ConciertosCancionesPK(conciertoId, cancionId);
        conciertosCancionesRepository.deleteById(pk);
    }
}




