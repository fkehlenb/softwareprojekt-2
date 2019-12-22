package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

/** The location data class */
@Data
public class Standort {

    /** The location */
    @NonNull
    public String ort;
}
