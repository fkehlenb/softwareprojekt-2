package de.unibremen.sfb.Model;

import lombok.Data;

import java.time.Duration;
import java.util.List;

@Data
/** Data class for the process step templates */
public class ProzessSchrittVorlage {

    /** Process step template id */
    public int psVID;

    /** Process step template states */
    public List<String> zustaende;

    /** Duration of the process step template */
    public Duration dauer;

    /** Accepted container input types */
    public List<Traegerart> eingabeTraeger;

    /** Accepted container output types */
    public List<Traegerart> ausgabeTraeger;

    /** The process step type */
    public ProzessSchrittArt art;

    /** The experimenting stations accepted in the process step template */
    public List<ExperimentierStation> stationen;

    /** */
}
