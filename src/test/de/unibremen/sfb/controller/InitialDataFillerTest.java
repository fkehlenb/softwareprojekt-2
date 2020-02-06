package de.unibremen.sfb.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitialDataFillerTest {

    @Test
    void init() {
        InitialDataFiller initialDataFiller = new InitialDataFiller();
        initialDataFiller.init();
        System.out.println("Testet die Daos");
    }
}