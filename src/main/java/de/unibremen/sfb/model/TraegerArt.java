package de.unibremen.sfb.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Set;

/** This class is the data class for container types */
@Data
public class TraegerArt {

    /**
     * List containing all container types
     */
    public Set<String> arten;

    public TraegerArt(Set<String> arten) {
        this.arten = arten;
    }

}