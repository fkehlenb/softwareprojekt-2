package de.unibremen.sfb.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * this class manages the interaction between the gui and the backend system for the initial situation (the first page any visitor sees)
 */
@SessionScoped
@Named
public class IndexBean implements Serializable {

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

}
