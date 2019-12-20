package de.unibremen.sfb.Model;

import lombok.Data;

import java.util.List;

@Data
/* The location data class */
public class Standort {

    /** The location */
    public String standort;

    /** The containers in this location */
    public List<Traeger> traeger;
}
