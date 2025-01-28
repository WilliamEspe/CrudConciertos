package com.example.gestion_conciertos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.gestion_conciertos")
public class GestionConciertosApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestionConciertosApplication.class, args);
    }
}

