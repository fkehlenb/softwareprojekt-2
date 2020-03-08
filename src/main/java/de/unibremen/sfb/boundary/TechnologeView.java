package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.Assert;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * this class manages the interaction of the gui with the backend system (for users who are technologists)
 */
@Named
@ViewScoped
@Getter
@Setter
@Slf4j
public class TechnologeView implements Serializable {

    /**
     * Job Service
     */
    @Inject
    private AuftragService auftragService;

    /**
     * Experimenting station service
     */
    @Inject
    private ExperimentierStationService experimentierStationService;

    /**
     * Process Step Service
     */
    @Inject
    private ProzessSchrittService prozessSchrittService;

    // TODO immer wieder neu laden mit der unteren id
    /**
     * the user managed by this bean
     */
    private User technologe;

    /**
     * List of all his jobs
     */
    private List<Auftrag> auftragList;

    /**
     * Sample Service
     */
    @Inject
    private ProbenService probeService;

    /**
     * User Service
     */
    @Inject
    private UserService userService;

    private ProzessSchritt parameterschritt;

    /**
     * loads the initial data from the database
     */
    @PostConstruct
    public void init() {
        try {
            technologe = userService.getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage("Couldn't grab current user! Error " + e.getMessage());
        }
    }


    public int getAuslastung(int esID) {
        try {
            ExperimentierStation e = null;
            try {
                e = experimentierStationService.getById(esID);
            } catch (ExperimentierStationNotFoundException enf) {
                log.error(enf.getLocalizedMessage());
            }
            int auslastung = 0;
            assert e != null;
            List<ProzessSchritt> nextSteps = e.getNextPS();
            for (ProzessSchritt ps :
                    nextSteps) {
                String[] parts = ps.getDuration().split(":");
                auslastung += Integer.parseInt(parts[0]);
            }
            return auslastung;
        } catch (Exception e) {
            return 0;
        }
    }

    public int getDauer(int psID) {
        try {
            ProzessSchritt psLast = null;
            int dauer = 0;
            try {
                psLast = prozessSchrittService.getObjById(psID);
            } catch (ProzessSchrittNotFoundException e) {
                e.printStackTrace();
            }
            List<ProzessSchritt> steps = auftragService.prevSteps(psLast);
            for (ProzessSchritt ps :
                    steps) {
                String[] parts = ps.getDuration().split(":");
                dauer += Integer.parseInt(parts[0]);
            }
            return dauer;
        } catch (Exception e) {
            return 0;
        }
    }

    public int getAnzahlAuftraege(int esID) {
        try {
            ExperimentierStation es = null;
            try {
                es = experimentierStationService.getById(esID);
            } catch (ExperimentierStationNotFoundException e) {
                e.printStackTrace();
            }
            assert es != null;
            return es.getNextPS().size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getProbenAnzahl(int esID) {
        try {
            ExperimentierStation es = null;
            try {
                es = experimentierStationService.getById(esID);
            } catch (ExperimentierStationNotFoundException e) {
                e.printStackTrace();
            }
            return probeService.getProbenByStandort(es.getStandort()).size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * returns the experimentation stations this user is assigned to
     *
     * @return a list containing all stations this user is assigned to
     */
    public List<ExperimentierStation> getStationen() {
        try {
            List<ExperimentierStation> res = experimentierStationService.getESByUser(technologe);
            res = res.stream().filter(c -> !c.getStandort().getOrt().equals("Lager")).collect(Collectors.toList());
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Hole alle current schritte der stationen, denen der technologe zugeordnet ist
     *
     * @return liste mit current schritten
     */
    //TODO ONLY THE ONES WITH AUFTRAG GESTARTET
    public List<ProzessSchritt> getSchritte() {
        List<ProzessSchritt> r = new ArrayList<>();
        try {
            r = prozessSchrittService.getSchritte();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            log.error("Could find any Steps");
            return new ArrayList<>();
        }
        return r;
    }

    /**
     * verfügbare schritte in den experimentierstationen des technologen
     *
     * @return liste
     */
    public List<ProzessSchritt> getJobs() {
        return prozessSchrittService.getJobs(technologe);
    }

    /**
     * finds the station a process step is currently at
     * the step belongs to a station this user is at
     *
     * @param ps the step
     * @return the station
     */
    public ExperimentierStation findStandort(ProzessSchritt ps) {
        try {
            return experimentierStationService.findStation(ps);
        } catch (IllegalArgumentException e) {
            errorMessage("invalid input finding the location");
            return null;
        }
    }

    /**
     * Report experimenting station as broken
     *
     * @param es - the experimenting station to report as broken
     */
    public void reportBroken(ExperimentierStation es) {
        try {
            if (!es.getStatus().equals(ExperimentierStationZustand.KAPUTT)) {
                es.setStatus(ExperimentierStationZustand.KAPUTT);
                experimentierStationService.updateES(es);
                log.info("Reported station as broken! ID " + es.getEsID());
                facesNotification("Reported experimenting station as broken!");
            } else {
                errorMessage("Station already reported broken!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't set experimenting station status to broken! Error " + e.getMessage());
            errorMessage("Couldn't report station as broken!");
        }
    }

    /**
     * creates a new sample (happens in "urformende" process chains)
     *
     * @param id the sample id of the new sample
     */
    public void createUrformend(String id) {
        //probeService.addNewSample(id);

    }

    /**
     * returns all samples to which the user has not yet uploaded data
     *
     * @return a set containing all those samples
     * // FIXME Keine PS
     */
    public List<Probe> viewToBeUploaded() {
        try {
            List<Probe> r = null;
            try {
                r = probeService.viewToBeUploaded();
            } catch (AuftragNotFoundException e) {
                e.printStackTrace();
                return new ArrayList<Probe>();
            }
            log.info("Probe die Hochgeladen werden muessen wurden geladen");
            return r;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * uploads a sample
     *
     * @param p the sample
     */
    public void upload(Probe p) {

    }

    /**
     * reports a sample as lost
     *
     * @param p the sample
     */
    public void reportLostProbe(Probe p) {
        try {
            probeService.setZustandForProbe(p, ProbenZustand.VERLOREN);
            log.info("sample " + p.getProbenID() + " was reported as missing");
        } catch (ProbeNotFoundException e) {
            e.printStackTrace();
            log.info("sample " + p.getProbenID() + " could not be found when trying to report as missing");
        } catch (IllegalArgumentException e) {
            errorMessage("invalid input");
        }
    }

    /**
     * finds the priority of a process step
     *
     * @param id of the step
     * @return the priority of the Auftrag the process step belongs to
     */
    public AuftragsPrioritaet getPriority(int id) {
        try {
            ProzessSchritt ps = null;
            try {
                ps = prozessSchrittService.getObjById(id);
            } catch (ProzessSchrittNotFoundException e) {
                e.printStackTrace();
            }
            Auftrag r = null;
            r = auftragService.getAuftrag(ps);
            return r.getPriority();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * creates and sends an error message
     *
     * @param e error messsage
     */
    private void errorMessage(String e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e, null));
        log.info("an error occurred" + e);
    }

    /**
     * the empty constructor
     */
    public TechnologeView() {
    }

    public String KommentarToString(Probe p) {
        return probeService.KommentarToString(p);
    }


    /**
     * Adds a new SEVERITY_INFO FacesMessage for the ui
     *
     * @param message Info Message
     */
    private void facesNotification(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }
}
