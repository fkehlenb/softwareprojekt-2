package de.unibremen.sfb.model;

import org.apache.shiro.crypto.hash.Hash;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ProzessSchrittTest {
    ProzessSchritt ps;


    @BeforeEach
    void setUp() {
        HashSet<ProzessSchrittLog> logs = new HashSet<>();
        logs.add(new ProzessSchrittLog(LocalDateTime.now(), "INSTANZIERT"));
        HashSet<QualitativeEigenschaft> e = new HashSet<>(); // TODO add eigenschaften
        ProzessSchrittParameter prozessSchrittParameter = new ProzessSchrittParameter("Testen", e);
        ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat = new ProzessSchrittZustandsAutomat(new ProzessSchrittZustandsAutomatVorlage(new User()));
        ProzessSchrittVorlage prozessSchrittVorlage = new ProzessSchrittVorlage(99, Duration.ofMinutes(42),
                ProzessSchrittArt.ERMITTELND, new HashSet<ExperimentierStation>(), (new ProzessSchrittZustandsAutomatVorlage(null)),
                null, prozessSchrittParameter);
        ps = new ProzessSchritt(42, prozessSchrittZustandsAutomat, logs , prozessSchrittVorlage);
//         new ProzessSchritt();
    }

    @Test
    void jsonTest() {
        Jsonb jsonb = JsonbBuilder.create();
        System.out.println(jsonb.toJson(ps));

        ps.getZustandsAutomat().setCurrent("In Bearbeitung");
        // TODO ps.prozessSchrittLog. zweiten Zeitstempel
        ps.getProzessSchrittLog().add(new ProzessSchrittLog(LocalDateTime.now(),"In Bearbeitung"));
        System.out.println(jsonb.toJson(ps));

        System.out.println(jsonb.toJson(ps.getProzessSchrittLog()));
    }

    @Test
    String outputJSON() {
        setUp();
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(ps);
    }
}