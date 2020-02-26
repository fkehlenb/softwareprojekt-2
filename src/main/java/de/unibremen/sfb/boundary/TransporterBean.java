package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.AuftragService;
import de.unibremen.sfb.service.ProzessKettenVorlageService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Named("transportBean")
@ViewScoped
@Getter
@Setter
@Slf4j

/**
 * this bean manages the interaction of the gui with the backend system (for users who are transporters)
 */
public class TransporterBean implements Serializable {
    private List<ProzessSchritt> ps;

    @Inject
    AuftragService auftragService;
    @Inject
    ProzessKettenVorlageService prozessKettenVorlageService;

    @PostConstruct
    void init(){
        ps = auftragService.getTransportSchritt();
    }

    /**
     * the user managed by this beans
     */
    //public User transporter;

    /**
     * returns all jobs available to the transporter
     * @return a set containing all those jobs
     */
    public List<ProzessSchritt> getAuftragList() {

        return auftragService.getTransportSchritt(); }

    /**
     * sets the status of the job this transporter is currently working on
     */
    public void setAuftragStatus() {}

    /**
     * reports a sample as lost
     * @param p the sample
     */
    public void reportLostProbe(Probe p) {}

    /**
     * reports a sample as lost via its id
     * @param id the id of the sample
     */
    public void reportLostProbe(int id) {}

    /**
     * the empty constructor
     */
    public TransporterBean(){}

    /**
     * returns the transporter managed by this bean
     * @return the user
     */
    //public User getTransporter() { return transporter; }

    /**
     * sets the transporter managed by this bean
     * @param t the user
     */
    //public void setTransporter(User t) { transporter = t; }
}
