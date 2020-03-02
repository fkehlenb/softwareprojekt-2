package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class AuftragTest {
    @Mock
    ProzessKettenVorlage vorlage;
    //Field priority of type AuftragsPrioritaet - was not mocked since Mockito doesn't mock enums
    @Mock
    List<ProzessSchritt> prozessSchritte;
    @Mock
    AuftragsLog log;
    @Mock
    Enum<ProzessKettenZustandsAutomat> prozessKettenZustandsAutomat;
    @InjectMocks
    Auftrag auftrag;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testToString() {
        String result = auftrag.toString();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testEquals() {
        boolean result = auftrag.equals("obj");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testSetValidData() {
        auftrag.setValidData(true);
    }

    @Test
    void testSetPkID() {
        auftrag.setPkID(0);
    }

//    @Test
//    void testSetVorlage() {
//        auftrag.setVorlage(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")))));
//    }

    @Test
    void testSetPriority() {
        auftrag.setPriority(AuftragsPrioritaet.KEINE);
    }

//    @Test
//    void testSetProzessSchritte() {
//        auftrag.setProzessSchritte(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 48, 47), "zustandsAutomat")), new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")))));
//    }

    @Test
    void testSetLog() {
        auftrag.setLog(new AuftragsLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 48, 47)));
    }

    @Test
    void testSetProzessKettenZustandsAutomat() {
        auftrag.setProzessKettenZustandsAutomat(null);
    }
}