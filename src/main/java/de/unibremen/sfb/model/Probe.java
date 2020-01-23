package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;

/** Sample object data class */
@Data
public class Probe {

    public Probe() {
    // FIXME Warum alles public. wie genau zugreifen
    }

    /** The sample's id */
    @NonNull
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

