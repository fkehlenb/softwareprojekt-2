package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.model.Kommentar;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.service.ProbenService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.CellEditEvent;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
@Getter
@Setter
@Slf4j
public class SingleSampleBean implements Serializable {

    private Probe p;

    @Inject
    private ProbenService probenService;

    @Inject
    private TechnologeView technologeView;

    private String singleKommentar;


    public String singleprobe(Probe p) {
        this.p = p;
        return "singleprobe.xhtml";
    }

    /**
     * listener for edit of comment in singlesample view
     * @param e event
     */
    public void KommentarEdit(CellEditEvent<String> e) {
        String ne = e.getNewValue();
        int row = e.getRowIndex();
        Kommentar k = p.getKommentar().get(row);

        editProbenComment(p, k, ne);
    }

    /**
     * edits a comment belonging to a sample
     * @param p the sample
     * @param c the comment
     */
    public void editProbenComment(Probe p, Kommentar k, String c) {
        try {
            probenService.editProbenComment(p, k, c);
            log.info("the comment " + k.getId() + " of probe " + p.getProbenID() + " was edited to " + c);
        }
        catch(ProbeNotFoundException e) {
            e.printStackTrace();
            log.info("an error occurred trying to update comment " + k.getId() + " of sample " + p.getProbenID() + " : " + e.getMessage());
        }
        catch(IllegalArgumentException e) {
            errorMessage("invalid input");
        }
    }

    /**
     * deletes a comment belonging to a sample
     * @param k the comment
     */
    public void deleteProbenComment(Kommentar k) {
        try {
            probenService.deleteProbenComment(p, k);
            log.info("comment " + k.getId() + " of probe " + p.getProbenID() + " was deleted");
        }
        catch(ProbeNotFoundException e) {
            e.printStackTrace();
            log.info("an error occurred trying to delete comment " + k.getId() + " of sample " + p.getProbenID() + " : " +e.getMessage());
        }
        catch(IllegalArgumentException e) {
            errorMessage("invalid input");
        }

    }

    /**
     * adds a comment to a sample
     * @param c the comment
     */
    public void addProbenComment(String c) {
        try {
            probenService.addProbenComment(p, c);
            log.info("the comment " + c + " was added to the sample " + p.getProbenID());
        } catch (ProbeNotFoundException e) {
            e.printStackTrace();
            log.info("an error occurred trying to add comment " + c + " to sample " + p.getProbenID() + " : " +e.getMessage());
        }
        catch(IllegalArgumentException e) {
            errorMessage("invalid input");
        }
    }

    public SingleSampleBean() {}

    /**
     * creates and sends an error message
     * @param e error messsage
     */
    public void errorMessage(String e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e, null));
        log.info("an error occurred" + e);
    }

}
