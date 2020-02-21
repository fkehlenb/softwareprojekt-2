package de.unibremen.sfb.converter;

import de.unibremen.sfb.model.User;
import de.unibremen.sfb.service.UserService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;


@FacesConverter(value = "userConverter", managed = true)
public class UserConverter implements Converter<User> {

    /**
     * User service
     */
    @Inject
    private UserService userService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, User id) {
        return String.valueOf(id);
    }

    @Override
    public User getAsObject(FacesContext context, UIComponent component, String id) {
        try {
            return userService.getUserByUsername(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
