package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.ProbenService;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Named
@SessionScoped
@Getter
@Setter
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
        return "singlejob.xhtml";
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

    public List<ProzessSchrittParameter> getParameter() {
        List<ProzessSchrittParameter> r = new LinkedList<>();
        for(Bedingung b : ps.getProzessSchrittVorlage().getBedingungen()) {
            r.addAll(b.getProzessSchrittParameter());
        }
        return r;
    }

    public void download() {
        technologeView.download(getParameter());
    }

    /*public String nextState() {
        return "f";
    }*/
}
