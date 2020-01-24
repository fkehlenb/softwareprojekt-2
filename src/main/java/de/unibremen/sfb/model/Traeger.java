package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/** Data class for container objects */
@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "Traeger.findByLoc", query = "SELECT t FROM Traeger t WHERE standort = :standort"),
        @NamedQuery(name = "Trager.findByProben", query = "SELECT t FROM Traeger t WHERE proben = :proben"),
        @NamedQuery(name = "Traeger.getByType", query = "SELECT t FROM Traeger t WHERE art = :art")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Traeger {

    /** The container's id */
    @NonNull
    @Id
    private int id;

    /** The container's location */
    @NonNull
    @ManyToOne
    private Standort standort;

    /** The job currently using this container */
    @OneToOne
    private Auftrag zugewiesenerAuftrag;

    /** The samples contained in the container */
    @ManyToMany
    private List<Probe> proben;

    /** The container's type */
    @NonNull
    @OneToOne
    private TraegerArt art;
}
