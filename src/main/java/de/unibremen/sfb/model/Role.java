package de.unibremen.sfb.model;

import de.unibremen.sfb.persistence.RoleE;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

/** The roles a user can have*/

    @Data
    @Entity
    @NamedQueries({
            @NamedQuery(name = "Role.findById", query = "SELECT u from Role u WHERE u.id = :id"),
            @NamedQuery(name = "Role.getAll", query = "SELECT u FROM Role u WHERE u.isValidData = true")
    })
    @RequiredArgsConstructor(access = AccessLevel.PUBLIC)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class Role {

    /**
     * On delete set to invalid
     */
    @NonNull
    private boolean isValidData = true;

    /**
     * User's id can be used for Identifying Roles refferenced
     */
    @Id
    @NonNull
    String name;
}
