package de.unibremen.sfb.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User tUser = new User();
    HashSet<Role> a = new HashSet<>();
    Jsonb jsonb = JsonbBuilder.create();

    @BeforeEach
    void setUp() {

        tUser.id = 42;
        tUser.vorname = "Santi";
        tUser.nachname = "Rey";
        tUser.email = "rey@uni-bremen.de";
        tUser.telefonnummer = "+49 112";
        tUser.username = "kev";
        var pw = "12345678";
        tUser.password = pw.getBytes();
        tUser.wurdeVerifiziert = false;
        tUser.erstellungsDatum = new Date();
        a.add(Role.TECHNOLOGE);
        tUser.rollen = a;
        tUser.language = "DEUTSCH";
    }
    @Test
    void exportJSON() {
        System.out.println(jsonb.toJson(tUser));
    }
}
