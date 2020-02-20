package de.unibremen.sfb.converter;

import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.service.StandortService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;


@FacesConverter(value = "standortConverter", managed = true)
public class StandortConverter implements Converter<Standort> {

    /** Standort service */
    @Inject
    private StandortService standortService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, Standort id) {
        // This method is called when item value is to be converted to HTTP request parameter.
        return id.toString();
    }

    @Override
    public Standort getAsObject(FacesContext context, UIComponent component, String id) {
        // This method is called when HTTP request parameter is to be converted to item value.
        try {
            return standortService.findById(Integer.parseInt(id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
