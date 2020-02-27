package de.unibremen.sfb.boundary;


import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named("pszavView")
@ViewScoped
@Getter
@Setter
@Log
public class PSZAVView implements Serializable {
    private List<ProzessSchrittZustandsAutomatVorlage> selpszav;
    private List<ProzessSchrittZustandsAutomatVorlage> verpszav;
    private List<String> sourceZ;
    private List<String> targetZ;
    private DualListModel<String> dualZ;
    private String toaddd;

    @Inject
    private ProzessSchrittZustandsAutomatVorlageService prozessSchrittZustandsAutomatVorlageService;

    @Inject
    private ProzessSchrittVorlageService prozessSchrittVorlageService;

    /**
     * Hier werden aus der Persitenz die benötigten Daten Geladen
     */
    @PostConstruct
    public void init() {
        sourceZ = new ArrayList<String>();
        targetZ = prozessSchrittZustandsAutomatVorlageService.getByID(996699).getZustaende();//;
        dualZ = new DualListModel<String>(sourceZ, targetZ);
        verpszav = prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen();
        //
    }

    public void toAdd(String id) throws ProzessSchrittVorlageNotFoundException {
        ProzessSchrittZustandsAutomatVorlage prozessSchrittZustandsAutomatVorlage =  prozessSchrittZustandsAutomatVorlageService.getByID(Integer.parseInt(id));
        List<String> newZustande = prozessSchrittZustandsAutomatVorlage.getZustaende();
        newZustande.add(toaddd);
        prozessSchrittZustandsAutomatVorlage.setZustaende(newZustande);
        prozessSchrittZustandsAutomatVorlageService.edit(prozessSchrittZustandsAutomatVorlage);
        verpszav = prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen();
    }
    public String erstellePSZAV() {
        // FIXME Implementation
        log.info("Selected Zustaende: " + dualZ.getTarget());
        return "?faces-redirect=true";
    }

    public void deletePSZAV() {
        try {
            prozessSchrittZustandsAutomatVorlageService.delete(selpszav);
        } catch (ProzessSchrittVorlageNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void onRowEdit(RowEditEvent<ProzessSchrittZustandsAutomatVorlage> event) throws ProzessSchrittVorlageNotFoundException {
        //When The Persistence gefit be, we can uncomment that.
        boolean a =checkOrdnung(event);
        if(!a){
        prozessSchrittZustandsAutomatVorlageService.edit(event.getObject());
        FacesMessage msg = new FacesMessage("PS Automat Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);}
        else{
            onRowCancelWegenOrdnung();
        }

    }

    private boolean checkOrdnung(RowEditEvent<ProzessSchrittZustandsAutomatVorlage> event) {
        int counterInbearbeitung =0;
        int counterBearbeitet = 0;
        int counterAngenommen =0;
        int counterWeitergeleitet = 0;
        int counter=0;
        var list = event.getObject().getZustaende();
        for (String listz:
        list) {
            if(listz.equals("Angenommen")){
                counterAngenommen=counter;
            }
            if(listz.equals("In Bearbeitung")){
                counterInbearbeitung=counter;
            }
            if(listz.equals("Bearbeitet")){
                counterBearbeitet=counter;
            }
            if(listz.equals("Weitergeleitet")){
                counterWeitergeleitet=counter;
            }
            counter++;
        }
        if(counterBearbeitet<counterInbearbeitung &&
                counterAngenommen<counterInbearbeitung&&
                counterAngenommen<counterBearbeitet &&
                counterAngenommen<counterWeitergeleitet ){
            return true;
        }else{
            return false;
        }
    }

    public void onRowCancel(RowEditEvent<ProzessSchrittVorlage> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void onRowCancelWegenOrdnung() {
        FacesMessage msg = new FacesMessage("Bearbeitet darf nicht vor in Bearbeitung sein");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}


