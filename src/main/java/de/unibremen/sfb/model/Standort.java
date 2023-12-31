package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/** The location data class */
@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor()
public class Standort {

    @Id
    @NonNull
    private int id;

    /** The location */
    @NonNull
    private String ort;
}
