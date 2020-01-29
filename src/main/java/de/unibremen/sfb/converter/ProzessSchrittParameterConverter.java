package de.unibremen.sfb.converter;

import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.service.ProzessSchrittParameterService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;


@FacesConverter(value = "prozessSchrittParameterConverter", managed = true)
public class ProzessSchrittParameterConverter implements Converter<ProzessSchrittParameter> {

    @Inject
    private ProzessSchrittParameterService prozessSchrittParameterService;

    @Override
    public String toString() {
        return "ProzessSchrittParameterConverter{" +
                "prozessSchrittParameterConverter" + prozessSchrittParameterService +
                '}';
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, ProzessSchrittParameter prozessSchrittParameter) {

        if (prozessSchrittParameter == null) {
            return "";
        }

        return prozessSchrittParameter.getName();
    }

    @Override
    public ProzessSchrittParameter getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return prozessSchrittParameterService.findByName(value);
    }

}
