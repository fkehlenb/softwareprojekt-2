package de.unibremen.sfb.Model;

import lombok.Data;

import java.util.Set;

/** This class is the data class for container types */
@Data
public class TraegerArt {

    /** List containing all container types */
    public Set<String> arten;
}
