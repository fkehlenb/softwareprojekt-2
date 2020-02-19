package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.Role;
import de.unibremen.sfb.model.User;
import de.unibremen.sfb.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authc.credential.PasswordMatcher;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This bean manages the signup webpage
 */
@Named
@RequestScoped
@Transactional
public class RegisterBean implements Serializable {

    /**
     * The user's name
     */
    @Getter
    @Setter
    private String vorname;
    /**
     * The user's surname
     */
    @Getter
    @Setter
    private String nachname;
    /**
     * The user's username
     */
    @Getter
    @Setter
    private String username;
    /**
     * The user's password
     */
    @Getter
    @Setter
    private String password;
    /**
     * The user's password confirmation
     */
    @Getter
    @Setter
    private String passwordConfirmation;
    /**
     * The user's email
     */
    @Getter
    @Setter
    @Email
    private String email;
    /**
     * The user's phone number
     */
    @Getter
    @Setter
    private String phoneNumber;

    /**
     * UserService
     */
    @Inject
    private UserService userService;

    /**
     * Redirect after successful checks
     */
    public void signup() {
        try {
            if (passwordsMatch(password, passwordConfirmation)) {
                User u = null;
                int i=0;
                try {
                    u = userService.getUserByEmail(email);
                }
                catch (Exception e){
                    //e.printStackTrace();
                }
                try {
                    i = u.getId();
                    facesError("Zu dieser Email existiert schon ein Benutzer!");
                }
                catch (Exception e){
                    // e.printStackTrace();
                }
                if (i!=0){
                    throw new IllegalArgumentException();
                }
                try {
                    u = userService.getUserByUsername(username);
                } catch (Exception e) {
                    //e.printStackTrace();
                }
                try {
                    i = u.getId();
                    facesError("Dieser Benutzername ist leider schon vergeben!");
                }
                catch (Exception e){
                    //e.printStackTrace();
                }
                if (i!=0){
                    throw new IllegalArgumentException("USER ALREADY EXISTS!");
                }
                PasswordMatcher matcher = new PasswordMatcher();
                userService.addUser(new User(UUID.randomUUID().hashCode(), vorname, nachname, email, phoneNumber, username, matcher.getPasswordService().encryptPassword(password), false, LocalDateTime.now(), List.of(Role.ADMIN), "DE"));
                //TODO redirect and send email
                FacesContext.getCurrentInstance().getExternalContext().redirect("nutzerVerwaltung.xhtml");
                System.out.println("REDIRECTED SUCCESSFULLY");
            } else {
                facesError("Passwords dont match!");
                throw new Exception("FAILED YOU MOTHERFUCKER!");
            }
        } catch (Exception e) {
           // e.printStackTrace();
        }
    }

    /**
     * Generates a new user id
     *
     * @return a new user id
     */
    private int idGenerator() {
        return UUID.randomUUID().hashCode();
    }

    /**
     * Check password matches
     *
     * @param a - the first entered password
     * @param b - the second entered password
     * @return a == b
     */
    private boolean passwordsMatch(String a, String b) {
        return a.equals(b);
    }

    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     * @param message Error Message
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

}
