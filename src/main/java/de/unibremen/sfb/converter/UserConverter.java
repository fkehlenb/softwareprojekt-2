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
        System.out.println(id);
        return String.valueOf(id); // @return username
    }

    @Override
    public User getAsObject(FacesContext context, UIComponent component, String id) {
        try {
            System.out.println("AS OBJECT: " + id);
            User u = userService.getUserById(Integer.parseInt(id));
            System.out.println("User found!");
            return userService.getUserById(Integer.parseInt(id));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
