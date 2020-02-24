package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.Kommentar;
import de.unibremen.sfb.model.Probe;
import lombok.Getter;
import org.primefaces.event.CellEditEvent;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
@Getter
public class SingleSampleBean implements Serializable {

    private Probe p;

    @Inject
    private TechnologeView technologeView;


    public String singleprobe(Probe p) {
        this.p = p;
        return "singleprobe.xhtml";
    }

    /**
     * listener for edit of comment in singlesample view
     * @param e event
     */
    public void KommentarEdit(CellEditEvent e) {
        String ne = (String) e.getNewValue();
        int row = e.getRowIndex();
        Kommentar k = p.getKommentar().get(row);

        technologeView.editProbenComment(p, k, ne);
    }

    public SingleSampleBean() {}
}
