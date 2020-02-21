package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** The transport jobs' data class */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class TransportAuftrag {

    /** On delete set to invalid */
    private boolean isValidData = true;

    @Id @GeneratedValue
    private int id;

    /** The transport job's current state */
    @NonNull
    private Enum<TransportAuftragZustand> zustandsAutomat;
}
