package de.unibremen.sfb.boundary;

import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.*;

@Named("fileUploadView")
@RequestScoped
@Slf4j
@Transactional
public class FileUploadView {
    @PersistenceContext
    EntityManager em;

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }


    public void upload() {
        if (file != null && file.getFileName() != null) {
            try {
                file.write("." + file.getFileName());
                toDisk(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Query b = em.createNativeQuery("DROP ALL OBJECTS");
            b.executeUpdate();

            Query q = em.createNativeQuery("RUNSCRIPT FROM '" + file.getFileName() + "'");
            q.executeUpdate();
            log.info("Backup was restored with Succes");
            FacesMessage message = new FacesMessage("Successfuly restored DB", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            assert file != null;
            FacesMessage message = new FacesMessage("No Success", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /**
     * https://www.baeldung.com/java-write-to-file
     * @throws IOException when an IO error occurs
     */
    public void toDisk(UploadedFile file)
            throws IOException {
        FileOutputStream outputStream = new FileOutputStream(".xczzc" + file.getFileName());
        outputStream.write(file.getContent());
        log.info(file.getFileName() + " has been writen to drive");
        outputStream.close();
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
