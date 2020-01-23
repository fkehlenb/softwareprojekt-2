package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

/** Data class for container objects */
@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "Traeger.findById", query = "SELECT t FROM Traeger t where t.id = :id")
})
public class Traeger {

    /** The container's id */
    @NonNull
    @Id
    public int id;
//
//    /** The container's location */
//    @NonNull
//    public Standort standort;
//
//    /** The job currently using this container */
//    public Auftrag zugewiesenerAuftrag;
//
//    /** The samples contained in the container */
//    public Set<Probe> proben;
//
//    /** The container's type */
//    @NonNull
//    public TraegerArt art;
}
