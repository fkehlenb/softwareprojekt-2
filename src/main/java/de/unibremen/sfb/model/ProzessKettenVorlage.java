package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/** Data class for the process chain templates */
@Data
@NamedQueries({
        @NamedQuery(name = "PKV.getByUser",
                query = "SELECT pkv FROM ProzessKettenVorlage pkv WHERE pkv.creator = :user") // FIXME Tutorgespraech Marcel meinte PK sind unabhaengig von Erstllern
})
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

    /** The user who created this template */
    @NonNull
    @ManyToOne
    private User creator;
}
