package de.unibremen.sfb.converter;

import de.unibremen.sfb.model.Auftrag;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "auftragConverter", managed = true)
public class AuftragsConverter implements Converter<Auftrag> {

    @Override
    public String toString() {
        return "AuftragConverter{" +
                "auftragConverter" +
                '}';
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Auftrag auftrag) {

        if (auftrag == null) {
            return "";
        }

        return auftrag.toString();
    }

    @Override
    public Auftrag getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
//            return auftragService.getAuftrag(Integer.parseInt(value)); // FIXME
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
