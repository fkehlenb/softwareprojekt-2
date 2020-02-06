package de.unibremen.sfb.controller;

import de.unibremen.sfb.exception.DuplicateUserException;
import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.User;
import de.unibremen.sfb.persistence.UserDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdminBeanTest implements {

    @Inject
    private UserDAO userDAO;

    AdminBean aBean = new AdminBean();

    @BeforeEach
    public  void init()  {


        aBean.setVorname("Vorname");
        aBean.setNachname("Nachname");
        aBean.setEmail("prueba@prueba.de");
        aBean.setTelefonNummer("11122223333");
        aBean.setUserName("Username");
        aBean.setPassword("password");
        aBean.setWurdeVerifiziert(true);
        aBean.setTECHNOLOGER(true);
        aBean.setLanguage("Deutsch");
        aBean.setRol(new ArrayList<>());



    }

    @Test
    public void addUser() throws DuplicateUserException, UserNotFoundException {
        //aBean.addUser();
        User user = userDAO.getUserByName("Vorname");
        System.out.println(user.getVorname());
    }
}