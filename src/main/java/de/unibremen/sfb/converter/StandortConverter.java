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

    @Inject
    private StandortService standortService;

    @Override
    public String toString() {
        return "StandortConverter{" +
                "standortConverter" + standortService +
                '}';
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Standort standort) {

        if (standort == null) {
            return "";
        }

        return standort.getOrt();
    }

    @Override
    public Standort getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return standortService.findByLocation(value);
    }
}
