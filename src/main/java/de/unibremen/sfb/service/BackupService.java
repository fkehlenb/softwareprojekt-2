 package de.unibremen.sfb.service;

import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.file.UploadedFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.FileOutputStream;
import java.io.IOException;
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
     * @throws  SQLException if the SQL is malformed
     */
    public void backup() throws SQLException {
        try {
            log.info("Trying to connect with DB");
            String sqlFilePath = "./Backup_" + LocalDateTime.now() + ".sql";
            sqlFilePath = sqlFilePath.replaceAll(":","_");
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
            Query b = em.createNativeQuery("DROP ALL OBJECTS");
            b.executeUpdate();
            assert file != null;
            Query q = em.createNativeQuery("RUNSCRIPT FROM '" + file.getFileName() + "'");
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
     * @param file which will be saved to disk
     * @throws IOException falls es IO fehler gibt
     */
    public void  toDisk(UploadedFile file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file.getFileName());
        outputStream.write(file.getContent());
        log.info(file.getFileName() + " has been writen to drive");
        outputStream.close();
    }

}
