package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/** Data class for the process chain templates */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedQueries({
        @NamedQuery(name = "PKV.getAll", query = "SELECT p FROM ProzessKettenVorlage p WHERE p.isValidData = true")
})
@RequiredArgsConstructor
public class ProzessKettenVorlage implements Serializable {

    /** On delete set to invalid */
    @NonNull
    private boolean isValidData = true;

    /** The process chain template id */
    @Id
    @NonNull
    private int pkvID;

    /** Process chain template name */
    @NonNull
    private String name;

    /** The process chain template's process steps (as templates hence not yet instantiated) */
    @NonNull
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ProzessSchrittVorlage> prozessSchrittVorlagen;

    @Override
    public String toString(){
        return name;
    }
}
