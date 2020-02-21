package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
/** Achive data class */
public class Archiv {

    /** On delete set to invalid */
    private boolean isValidData = true;

    @Id @GeneratedValue
    private int id;

    /** The job that has been archived */
    @NonNull
    @OneToOne
    private Auftrag auftrag;

    /** The date the job has been archived */
    private LocalDateTime datum;
}
