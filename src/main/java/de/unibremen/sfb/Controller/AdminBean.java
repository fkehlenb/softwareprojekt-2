package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.Auftrag;
import de.unibremen.sfb.Model.ExperimentierStation;
import de.unibremen.sfb.Model.TraegerArt;
import de.unibremen.sfb.Model.User;

import java.util.Set;

public class AdminBean {

    public User admin;

    public Set<User> getAllUser() { return null; }

    public void addUser(User user) {}

    public void editUser(User user) {}

    public void deleteUser(User user) {}

    public void addTraegerArt(TraegerArt ta) {}

    public void editTraegerArt(TraegerArt ta) {}

    public void deleteTraegerArt(TraegerArt ta) {}

    public void addStation(ExperimentierStation es) {}

    public void editStation(ExperimentierStation es) {}

    public void deleteStation(ExperimentierStation es) {}

    public void userToStation(User us, ExperimentierStation es) {}

    public void generateRegestrationMail()  {}

    public Set<ExperimentierStation> getES()  { return null; }

    public void editAuftragTime(Auftrag a) {}

    public void backup() {}
}

