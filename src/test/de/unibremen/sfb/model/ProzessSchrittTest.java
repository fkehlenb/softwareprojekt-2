package de.unibremen.sfb.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import java.time.Duration;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ProzessSchrittTest {
    ProzessSchritt ps;


    @BeforeEach
    void setUp() {
        HashSet<QualitativeEigenschaft> e = new HashSet<>(); // TODO add eigenschaften
        ProzessSchrittParameter prozessSchrittParameter = new ProzessSchrittParameter("Testen", e);
        ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat = new ProzessSchrittZustandsAutomat(new ProzessSchrittZustandsAutomatVorlage(new User()));
        ProzessSchrittVorlage prozessSchrittVorlage = new ProzessSchrittVorlage(99, Duration.ofMinutes(42),
                ProzessSchrittArt.ERMITTELND, new HashSet<ExperimentierStation>(), (new ProzessSchrittZustandsAutomatVorlage(null)),
                null, prozessSchrittParameter);
        ps = new ProzessSchritt(42, prozessSchrittZustandsAutomat, null,  new ProzessSchrittZustandsAutomatVorlage(null));

    }

    @Test
    void jsonTest() {
        Jsonb jsonb = JsonbBuilder.create();
        System.out.println(jsonb.toJson(ps));

        ps.zustandsAutomat.current = "In Bearbeitung";
        // TODO ps.prozessSchrittLog. zweiten Zeitstempel
        ps.prozessSchrittLog.add(new ProzessSchrittLog("In Bearbeitung"));
        System.out.println(jsonb.toJson(ps));

        System.out.println(jsonb.toJson(ps.prozessSchrittLog));
    }

    @Test
    String outputJSON() {
        setUp();
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(ps);
    }
}