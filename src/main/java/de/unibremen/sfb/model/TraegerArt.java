package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/** This class is the data class for container types */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TraegerArt {

    @Id @GeneratedValue
    private int id;

    /**
     * List containing all container types
     */
    @ElementCollection
    private List<String> arten;
}