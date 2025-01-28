package com.example.gestion_conciertos.controller;

import com.example.gestion_conciertos.service.ConciertosCancionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conciertos-canciones")
public class ConciertosCancionesController {

    @Autowired
    private ConciertosCancionesService conciertosCancionesService;

    // Asociar una canción a un concierto
    @PostMapping("/asociar")
    public void asociarCancionAConcierto(@RequestParam Long conciertoId, @RequestParam Long cancionId) {
        conciertosCancionesService.asociarCancionAConcierto(conciertoId, cancionId);
    }

    // Eliminar la asociación de una canción en un concierto
    @DeleteMapping("/eliminar")
    public void eliminarCancionDeConcierto(@RequestParam Long conciertoId, @RequestParam Long cancionId) {
        conciertosCancionesService.eliminarCancionDeConcierto(conciertoId, cancionId);
    }
}
