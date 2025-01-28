package com.example.gestion_conciertos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalTime;

@Entity
@Table(name = "Canciones")
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El título no puede estar vacío")
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres")
    private String titulo;

    @Column(nullable = false)
    @NotNull(message = "La duración no puede ser nula")
    private LocalTime duracion;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp fechaRegistro;

    // Constructor por defecto
    public Cancion() {}

    // Constructor con parámetros
    public Cancion(String titulo, LocalTime duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
