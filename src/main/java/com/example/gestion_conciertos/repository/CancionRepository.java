package com.example.gestion_conciertos.repository;

import com.example.gestion_conciertos.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {

    // Método para verificar si ya existe una canción con el mismo título
    boolean existsByTitulo(String titulo);
}
