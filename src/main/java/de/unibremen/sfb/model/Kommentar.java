package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Kommentar {

    /** On delete set to invalid */
    private boolean isValidData = true;

    @Id @GeneratedValue
    private int id;

    @NonNull
    private LocalDateTime dateTime;

    @NonNull
    private String text;
}
