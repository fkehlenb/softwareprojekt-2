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

class ProzessSchrittTest {
    @Mock
    ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat;
    @Mock
    List<ProzessSchrittParameter> prozessSchrittParameters;
    @Mock
    TransportAuftrag transportAuftrag;
    @Mock
    List<ProzessSchrittLog> prozessSchrittLog;
    @InjectMocks
    ProzessSchritt prozessSchritt;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testToString() {
        String result = prozessSchritt.toString();
        Assertions.assertEquals("null", result);
    }

    @Test
    void testSetValidData() {
        prozessSchritt.setValidData(true);
    }

    @Test
    void testSetId() {
        prozessSchritt.setId(0);
    }

    @Test
    void testSetAssigned() {
        prozessSchritt.setAssigned(true);
    }

    @Test
    void testSetProzessSchrittZustandsAutomat() {
        prozessSchritt.setProzessSchrittZustandsAutomat(new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")));
    }

    @Test
    void testSetDuration() {
        prozessSchritt.setDuration("duration");
    }

    @Test
    void testSetProzessSchrittParameters() {
        prozessSchritt.setProzessSchrittParameters(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))));
    }

    @Test
    void testSetAttribute() {
        prozessSchritt.setAttribute("attribute");
    }

    @Test
    void testSetTransportAuftrag() {
        prozessSchritt.setTransportAuftrag(new TransportAuftrag());
    }

    @Test
    void testSetProzessSchrittLog() {
        prozessSchritt.setProzessSchrittLog(Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 47, 45), "zustandsAutomat")));
    }

    @Test
    void testSetName() {
        prozessSchritt.setName("name");
    }

    @Test
    void testSetUrformend() {
        prozessSchritt.setUrformend(true);
    }

    @Test
    void testSetAmountCreated() {
        prozessSchritt.setAmountCreated(0);
    }

    @Test
    void testSetUploaded() {
        prozessSchritt.setUploaded(true);
    }
}

