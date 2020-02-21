package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/** Data class for the process chain templates */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedQueries({
        @NamedQuery(name = "PKV.getAll", query = "SELECT p FROM ProzessKettenVorlage p WHERE p.isValidData = true")
})
@RequiredArgsConstructor
public class ProzessKettenVorlage {

    /** On delete set to invalid */
    private boolean isValidData = true;

    /** The process chain template id */
    @Id
    @NonNull
    private int pkID;

    /** The process chain template's process steps (as templates hence not yet instantiated) */
    @NonNull
    @ManyToMany(fetch = FetchType.EAGER)
    private List<ProzessSchrittVorlage> prozessSchrittVorlagen;
}
