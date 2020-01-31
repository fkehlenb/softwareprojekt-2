package de.unibremen.sfb.service;

import de.unibremen.sfb.model.User;
import de.unibremen.sfb.persistence.UserDAO;
import lombok.Getter;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.*;

@Singleton
@Getter
public class UserService {
    private List<User> users;

    @Inject
    UserDAO userDAO;

    @PostConstruct
    public void init() {
        // FIXME Load from db
        this.users = userDAO.getAll();
    }



    public void addUser(User user) {
        this.users.add(user);
    }

    public User findByName(String name) {
        // FIXME Use String as ID or convert to String
        return this.users.stream().filter(c -> c.getNachname().equals(name)).findFirst().orElse(null);
    }
}
