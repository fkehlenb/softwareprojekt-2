package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * The location data class
 */
@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Zustand.getByOrt", query = "SELECT s FROM Zustand s WHERE s.ort = :ort AND s.isValidData = true")
})
public class Zustand {

    /**
     * On delete set to invalid
     */
    @NonNull
    private boolean isValidData = true;

    @Id
    @NonNull
    private int id;

    /**
     * The location
     */
    @NonNull
    private String ort;

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
