package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/** This class is the data class for container types */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class TraegerArt {

    /** On delete set to invalid */
    private boolean isValidData = true;

    @Id @GeneratedValue
    private int id;

    /** Container Type */
    @NonNull
    private String art;
}