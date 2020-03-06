package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
}
