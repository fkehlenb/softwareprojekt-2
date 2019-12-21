package de.unibremen.sfb.Controller;

/**
 * this class manages the interaction between the gui and the backend system for the initial situation (the first page any visitor sees)
 */
public class IndexBean {

    /**
     * username will be saved here once entered
     */
    public String username;

    /**
     * password will be saved here once entered by user
     */
    public String password;

    /**
     * login with the values in username and password
     */
    public void login() {}

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
}
