package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.Set;

/** Data class for the process chain templates */
@Data
@NamedQueries({
        @NamedQuery(name = "PKV.getByUser",
                query = "SELECT pkv FROM ProzessKettenVorlage pkv WHERE pkv.creator = :user")
})
//TODO @Entity
public class ProzessKettenVorlage {

    /** The process chain template id */
    //TODO @Id
    @NonNull
    public int pkID;

    /** The process chain template's process steps (as templates hence not yet instantiated) */
    @NonNull
    public Set<ProzessSchrittVorlage> prozessSchrittVorlagen;

    /** The user who created this template */
    @NonNull
    public User creator;
}
