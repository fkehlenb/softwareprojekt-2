package de.unibremen.sfb.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProbeTest {
    Jsonb jsonb = JsonbBuilder.create();
    List proben = new ArrayList();

    @BeforeEach
    void setUp() {

        Probe erste = new Probe();
        Standort a = new  Standort("Hier");
        erste.probenID = 14;
        erste.standort = a;
        proben.add(erste);
    }

    @Test
    void first() {
        System.out.println(jsonb.toJson(proben));
    }
}