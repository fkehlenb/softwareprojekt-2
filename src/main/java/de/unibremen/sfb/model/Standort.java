package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.ManyToOne;

/** The location data class */
@Data
public class Standort {

    /** The location */
    @NonNull
    @ManyToOne
    public String ort;


    public Standort(String ort) {
        this.ort = ort;
    }
}
