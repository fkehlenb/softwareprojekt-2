package de.unibremen.sfb.service;

import de.unibremen.sfb.model.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class CarServiceTest {
    @Mock
    EntityManager em;
    @InjectMocks
    CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCars() {
        List<Car> result = carService.createCars(1);
        Assertions.assertEquals(Arrays.<Car>asList(new Car("id", "Brand", 0, "color", 0, true)), result);
    }

    @Test
    void testGetColors() {
        List<String> result = carService.getColors();
        var colors = new String[10];
        colors[0] = "Black";
        colors[1] = "White";
        colors[2] = "Green";
        colors[3] = "Red";
        colors[4] = "Blue";
        colors[5] = "Orange";
        colors[6] = "Silver";
        colors[7] = "Yellow";
        colors[8] = "Brown";
        colors[9] = "Maroon";
        List<String> tResult = new ArrayList<String>(Arrays.asList(colors));
        Assertions.assertEquals(tResult, result);
    }

    @Test
    void testGetBrands() {
        List<String> result = carService.getBrands();
        Assertions.assertEquals(Arrays.<String>asList("String"), result);
    }
}