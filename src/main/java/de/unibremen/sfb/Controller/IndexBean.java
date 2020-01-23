package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.User;
import de.unibremen.sfb.Persistence.UserDAO;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Message;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * this class manages the interaction between the gui and the backend system for the initial situation (the first page any visitor sees)
 */
@RequestScoped
@Named
public class IndexBean implements Serializable {

    /**
     * username will be saved here once entered
     */
    @Getter
    @Setter
    private String username;

    /**
     * password will be saved here once entered by user
     */
    @Getter
    @Setter
    private String password;

    @Inject
    private UserDAO dao;

    /**
     * login with the values in username and password
     */
    public void login() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("technologe.xhtml");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * sets the language in which everything is supposed to be displayed
     * @param l the new language
     */
    public void setLanguage(String l) {}

    /**
     * returns the language everything is currently displayed in
     * @return the current language
     */
    public String getCurrentLanguage() { return null; }

    /**
     * the empty constructor
     */
    public IndexBean() {}

    /**
     * returns the username
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * sets the username
     * @param username the username
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * returns the password
     * @return the password
     */
    public String getPasswort() { return password; }

    /**
     * sets the password
     * @param password the password
     */
    public void setPassword(String password) { this.password = password; }
}
