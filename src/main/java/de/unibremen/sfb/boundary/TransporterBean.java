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
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named("transportBean")
@ViewScoped
@Getter
@Setter
@Slf4j
@Transactional

/*
  this bean manages the interaction of the gui with the backend system (for users who are transporters)
 */
public class TransporterBean implements Serializable {
    private List<ProzessSchritt> ps;
    private List<ProzessSchritt> ps2;
    private List<TransportAuftrag> transportAuftragSelected;

    @Inject
    private AuftragService auftragService;
    @Inject
    private ProzessKettenVorlageService prozessKettenVorlageService;
    @Inject
    private TransportAuftrag transportAuftrag;

    @PostConstruct
    void init(){
        ps = auftragService.getTransportSchritt();
        ps2 = auftragService.getTransportSchritt2();

    }

    /**
     * returns all jobs available to the transporter
     * @return a set containing all those jobs
     */
    public List<ProzessSchritt> getAuftragList() {

        return auftragService.getTransportSchritt();
    }

    /**
     * sets the status of the job this transporter is currently working on
     */
    public void changeTransportZustandAbgeholt(int TransportID) {
      try {
          TransportAuftrag tr = auftragService.getTransportAuftragByID(TransportID);
          auftragService.sedTransportZustandAbgeholt(tr);
          log.info("TransportAuftragZustand wurde gewechselt auf Abgeholt " + TransportID);

      }
      catch (Exception e) {
          e.printStackTrace();
          log.error("Failed to change state to Abgeholt" + TransportID);
      }
    }

    /**
     * sets the status of the job this transporter is currently working on
     */
    public void changeTransportZustandAbgeliefert(int TransportID) {
        try {
            TransportAuftrag tr = auftragService.getTransportAuftragByID(TransportID);
            auftragService.sedTransportZustandAbgeliefert(tr);
            log.info("TransportAuftragZustand wurde gewechselt auf Abgeliefert " + TransportID);

        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to change state to Abgeliefert" + TransportID);
        }
    }

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




}
