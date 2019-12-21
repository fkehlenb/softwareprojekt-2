package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.ProzessSchrittParameter;
import de.unibremen.sfb.Model.QualitativeEigenschaft;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;

public class BedingungController {

    public ModuleLayer.Controller controller;

    public void setPSZ(Set<ProzessSchrittParameter> psz) {}

    public Set<ProzessSchrittParameter> getPSZ() { return null; }

    public void setEigenschaften(Set<QualitativeEigenschaft> eigenschaft) {}

    public Set<QualitativeEigenschaft> getEigenschaften() { return null; }

    public void setBedingung(Pair<Set<ProzessSchrittParameter>,Set<QualitativeEigenschaft>> bedingung) {}

    public Pair<Set<ProzessSchrittParameter>,Set<QualitativeEigenschaft>> getBedingung() { return null; }
}
