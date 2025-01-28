package com.example.gestion_conciertos.model;

import jakarta.persistence.Column;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ConciertosCancionesPK implements Serializable {

    @Column(name = "concierto_id")
    private Long conciertoId;

    @Column(name = "cancion_id")
    private Long cancionId;

    // Getters and Setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConciertosCancionesPK that = (ConciertosCancionesPK) o;
        return conciertoId.equals(that.conciertoId) && cancionId.equals(that.cancionId);
    }

    @Override
    public int hashCode() {
        return 31 * conciertoId.hashCode() + cancionId.hashCode();
    }

    public Long getConciertoId() {
        return conciertoId;
    }

    public void setConciertoId(Long conciertoId) {
        this.conciertoId = conciertoId;
    }

    public Long getCancionId() {
        return cancionId;
    }

    public void setCancionId(Long cancionId) {
        this.cancionId = cancionId;
    }

    public ConciertosCancionesPK(Long conciertoId, Long cancionId) {
        this.conciertoId = conciertoId;
        this.cancionId = cancionId;
    }

    public ConciertosCancionesPK() {
    }
    
}
