package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.Role;
import de.unibremen.sfb.Model.User;

import java.time.LocalDateTime;
import java.util.Set;

public class UserController {

    public User user;

    public void setID(int id) {}

    public int getID() { return 0; }

    public void addRole(Set<Enum<Role>> role) {}

    public Set<Enum<Role>> getRole() { return null; }

    public void removeRole(Enum<Role> role) {}

    public void applyUsername(String username) {}

    public void setEmail(String email) {}

    public String getEmail() { return null; }

    public void setName(String name) {}

    public String getName(String name) { return null; }

    public void setSurname(String name) {}

    public String getSurname() { return null; }

    public void setPassword(String password) {}

    public Byte[] getPassword() { return null; }

    public void setPhone(String pn) {}

    public String getPhone() { return null; }

    public void setVerified(boolean v) {}

    public boolean getVerified() { return false; }

    public void setCreationDate(LocalDateTime d) {}

    public LocalDateTime getCreationDate() { return null; }

    public String getCurrentLanguage() { return null; }

    public void setDefaultLanguage(String l) {}

}
