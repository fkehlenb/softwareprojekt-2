package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.*;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.time.Duration;
import java.util.List;
import java.util.Set;

@Named
@RequestScoped
public class PsVErstellenBean {

    @NotEmpty
    private  int psVID;

    @NonNull
    private Duration dauer;

    @NonNull
    private List<TraegerArt> eingabeTraeger;

    @NotEmpty
    private List<TraegerArt> ausgabeTraeger;

    @NonNull
    private ProzessSchrittArt psArt;

    private Set<ExperimentierStation> stationen;

    @NonNull
    private ProzessSchrittZustandsAutomatVorlage zustandsAutomat;

    @NonNull
    private Set<ProzessSchrittParameter> prozessSchrittParameters;

}
