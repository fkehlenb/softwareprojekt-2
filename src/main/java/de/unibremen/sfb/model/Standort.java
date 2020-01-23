package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;

/** The location data class */
@Data
@Entity
public class Standort {

    /** The location */
    @NonNull
    @Id
    public String ort;
<<<<<<< HEAD:src/main/java/de/unibremen/sfb/Model/Standort.java
=======

    public Standort(String ort) {
        this.ort = ort;
    }
>>>>>>> 882d90f9d6eb0d81f52cba19f05a24b29ca98b00:src/main/java/de/unibremen/sfb/model/Standort.java
}
