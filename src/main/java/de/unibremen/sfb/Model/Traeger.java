package de.unibremen.sfb.Model;

import lombok.Data;

import java.util.List;

@Data
/** Data class for container objects */
public class Traeger {

    /** The container's id */
    public int id;

    /** The container's location */
    public Standort standort;

    /** The job currently using this container */
    public Auftrag zugewiesenerAuftrag;

    /** The samples contained in the container */
    public List<Probe> proben;

    /** The container's type */
    public Traegerart art;
}
