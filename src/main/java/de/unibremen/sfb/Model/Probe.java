package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalDateTime;
import java.util.Set;

/** Sample object data class */
@Data
public class Probe {

    /** The sample's id */
    @NonNull
    public int probenID;

    /** Comment added to the sample and when it was added */
    @NonNull
    public Pair<LocalDateTime,String> kommentar;

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
