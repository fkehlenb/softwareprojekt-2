package de.unibremen.sfb.model;

import javax.persistence.ManyToMany;

/** The roles a user can have*/
public enum Role {
    TECHNOLOGE, PKADMIN, TRANSPORT, LOGISTIKER, ADMIN
}
