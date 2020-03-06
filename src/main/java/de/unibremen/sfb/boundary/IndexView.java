package de.unibremen.sfb.boundary;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * this class manages the interaction between the gui and the backend system for the initial situation (the first page any visitor sees)
 */
@ViewScoped
@Named
public class IndexView implements Serializable {

    // Define the Subject
    private Subject subject = SecurityUtils.getSubject();

    /**
     * Return the Subject
     * @return current Security Subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * returns the language everything is currently displayed in
     * @return the current language
     */
    public String getCurrentLanguage() { return null; }

    /**
     * the empty constructor
     */
    public IndexView() {}

}
