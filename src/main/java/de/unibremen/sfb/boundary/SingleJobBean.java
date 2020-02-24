package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.Kommentar;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.model.ProzessSchritt;
import de.unibremen.sfb.service.ProbenService;
import lombok.Getter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;

@Named
@RequestScoped
@Getter
public class SingleJobBean implements Serializable {

    private ProzessSchritt ps;

    /**
     * saves a comment the user can type in to be added to all samples of a prozessschritt
     */
    private String kommentarForAll;

    @Inject
    private ProbenService probeService;

    @Inject
    private TechnologeView technologeView;

    public String singlejob(ProzessSchritt ps) {
        this.ps = ps;
        return "singleview.xhtml";
    }

    public String KommentarToString(Probe p) {
        return probeService.KommentarToString(p);
    }

    /**
     * adds a comment to a process step
     * @param ps the process step
     * @param c the comment
     */
    public void addComment(ProzessSchritt ps, String c) {
        if(ps == null || c == null) {
            technologeView.errorMessage("invalid input");
        }
        else {
            Kommentar k = new Kommentar(LocalDateTime.now(), c);
            for(Probe p : ps.getZugewieseneProben()) {
                technologeView.addProbenComment(p, c);
            }
        }
        kommentarForAll = "";
    }

    /*public String nextState() {
        return "f";
    }*/
}
