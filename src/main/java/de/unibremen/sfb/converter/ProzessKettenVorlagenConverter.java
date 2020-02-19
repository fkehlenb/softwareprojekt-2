package de.unibremen.sfb.converter;

import de.unibremen.sfb.exception.ProzessKettenVorlageNotFoundException;
import de.unibremen.sfb.model.ProzessKettenVorlage;
import de.unibremen.sfb.model.ProzessKettenVorlage;
import de.unibremen.sfb.service.ProzessKettenVorlageService;
import de.unibremen.sfb.service.ProzessKettenVorlageService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@FacesConverter(value = "prozessKettenVorlageConverter", managed = true)
public class ProzessKettenVorlagenConverter implements Converter<ProzessKettenVorlage> {

    @Inject
    private ProzessKettenVorlageService prozessKettenVorlageService;

    @Override
    public String toString() {
        return "ProzessKettenVorlageConverter{" +
                "prozessKettenVorlageConverter" + prozessKettenVorlageService +
                '}';
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, ProzessKettenVorlage prozessKettenVorlage) {

        if (prozessKettenVorlage == null) {
            return "";
        }

        return prozessKettenVorlage.toString();
    }

    @Override
    public ProzessKettenVorlage getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        String id = null;
        Pattern pattern = Pattern.compile("pkID=\"([0-9]+)\"");
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            id = matcher.group(1);
        }
        var result = prozessKettenVorlageService.getPKV(99);
        return result;
    }
}
