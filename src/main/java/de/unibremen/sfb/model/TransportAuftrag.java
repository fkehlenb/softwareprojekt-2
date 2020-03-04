package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

/** The transport jobs' data class */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class TransportAuftrag implements Serializable {

    /** On delete set to invalid */
    @NonNull
    private boolean isValidData = true;

    @Id @GeneratedValue
    private int id;

    @NonNull
    private LocalDateTime erstellt;
    private LocalDateTime abgeholt;
    private LocalDateTime abgeliefert;


    /** The transport job's current state */
    @NonNull
    private Enum<TransportAuftragZustand> zustandsAutomat;

    @ManyToOne
    @NonNull
    private Standort start;
    @ManyToOne
    @NonNull
    private Standort ziel;
}
