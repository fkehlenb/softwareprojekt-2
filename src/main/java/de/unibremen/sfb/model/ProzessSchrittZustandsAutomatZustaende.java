package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class ProzessSchrittZustandsAutomatZustaende {

    /** ID */
    @Id
    @NonNull
    private int id;

    /** List of available process step states */
    @ElementCollection
    @NonNull
    private List<String> zustaende;
}
