package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/** Data class for the process chain templates */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class ProzessKettenVorlage {

    /** The process chain template id */
    @Id
    @NonNull
    private int pkID;

    /** The process chain template's process steps (as templates hence not yet instantiated) */
    @NonNull
    @OneToMany
    private List<ProzessSchrittVorlage> prozessSchrittVorlagen;
}
