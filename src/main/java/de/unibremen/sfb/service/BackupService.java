package de.unibremen.sfb.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        try {
            log.info("Trying to connect with DB");
            String sqlFilePath = "./Backup_" + LocalDateTime.now() + ".sql";
            Query q = em.createNativeQuery(String.format("SCRIPT TO '%s'", sqlFilePath));
            log.info(q.getResultList().toString());
        }
        catch (Exception e){
            e.printStackTrace();
            throw new SQLException();
        }
    }

    /** Datenbank import
     * @param file - the file to load data from
     * @throws IOException on failure */
    public void upload(UploadedFile file) throws IOException{
        try {
            if (file != null && file.getFileName() != null) {
                toDisk(file);
            }
            Query b = em.createNativeQuery(String.format("DROP ALL OBJECTS"));
            b.executeUpdate();
            Query q = em.createNativeQuery(String.format("RUNSCRIPT FROM '" + file.getFileName() + "'"));
            q.executeUpdate();
            log.info("Backup was restored with Success!");
        }
        catch (Exception e){
            e.printStackTrace();
            throw new IOException();
        }
    }

    /**
     * https://www.baeldung.com/java-write-to-file
     * @throws IOException
     */
    public void  toDisk(UploadedFile file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file.getFileName());
        outputStream.write(file.getContent());
        log.info(file.getFileName() + " has been writen to drive");
        outputStream.close();
    }

}
