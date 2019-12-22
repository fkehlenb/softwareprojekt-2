package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

import java.util.Set;

/** Data class for container objects */
@Data
public class Traeger {

    /** The container's id */
    @NonNull
    public int id;

    /** The container's location */
    @NonNull
    public Standort standort;

    /** The job currently using this container */
    public Auftrag zugewiesenerAuftrag;

    /** The samples contained in the container */
    public Set<Probe> proben;

    /** The container's type */
    @NonNull
    public TraegerArt art;
}
