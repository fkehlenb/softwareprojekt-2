package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

import java.util.Set;

/** Data class for the process chain templates */
@Data
public class ProzessKettenVorlage {

    /** The process chain template id */
    @NonNull
    public int pkID;

    /** The process chain template's process steps (as templates hence not yet instantiated) */
    @NonNull
    public Set<ProzessSchrittVorlage> processSchrittVorlagen;
}
