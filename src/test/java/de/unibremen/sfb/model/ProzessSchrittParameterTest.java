package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ProzessSchrittParameterTest {
    @Mock
    List<QualitativeEigenschaft> qualitativeEigenschaften;
    @InjectMocks
    ProzessSchrittParameter prozessSchrittParameter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testToString() {
        String result = prozessSchrittParameter.toString();
        Assertions.assertEquals(" null", result);
    }

    @Test
    void testSetValidData() {
        prozessSchrittParameter.setValidData(true);
    }

    @Test
    void testSetId() {
        prozessSchrittParameter.setId(0);
    }

    @Test
    void testSetName() {
        prozessSchrittParameter.setName("name");
    }

    @Test
    void testSetQualitativeEigenschaften() {
        prozessSchrittParameter.setQualitativeEigenschaften(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));
    }
}

