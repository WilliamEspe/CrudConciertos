package com.example.gestion_conciertos.repository;

import com.example.gestion_conciertos.model.ConciertosCanciones;
import com.example.gestion_conciertos.model.ConciertosCancionesPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConciertosCancionesRepository extends JpaRepository<ConciertosCanciones, ConciertosCancionesPK> {
    List<ConciertosCanciones> findByConciertoId(Long conciertoId);
}



