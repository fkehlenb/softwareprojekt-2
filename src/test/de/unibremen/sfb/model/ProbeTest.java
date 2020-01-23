package de.unibremen.sfb.model;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProbeTest {
    Jsonb jsonb = JsonbBuilder.create();
    List proben = new ArrayList();
    Set eigenschaften = new HashSet<QualitativeEigenschaft>();

    @BeforeEach
    void setUp() {

        Probe erste = new Probe();
        Standort a = new  Standort("Hier");
        erste.probenID = 14;
        erste.standort = a;
        erste.kommentar = new Kommentar(LocalDateTime.now(), "Hallo Welt");
        eigenschaften.add(new QualitativeEigenschaft("getestet"));

        erste.qualitativeEigenschaften = eigenschaften;


        proben.add(erste);

    }


    @Test
    void first() {
        System.out.println(jsonb.toJson(proben));
    }
}