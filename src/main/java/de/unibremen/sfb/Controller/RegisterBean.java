package de.unibremen.sfb.Controller;

/**
 * this class manages the interaction between the gui and the backend system for situations in which a new user is registered
 */
public class RegisterBean {

    /**
     * the username of the new user
     */
    public String username;
    /**
     * the first name of the new user
     */
    public String name;
    /**
     * the surname of the new user
     */
    public String surname;
    /**
     * the email address of the new user
     */
    public String email;
    /**
     * the password of the new user
     */
    public String password;

    /**
     * registers the new user in the data base
     */
    public void register() {}

    /**
     * sets the language of the new user
     * @param l the language
     */
    public void setLanguage(String l) {}

    /**
     * returns the language everything is currently displayed in
     * @return the language
     */
    public String getCurrentLanguage() { return null; }
}
