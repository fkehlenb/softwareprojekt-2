package de.unibremen.sfb.Controller;

/**
 * this class manages the interaction of the gui with the backend system (for situations in which the user is attempting to reset their password)
 */
public class ResetBean {

    /**
     * the username of the user to be reset //TODO password, oder?
     */
    public String username;
    /**
     * the email address of the user to be reset
     */
    public String email;

    /**
     * resets the user after finding him via his username
     */
    public void resetByUsername() {}

    /**
     * resets the user after finding him via his email
     */
    public void resetByEmail() {}

    /**
     * sets the language in which everything is displayed for this user
     * @param l the language
     */
    public void setLanguage(String l) {}

    /**
     * the language everything is currently displayed in for this user
     * @return the language
     */
    public String getCurrentLanguage() { return null; }
}
