package de.unibremen.sfb.service;

import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * This class handles the database backup mechanisms
 */
@Slf4j
public class BackupService implements Serializable {

    /**
     * The entity manager
     */
    @PersistenceContext(name = "swp2")
    private EntityManager em;

    /**
     * Backup the database
     */
    public void backup() throws SQLException {
        log.info("Trying to connect with DB");
        String sqlFilePath = "./Backup_" + LocalDateTime.now() + ".sql";
        Query q = em.createNativeQuery(String.format("SCRIPT TO '%s'", sqlFilePath));
        log.info(q.getResultList().toString());
        FacesMessage message = new FacesMessage("Successfuly saved DB", sqlFilePath + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
