package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

/** The transport jobs' data class */
@Data
public class TransportAuftrag {

    /** The transport job's current state */
    @NonNull
    public Enum<TransportAuftragZustand> zustandsAutomat;
}
