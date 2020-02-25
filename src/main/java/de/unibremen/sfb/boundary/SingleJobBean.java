package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateProzessSchrittLogException;
import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittLogNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.ProbenService;
import de.unibremen.sfb.service.ProzessSchrittParameterService;
import de.unibremen.sfb.service.ProzessSchrittService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.omnifaces.util.Faces;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Named
@SessionScoped
@Getter
@Setter
@Slf4j
public class SingleJobBean implements Serializable {

    private ProzessSchritt ps;

    /**
     * saves a comment the user can type in to be added to all samples of a prozessschritt
     */
    private String kommentarForAll;

    @Inject
    private ProbenService probeService;

    @Inject
    private ProzessSchrittParameterService prozessSchrittParameterService;

    @Inject
    private ProzessSchrittService psService;

    public String singlejob(ProzessSchritt ps) {
        this.ps = ps;
        return "singlejob.xhtml";
    }

    public String KommentarToString(Probe p) {
        return probeService.KommentarToString(p);
    }

    /**
     * adds a comment to a process step
     * @param c the comment
     */
    public void addComment(String c) {
        if(ps == null) {
            errorMessage("invalid input");
        }
        else {
            Kommentar k = new Kommentar(LocalDateTime.now(), c);
            for (Probe p : ps.getZugewieseneProben()) {
                try {
                    probeService.addProbenComment(p, c); //TODO
                } catch (ProbeNotFoundException e) {
                    e.printStackTrace();
                    log.info("the sample " + p.getProbenID() + " could not be found while trying to add comment " + c);
                }
                catch(IllegalArgumentException e) {
                    errorMessage("invalid input");
                }
            }
        }
        kommentarForAll = "";
    }

    /**
     * returns the ProzessSchrittParameter of the ProzessSchritt this bean manages
     * @return a list containing the prozessSchrittParameter
     */
    public List<ProzessSchrittParameter> getParameter() {
        List<ProzessSchrittParameter> r = new LinkedList<>();
        for(Bedingung b : ps.getProzessSchrittVorlage().getBedingungen()) {
            r.addAll(b.getProzessSchrittParameter());
        }
        return r;
    }

    /**
     * downloads the parameters as a json file
     */
    public void download() {
        download(getParameter());
    }

    /**
     * creates and sends an error message
     * @param e error messsage
     */
    public void errorMessage(String e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e, null));
        log.info("an error occurred" + e);
    }

    /**
     * downloads a process step parameter
     * @param psp the parameter
     */
    public void download(List<ProzessSchrittParameter> psp) {
        try {
            Faces.sendFile(prozessSchrittParameterService.toJSON(psp), true);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sets the state of a ProzessSchritt on further than it was
     */
    public void setJobZustand() {
            int i = 0;
            while(!ps.getProzessSchrittZustandsAutomat().getProzessSchrittZustandsAutomatVorlage().getZustaende().get(i).equals(ps.getProzessSchrittZustandsAutomat().getCurrent())) {
                i++;
            }
            try {
                psService.setZustand(ps, ps.getProzessSchrittZustandsAutomat().getProzessSchrittZustandsAutomatVorlage().getZustaende().get(i+1));
                log.info("set state of ProzessSchritt " + ps.getPsID() + " to " + ps.getProzessSchrittZustandsAutomat().getCurrent());
            }
            catch(ProzessSchrittNotFoundException | ProzessSchrittLogNotFoundException | DuplicateProzessSchrittLogException e) {
                e.printStackTrace();
                log.info("an error occurred trying to update the state of " + ps.getPsID() + ": " + e.getMessage());
            }
            catch(IllegalArgumentException e) {
                errorMessage("invalid input");
            }

    }

    /*public String nextState() {
        return "f";
    }*/
}
