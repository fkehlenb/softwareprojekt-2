package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;

/** The location data class */
@Data
@Entity
public class Standort {

    /** The location */
    @NonNull
    @Id
    public String ort;

    public Standort(String ort) {
        this.ort = ort;
    }
}
