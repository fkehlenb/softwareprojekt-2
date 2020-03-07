package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/** Data class for container objects */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Traeger {

    /** On delete set to invalid */
    @NonNull
    private boolean isValidData = true;

    /** The container's id */
    @NonNull
    @Id
    private int id;

    /** The container's type */
    @NonNull
    private String art;

    @ManyToMany(fetch = FetchType.LAZY)
    @NonNull
    private List<Probe> proben;

    /** Container location */
    @NonNull
    @ManyToOne
    private Standort standort;

    @Override
    public String toString(){
        return "Traeger " + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Traeger traeger = (Traeger) o;
        return getId() == traeger.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
