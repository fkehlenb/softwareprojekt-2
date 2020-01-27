package de.unibremen.sfb.model;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProbeTest {
    // Create custom configuration with formatted output
    JsonbConfig config = new JsonbConfig()
            .withFormatting(true);

    // Create Jsonb with custom configuration
    Jsonb jsonb = JsonbBuilder.create(config);;
    List proben = new ArrayList();
    Set eigenschaften = new HashSet<QualitativeEigenschaft>();

    @BeforeEach
    void setUp() {

        Probe erste = new Probe();
        Standort a = new  Standort("Hier");
        erste.setProbenID(14);
        erste.setStandort(a);
        erste.setKommentar(new Kommentar(LocalDateTime.now(), "Hallo Welt"));
        eigenschaften.add(new QualitativeEigenschaft("getestet"));

        erste.setQualitativeEigenschaften(eigenschaften);


        proben.add(erste);

    }


    @Test
    void first() {
        System.out.println(jsonb.toJson(proben));
    }
}