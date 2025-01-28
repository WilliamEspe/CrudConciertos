package com.example.gestion_conciertos.controller;

import com.example.gestion_conciertos.model.Cancion;
import com.example.gestion_conciertos.model.Concierto;
import com.example.gestion_conciertos.service.CancionService;
import com.example.gestion_conciertos.service.ConciertoService;
import com.example.gestion_conciertos.service.ConciertosCancionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/conciertos")
public class ConciertoController {

    @Autowired
    private ConciertoService conciertoService;

    @Autowired
    private CancionService cancionService;

    @Autowired
    private ConciertosCancionesService conciertosCancionesService;

    // Asociar una canción a un concierto
    @PostMapping("/{conciertoId}/canciones")
    public ResponseEntity<String> asociarCancionAConcierto(@PathVariable Long conciertoId, @RequestBody Map<String, Long> ids) {
        // Verificar que el concierto exista
        Optional<Concierto> concierto = conciertoService.obtenerConciertoPorId(conciertoId);
        if (!concierto.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Concierto no encontrado");
        }

        // Verificar que el id de la canción no sea nulo
        Long cancionId = ids.get("cancionId");
        if (cancionId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id de la canción es necesario");
        }

        // Verificar que la canción exista
        Cancion cancionExistente = cancionService.obtenerCancionPorId(cancionId);
        if (cancionExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Canción no encontrada");
        }

        // Asociar la canción al concierto
        conciertosCancionesService.asociarCancionAConcierto(conciertoId, cancionId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Canción asociada al concierto");
    }


    @GetMapping
    public ResponseEntity<List<Concierto>> obtenerConciertos() {
        List<Concierto> conciertos = conciertoService.obtenerTodosConciertos();
        return ResponseEntity.ok(conciertos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Concierto> obtenerConciertoPorId(@PathVariable Long id) {
        Optional<Concierto> conciertoOptional = conciertoService.obtenerConciertoPorId(id);
        if (!conciertoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(conciertoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Concierto> crearConcierto(@RequestBody Concierto concierto) {
        Concierto nuevoConcierto = conciertoService.crearConcierto(concierto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoConcierto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Concierto> actualizarConcierto(@PathVariable Long id, @RequestBody Concierto concierto) {
        Concierto conciertoActualizado = conciertoService.actualizarConcierto(id, concierto);
        if (conciertoActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(conciertoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarConcierto(@PathVariable Long id) {
        conciertoService.eliminarConcierto(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{conciertoId}/canciones/{cancionId}")
    public ResponseEntity<Void> eliminarCancionDeConcierto(
            @PathVariable Long conciertoId,  // ID del concierto
            @PathVariable Long cancionId) {  // ID de la canción

        // Llamar al servicio para eliminar la canción del concierto
        conciertosCancionesService.eliminarCancionDeConcierto(conciertoId, cancionId);

        // Devolver una respuesta vacía
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/canciones")
    public ResponseEntity<List<Cancion>> obtenerCancionesDeConcierto(@PathVariable Long id) {
        List<Cancion> canciones = conciertoService.obtenerCancionesDeConcierto(id);
        if (canciones.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(canciones);
    }


}

