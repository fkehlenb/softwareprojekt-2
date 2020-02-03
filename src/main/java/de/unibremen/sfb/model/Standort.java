package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** The location data class */
@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Standort {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private int id;

    /** The location */
    @NonNull
    private String ort;
}
