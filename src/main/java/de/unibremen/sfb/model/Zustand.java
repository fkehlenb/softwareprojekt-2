package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * The location data class
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Zustand.getAll", query = "SELECT z FROM Zustand z WHERE  z.isValidData = true")
})
public class Zustand {

    /**
     * On delete set to invalid
     */
    @NonNull
    private boolean isValidData = true;

    @Id
    @NonNull
    private String name;

    @NonNull
    @ElementCollection
    private List<String> zustaende = new ArrayList<String>();

    public Zustand(String id, ArrayList<String> zustaende) {
        this.name = id;
        this.zustaende = zustaende;
    }

    @Override
    public String toString() {
        return name;
    }
}
