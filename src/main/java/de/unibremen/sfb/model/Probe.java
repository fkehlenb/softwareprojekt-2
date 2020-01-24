package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/** Sample object data class */
@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "Probe.getByLocation",
                query = "SELECT p FROM Probe p WHERE p.standort = :standort"),
        @NamedQuery(name = "Probe.getByTraeger",
                query = "SELECT p FROM Probe p WHERE p.currentTraeger = :traeger")
})
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Probe {

    /** The sample's id */
    @NonNull
    @Id
    private int probenID;

    /** Comment added to the sample and when it was added */
    @OneToOne
    private Kommentar kommentar;

    /** The sample's state */
    @NonNull
    private Enum<ProbenZustand> zustand;

    /** The sample's location */
    @NonNull
    @OneToOne
    private Standort standort;

    /** The qualitative/quantitative descriptors of the sample */
    @OneToMany
    private List<QualitativeEigenschaft> qualitativeEigenschaften;

    /** The container the sample is currently located in */
    @OneToOne
    private Traeger currentTraeger;
}

