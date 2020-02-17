package de.unibremen.sfb.service;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Backup;

import java.io.Serializable;

/**
 * This class handles the database backup mechanisms
 */
@Slf4j
public class BackupService implements Serializable {

    /**
     * Backup the database
     */
    public void backupDatabase() {
        try {
            String directory = "C:\\Users\\Leonard\\Desktop\\test";
            Backup.execute(directory + "test.zip",directory,"swp2",true);
            log.info("Created backup!");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't backup database!");
        }
    }

}
