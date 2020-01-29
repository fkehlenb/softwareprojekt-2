package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.model.ProzessSchrittParameter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;


@FacesConverter(value = "experimentierStationConverter", managed = true)
public class ExperimentierStationConverter implements Converter<ExperimentierStation> {

    @Inject
    private ExperimentierStationService experimentierStationService;

    @Override
    public String toString() {
        return "ExperimentierStationConverter{" +
                "experimentierStationConverter" + experimentierStationService +
                '}';
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, ExperimentierStation experimentierStation) {

        if (experimentierStation == null) {
            return "";
        }

        return experimentierStation.getName();
    }

    @Override
    public ExperimentierStation getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return experimentierStationService.findByName(value);
    }
}
