package com.example.gestion_conciertos.controller;

import com.example.gestion_conciertos.model.Cancion;
import com.example.gestion_conciertos.service.CancionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/canciones")
public class CancionController {

    @Autowired
    private CancionService cancionService;

    // Obtener todas las canciones
    @GetMapping
    public List<Cancion> obtenerCanciones() {
        return cancionService.obtenerTodasCanciones();
    }

    // Obtener una canción por ID
    @GetMapping("/{id}")
    public Cancion obtenerCancion(@PathVariable Long id) {
        return cancionService.obtenerCancionPorId(id);
    }

    // Crear una nueva canción con validación
    @PostMapping
    public Cancion crearCancion(@Valid @RequestBody Cancion cancion) {
        return cancionService.crearCancion(cancion);
    }

    // Actualizar una canción existente con validación
    @PutMapping("/{id}")
    public Cancion actualizarCancion(@PathVariable Long id, @Valid @RequestBody Cancion cancion) {
        return cancionService.actualizarCancion(id, cancion);
    }

    // Eliminar una canción por ID
    @DeleteMapping("/{id}")
    public void eliminarCancion(@PathVariable Long id) {
        cancionService.eliminarCancion(id);
    }
}
