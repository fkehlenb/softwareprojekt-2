package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.User;
import de.unibremen.sfb.service.UserService;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * this class manages the interaction of the gui with the backend system (for situations in which the user is attempting to reset their password)
 */
@Named
@RequestScoped
@Transactional
public class ResetBean implements Serializable {

    /**
     * the username of the user to be reset
     */
    @Getter
    @Setter
    private String username;
    /**
     * the email address of the user to be reset
     */
    @Getter
    @Setter
    private String email;

    /** User controller */
    @Inject
    private UserService userService;

    /**
     * resets the user after finding him via his username
     */
    public void resetByUsername() {
        try {
            User u = userService.getUserByUsername(getUsername());
        }
        catch (Exception e){
            e.printStackTrace();
            facesError("Kein Benutzer mit diesem Benutzername gefunden!");
        }
    }

    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     * @param message Error Message
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    /**
     * resets the user after finding him via his email
     */
    public void resetByEmail() {
        try {
            User u = userService.getUserByEmail(getEmail());
        }
        catch (Exception e){
            e.printStackTrace();
            facesError("Kein Benutzer mit dieser Email Adresse gefunden!");
        }
    }

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

    /**
     * the empty constructor
     */
    public ResetBean() {}
}
