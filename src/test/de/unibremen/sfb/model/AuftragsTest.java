package de.unibremen.sfb.model;

import org.apache.shiro.crypto.hash.Hash;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuftragsTest {
    ProzessSchritt ps;
    Auftrag pk;
    ProzessKettenVorlage pkv;
    HashSet<Role> a = new HashSet<Role>();
    User tUser = new User();

    // Create custom configuration with formatted output
    JsonbConfig config = new JsonbConfig()
            .withFormatting(true);

    // Create Jsonb with custom configuration
    Jsonb jsonb = JsonbBuilder.create(config);;


    @BeforeEach
    void setUp() {

        // User Setup
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

        // ProzessSchrittVorlage Setuo
        HashSet<ProzessSchrittLog> logs = new HashSet<>();
        logs.add(new ProzessSchrittLog(LocalDateTime.now(), "INSTANZIERT"));
        HashSet<QualitativeEigenschaft> e = new HashSet<>(); // TODO add eigenschaften
        ProzessSchrittParameter prozessSchrittParameter = new ProzessSchrittParameter("Testen", e);
        ProzessSchrittVorlage prozessSchrittVorlage = new ProzessSchrittVorlage(99, Duration.ofMinutes(42),
                ProzessSchrittArt.ERMITTELND, new HashSet<ExperimentierStation>(), (new ProzessSchrittZustandsAutomatVorlage(tUser)),
                tUser, prozessSchrittParameter);


        // PkVorlage Setup
        List<ProzessSchrittVorlage> psListe = new ArrayList<>();
        psListe.add(prozessSchrittVorlage);
        pkv = new ProzessKettenVorlage(99, psListe);

        // Auftrag Setup

        AuftragsLog aLog = new AuftragsLog();
        aLog.setErstellt(LocalDateTime.now()); ;
        pk = new Auftrag(420, pkv, AuftragsPrioritaet.HOCH, new ArrayList<ProzessSchritt>(), aLog , ProzessKettenZustandsAutomat.INSTANZIIERT);

        // PS Setup
        ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat = new ProzessSchrittZustandsAutomat();
        ps = new ProzessSchritt(42, prozessSchrittZustandsAutomat, logs , prozessSchrittVorlage);

        // PS aufuellen
        pk.getProzessSchritte().add(ps);


    }

    @Test
    void jsonTestPS() {
        System.out.println(jsonb.toJson(ps));

        ps.getZustandsAutomat().setCurrent("In Bearbeitung");
        // TODO ps.prozessSchrittLog. zweiten Zeitstempel
        // FIXMe Controller for Updating States
        ps.getProzessSchrittLog().add(new ProzessSchrittLog(LocalDateTime.now(),"In Bearbeitung"));
        System.out.println(jsonb.toJson(ps));
    }

    @Test
    void outJSONPsLog() {
        System.out.println(jsonb.toJson(ps.getProzessSchrittLog()));
    }

    @Test
    void outputJSONAuftrag() {
        System.out.println(jsonb.toJson(pk));
    }

    @Test
    String outputJSON() {
        return jsonb.toJson(ps);
    }

    @Test
    void exportJSONUser() {
        System.out.println(jsonb.toJson(tUser));
    }
}