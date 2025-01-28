package com.example.gestion_conciertos.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "Conciertos_Canciones")
public class ConciertosCanciones {

    @EmbeddedId
    private ConciertosCancionesPK id;

    @ManyToOne
    @MapsId("conciertoId")
    @JoinColumn(name = "concierto_id", referencedColumnName = "id")
    private Concierto concierto;

    @ManyToOne
    @MapsId("cancionId")
    @JoinColumn(name = "cancion_id", referencedColumnName = "id")
    private Cancion cancion;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp fechaRegistro;

    // Getters and Setters

    public Concierto getConcierto() {
        return concierto;
    }

    public void setConcierto(Concierto concierto) {
        this.concierto = concierto;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public ConciertosCancionesPK getId() {
        return id;
    }

    public void setId(ConciertosCancionesPK id) {
        this.id = id;
    }

    public ConciertosCanciones(ConciertosCancionesPK id, Concierto concierto, Cancion cancion) {
        this.id = id;
        this.concierto = concierto;
        this.cancion = cancion;
    }

    public ConciertosCanciones() {
    }

    

}
