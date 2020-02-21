package de.unibremen.sfb.boundary;

import de.unibremen.sfb.controller.ProbeController;
import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.primefaces.event.CellEditEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * this class manages the interaction of the gui with the backend system (for users who are technologists)
 */
@Named
@SessionScoped
@Getter
@Setter
public class TechnologeBean implements Serializable {

    /**
     * the user managed by this bean
     */
    private User technologe;

    /**
     * saves whether the user wants to see only samples that need info to be uploaded, or everything
     */
    private Boolean viewUploaded;

    private ProzessSchritt singleJob;

    @Inject
    private ExperimentierStationService esService;

    @Inject
    private ProbenService probeService;

    @Inject
    private UserService userService;

    @Inject
    private AuftragService auftragService;

    @Inject
    private ProbeController probeController;

    @Inject
    private ProzessSchrittService psService;

    private Kommentar singleKommentar;



    /**
     * loads the initial data from the database
     */
    @PostConstruct
    public void init() {
        technologe = userService.getCurrentUser(); //TODO evtl updated das nicht richtig?
        System.out.println(technologe.getUsername());
        /*
        also wenn technologe angemeldet, shit tut, und dann ein anderer angemeldeter benutzer
        währenddessen als admin den technologen an anderen stationen zuteilt,
        wird das dann richtig angezeigt? etc...
         */
    }

    /**
     * returns the experimentation stations this user is assigned to
     * @return a list containing all stations this user is assigned to
     */
    public List<ExperimentierStation> getStationen() {
        return esService.getESByUser(technologe);
    }

    /**
     * returns the assignments currently available for this user
     * @return a set containing all availabe jobs
     */
    public Set<ProzessSchritt> getAuftrag() {
        Set<ProzessSchritt> res = new HashSet<>();
        //alle einträge in queues von experimentierstationen denene der user zugeordnet ist
        return res;
    }

    /**
     *
     * NOT SOMETHING THE TECHNOLOGE IS SUPPOSED TO BE ABLE TO DO
     *
     * sets the state of a job
     * @param a the job
     * @param zustand the new state
     */
    public void setAuftragsZustand(Auftrag a, Enum<ProzessKettenZustandsAutomat> zustand) {
        if(a == null || zustand == null) {
            errorMessage("invalid input");
        }
        else {
            try {
                auftragService.setAuftragsZustand(a, zustand);
            } catch (AuftragNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * sets the state of a ProzessSchritt on further than it was
     * @param a the ProzessSchritt
     */
    public void setJobZustand(ProzessSchritt a) {
        if(a == null) {
            errorMessage("invalid input");
        }
        else {
            int i = 0;
            while(!a.getZustandsAutomat().getProzessSchrittZustandsAutomatVorlage().getZustaende().get(i).equals(a.getZustandsAutomat().getCurrent())) {
                i++;
            }
            try {
                psService.setZustand(a, a.getZustandsAutomat().getProzessSchrittZustandsAutomatVorlage().getZustaende().get(i+1));
            }
            catch(ProzessSchrittNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * reports an experimentation station as broken
     * @param es the station
     */
    public void reportBroken(ExperimentierStation es) {
        if(es == null) {
            errorMessage("invalid input");
        }
        else {
            try {
                esService.setZustand(es, ExperimentierStationZustand.KAPUTT);
            } catch (ExperimentierStationNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * assigns this user to a job
     * @param a the job
     */
    public void assignToAuftrag(Auftrag a) {
        if(a==null) {
            errorMessage("invalid input");
        }
        else {
            try {
                auftragService.assignToAuftrag(technologe, a);
            } catch (AuftragNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * assigns this user to a prozessSchritt
     * @param ps the prozessschritt
     */
    public void assignToAuftrag(ProzessSchritt ps) {
        //find auftrag this prozessschritt belongs to
        //then assignToAuftrag(a);
    }

    /**
     * creates a new sample (happens in "urformende" process chains)
     * @param id the sample id of the new sample
     */
    public void createUrformend(String id) {
        //probeService.addNewSample(id);
        //TODO kann das der technologe wirklich selber?
    }

    /**
     * adds a comment to a sample
     * @param p the sample
     * @param c the comment
     */
    public void addProbenComment(Probe p, String c) {
        if(p == null || c == null) {
            errorMessage("invalid input");
        }
        else {
            try {
                probeService.addProbenComment(p, c);
            } catch (ProbeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * edits a comment belonging to a sample
     * @param p the sample
     * @param c the comment
     */
    public void editProbenComment(Probe p, Kommentar k, String c) {
        if(p == null || c == null || k == null) {
            errorMessage("invalid input");
        }
        else {
            try {
                probeService.editProbenComment(p, k, c);
            }
            catch(ProbeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * deletes a comment belonging to a sample
     * @param p the sample
     * @param k the comment
     */
    public void deleteProbenComment(Probe p, Kommentar k) {
        if(p == null || k == null) {
            errorMessage("invalid input");
        }
        else {
            try {
                probeService.deleteProbenComment(p, k);
            }
            catch(ProbeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * returns all samples to which the user has not yet uploaded data
     * @return a set containing all those samples
     */
    public List<Probe> viewToBeUploaded() {
        List<Probe> res = new LinkedList<>();
        for(ProzessSchritt ps : getJobs()) {
            if(!ps.isUploaded()) {
                res.addAll(ps.getZugewieseneProben());
            }
        }
        return res;
    }

    /**
     * uploads a sample
     * @param p the sample
     */
    public void upload(Probe p) {

    }

    /**
     * reports a sample as lost
     * @param p the sample
     */
    public void reportLostProbe(Probe p) {
        probeController.setZustandForProbe(p, ProbenZustand.VERLOREN);
    }

    /**
     * reports a sample as lost by its id
     * @param id the id of the sample to be reported
     */
    public void reportLostProbe(String id) {
        if(id==null) {
            errorMessage("invalid input");
        }
        else {
            try {
                reportLostProbe(probeService.getProbeById(id));
            } catch (ProbeNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * creates and sends an error message
     * @param e error messsage
     */
    public void errorMessage(String e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e, null));
    }

    /**
     * the empty constructor
     */
    public TechnologeBean() {}

    /**
     * returns the technologist managed by this bean
     * @return the user
     */
    public User getTechnologe() {
        return technologe;
    }

    /**
     * sets the technologist managed by this bean
     * @param technologe the new user
     */
    public void setTechnologe(User technologe) {
        this.technologe = technologe;

    }

    /**
     * returns the jobs this user is currently assigned to
     * ProzessSchritte, not Auftrag, as the Technologe is
     * not supposed to see entire job chains, just what they
     * have to do
     *
     * @return a list containing all the jobs
     */
    public List<ProzessSchritt> getJobs() {
        //return psService.getSchritteByUser(technologe);
        //also nur ein technologe pro Station, und
        //kann erst auftrag annehmen, wenn er an dieser station nichts zu tun
        //nur ein auftrag pro station, und ein technologe pro station
        List<ProzessSchritt> r = new LinkedList<>();
        ProzessSchrittZustandsAutomatVorlage v = new ProzessSchrittZustandsAutomatVorlage();
        ProzessSchrittZustandsAutomat psz = new ProzessSchrittZustandsAutomat("sad", v);
        List<ProzessSchrittLog> l = new LinkedList<>();
        List<ExperimentierStation> le = new LinkedList<>();
        List<Bedingung> be = new LinkedList<>();
        ProzessSchrittVorlage vl = new ProzessSchrittVorlage(0, Duration.ZERO, "sdhvoa", le, be);
        ProzessSchritt p = new ProzessSchritt(0, psz, l, vl);
        ProzessSchritt q = new ProzessSchritt(1, psz, l, vl);
        ProzessSchritt w = new ProzessSchritt(3, psz, l, vl);
        r.add(p);
        r.add(q);
        r.add(w);

        return r;
    }

    public List<Probe> getSamples() {
        /*if(viewUploaded) {
            return viewToBeUploaded();
        }
        return probeService.getProbenByUser(technologe);*/

        List<Probe> res = new LinkedList<>();
        /*for(ProzessSchritt ps : getJobs()) {
            res.addAll(ps.getTraeger().getProben());
        }*/
        Probe p = new Probe("asdf", ProbenZustand.VERLOREN, new Standort(0, "asdv"));
        List<Kommentar> ks = new LinkedList<>();
        ks.add(new Kommentar(LocalDateTime.now(), "saödvjkb"));
        ks.add(new Kommentar(LocalDateTime.now(), "ädkänbvaejk"));
        p.setKommentar(ks);
        Probe q = new Probe("dsgnfg", ProbenZustand.KAPUTT, new Standort(0, "asdv"));
        q.setKommentar(ks);
        res.add(p);
        res.add(q);
        return res;
    }

    public String KommentarToString(Probe p) {
        return probeService.KommentarToString(p);
    }
}
