package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.Pair;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.Set;

/** Sample object data class */
@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "Probe.getByLocation",
                query = "SELECT p FROM Probe p WHERE p.standort = :standort"),
        @NamedQuery(name = "Probe.getByTraeger",
                query = "SELECT p FROM Probe p WHERE p.currentTraeger = :traeger")
})
public class Probe {

    public Probe() {
        /* JPA */
    }

    /** The sample's id */
    @NonNull
    @Id
    public int probenID;

    /** Comment added to the sample and when it was added */
    public Kommentar kommentar;

    /** The sample's state */
    @NonNull
    public Enum<ProbenZustand> zustand;

    /** The sample's location */
    @NonNull
    public Standort standort;

    /** The qualitative/quantitative descriptors of the sample */
    public Set<QualitativeEigenschaft> qualitativeEigenschaften;

    /** The container the sample is currently located in */
    public Traeger currentTraeger;
}

