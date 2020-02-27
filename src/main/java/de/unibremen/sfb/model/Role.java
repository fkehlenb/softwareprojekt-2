package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;

/**
 * The roles a user can have
 */

@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "Role.getAll", query = "SELECT u FROM Role u WHERE u.isValidData = true"),
        @NamedQuery(name = "Role.getByName", query = "select u from Role u where u.isValidData = true and u.username = :name"),
        @NamedQuery(name = "Role.getByUser", query = "select r from Role r where r.username = :username and r.isValidData = true")
})
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {

    /** ID */
    @Id
    @NonNull
    private int id;

    /**
     * On delete set to invalid
     */
    @NonNull
    private boolean isValidData = true;

    /**
     * Role type
     */
    @NonNull
    private String name;

    /** User assigned the role */
    private String username;
}
