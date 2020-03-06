package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.User;
import de.unibremen.sfb.service.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

@SessionScoped
@Named
@Slf4j
public class LanguageBean implements Serializable{

    private static final long serialVersionUID = 1L;

    @Setter
    private UserService userService;

    @Getter
    @Setter
    private int userID;



    private static Map<String,Object> countries;
    static{
        countries = new LinkedHashMap<>();
         //label, value
        countries.put("de", Locale.GERMAN);
        countries.put("en", Locale.ENGLISH);
    }

    public Map<String, Object> getCountriesInMap() {
        return countries;
    }


    public String getLocaleCode() throws UserNotFoundException {
        try {
            return userService.getUserById(userID).getLanguage();
        }
        catch (Exception e){
            return "DEUTSCH";
        }
    }


    public void setLocaleCode(String localeCode) {
        try {
            User u = userService.getUserById(userID);
            u.setLanguage(localeCode);
            userService.updateUser(u);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Couldn't change user language! ID " + userID);
        }
    }

    //value change event listener
    public void countryLocaleCodeChanged(ValueChangeEvent e){

        String newLocaleValue = e.getNewValue().toString();
        //loop country map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {

            if(entry.getValue().toString().equals(newLocaleValue)){

                // Hier wird die Sprache gesetzt
                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale((Locale)entry.getValue());

            }
        }
    }
   // private Res

    public void setLanguage(){
        try {
            var lang =  userService.getCurrentUser().getLanguage();
            FacesContext.getCurrentInstance()
                    .getViewRoot().setLocale( new Locale(lang));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        }
    // private Res

}
