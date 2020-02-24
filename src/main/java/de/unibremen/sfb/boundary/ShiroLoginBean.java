package de.unibremen.sfb.boundary; /**
 * Very simple bean that authenticates the user via Apache Shiro, using JSF
 * @author Daniel Mascarenhas
 */


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
@Slf4j  // WICHTIG TODO nicht @Log sondern @slf4j
public class ShiroLoginBean implements Serializable {
//    private static final Logger log = LoggerFactory.getLogger(ShiroLoginBean.class);

    private String username;
    private String password;
    private Boolean rememberMe;

    public ShiroLoginBean() {
    }

    /**
     * Try and authenticate the user
     */
    public void doLogin() {
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), getPassword(), getRememberMe());

        try {
            subject.login(token);

            if (subject.hasRole("admin")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } //FIXME other Roles
            else if (subject.hasRole("logistik")){
                FacesContext.getCurrentInstance().getExternalContext().redirect("logistik/index.xhtml");
            }
            else if (subject.hasRole("pkAdmin")){
                FacesContext.getCurrentInstance().getExternalContext().redirect("pkAdmin/index.xhtml");
            }
            else if (subject.hasRole("transport")){
                FacesContext.getCurrentInstance().getExternalContext().redirect("transport/index.xhtml");
            }
            else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            }
        }
        catch (UnknownAccountException ex) {
            facesError("Unknown account");
            log.error("Something else is wrong here");
        }
        catch (IncorrectCredentialsException ex) {
            facesError("Wrong password");
            log.error(ex.getMessage(), ex);
        }
        catch (LockedAccountException ex) {
            facesError("Locked account");
            log.error(ex.getMessage(), ex);
        }
        catch (AuthenticationException | IOException ex) {
            facesError("Unknown error: " + ex.getMessage());
            log.error(ex.getMessage(), ex);
        }
        finally {
            token.clear();
        }
    }

    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     * @param message Error Message
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = senha;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean lembrar) {
        this.rememberMe = lembrar;
    }
}
