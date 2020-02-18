package de.unibremen.sfb.controller;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

import de.unibremen.sfb.service.AuftragService;
import org.junit.jupiter.api.Test;


class InitialDataFillerTest {

    @Inject
    AuftragService auftragService;



    @Test
    void init() {
//        InitialDataFiller initialDataFiller = new InitialDataFiller();
//        initialDataFiller.init();
//        System.out.println("Testet die Daos");
    }

    @Test
    void testAuftrag() {
        JsonbConfig config = new JsonbConfig()
                .withFormatting(true);

        // Create Jsonb with custom configuration
        Jsonb jsonb = JsonbBuilder.create(config);
        String result = jsonb.toJson(auftragService.getAuftrage());
        System.out.println(result);
    }
}
