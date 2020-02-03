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

        @Inject
        private UserService userService;

        @Override
        public String toString() {
            return "UserConverter{" +
                    "userConverter" + userService +
                    '}';
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, User user) {

            if (user == null) {
                return "";
            }

            return user.getNachname();
        }

        @Override
        public User getAsObject(FacesContext context, UIComponent component, String value) {
            if (value == null || value.isEmpty()) {
                return null;
            }
            /* make for Hurwitz Commentiert for Rey, um konflicten zu vermeiden
            return userService.findByName(value);*/
            return null;
        }
}
