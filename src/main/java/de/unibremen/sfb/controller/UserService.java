package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.Role;
import de.unibremen.sfb.model.User;
import lombok.Getter;
import net.sourceforge.plantuml.ugraphic.UScale;
import org.apache.shiro.crypto.hash.Hash;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.time.LocalDateTime;
import java.util.*;

@Singleton
@Getter
public class UserService {
    private Set<User> users;

    @PostConstruct
    public void init() {
        // FIXME Load from db
        this.users = createDefaultUser();
    }

    private Set<User> createDefaultUser() {
        // User Setup
        Set<Role> a = new HashSet<>();
        User tUser = new User(0, "Default", "Loser", "l@g.c", "110",
                "kev,", "12345678".getBytes(), true, LocalDateTime.now(),
                a, new ArrayList<Auftrag>(), "DEUTSCH");
        users = new HashSet<>();
        users.add(tUser);
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public User findByName(String name) {
        // FIXME Use String as ID or convert to String
        return this.users.stream().filter(c -> c.getNachname().equals(name)).findFirst().orElse(null);
    }
}
