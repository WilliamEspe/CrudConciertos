package com.example.gestion_conciertos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "Conciertos")
public class Concierto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El nombre del concierto no puede estar vacío")
    private String nombre;

    @Column(nullable = false)
    @NotNull(message = "La fecha del concierto no puede ser nula")
    private LocalDate fecha;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp fechaRegistro;

    // Constructor por defecto
    public Concierto() {}

    // Constructor con parámetros
    public Concierto(String nombre, LocalDate fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }
    // Getters and Setters

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Concierto(Long id) {

        this.id = id;

    }
}
