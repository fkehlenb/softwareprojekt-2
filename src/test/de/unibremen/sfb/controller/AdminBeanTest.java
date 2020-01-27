package de.unibremen.sfb.controller;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AdminBeanTest {

    @Test
    void backup() throws SQLException {
        var aBean = new AdminBean();
        aBean.backup();
    }
}