package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;

/** This class is the data class for container types */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class TraegerArt {

    /** On delete set to invalid */
    @NonNull
    private boolean isValidData = true;

    /** Container Type */
    @Id
    @NonNull
    private String art;

    @Override
    public String toString() {
        return
                "art='" + art ;
    }
}