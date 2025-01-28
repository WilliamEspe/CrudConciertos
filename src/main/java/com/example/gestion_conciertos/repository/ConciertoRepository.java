package com.example.gestion_conciertos.repository;

import com.example.gestion_conciertos.model.Concierto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConciertoRepository extends JpaRepository<Concierto, Long> {

    boolean existsByNombre(String nombre);
}
