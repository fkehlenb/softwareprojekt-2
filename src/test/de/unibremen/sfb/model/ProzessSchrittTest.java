package de.unibremen.sfb.model;

import org.apache.shiro.crypto.hash.Hash;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ProzessSchrittTest {
    ProzessSchritt ps;
    HashSet<Role> a = new HashSet<Role>();
    User tUser = new User();
    Jsonb jsonb = JsonbBuilder.create();


    @BeforeEach
    void setUp() {

        tUser.setVorname("Santi");
        tUser.setNachname("Rey");
        tUser.setEmail("rey@uni-bremen.de");
        tUser.setTelefonnummer("+49 112");
        tUser.setUsername("kev");
        var pw = "12345678";
        tUser.setPassword(pw.getBytes());
        tUser.setWurdeVerifiziert(false);
        tUser.setErstellungsDatum(new Date());
        a.add(Role.TRANSPORT);
        a.add(Role.TECHNOLOGE);
        a.add(Role.LOGISTIKER);
        tUser.setRollen(a);
        tUser.setLanguage("DEUTSCH");

        HashSet<ProzessSchrittLog> logs = new HashSet<>();
        logs.add(new ProzessSchrittLog(LocalDateTime.now(), "INSTANZIERT"));
        HashSet<QualitativeEigenschaft> e = new HashSet<>(); // TODO add eigenschaften
        ProzessSchrittParameter prozessSchrittParameter = new ProzessSchrittParameter("Testen", e);
        ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat = new ProzessSchrittZustandsAutomat();
        ProzessSchrittVorlage prozessSchrittVorlage = new ProzessSchrittVorlage(99, Duration.ofMinutes(42),
                ProzessSchrittArt.ERMITTELND, new HashSet<ExperimentierStation>(), (new ProzessSchrittZustandsAutomatVorlage(tUser)),
                tUser, prozessSchrittParameter);
        ps = new ProzessSchritt(42, prozessSchrittZustandsAutomat, logs , prozessSchrittVorlage);
//         new ProzessSchritt();
    }

    @Test
    void jsonTest() {
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

    @Test
    void exportJSONUser() {
        System.out.println(jsonb.toJson(tUser));
    }
}