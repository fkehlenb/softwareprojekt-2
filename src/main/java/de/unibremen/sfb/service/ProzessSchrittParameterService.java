package de.unibremen.sfb.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.unibremen.sfb.exception.DuplicateProzessSchrittParameterException;
import de.unibremen.sfb.exception.ProzessSchrittParameterNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.persistence.ProzessSchrittParameterDAO;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@Getter
@Slf4j
public class ProzessSchrittParameterService implements Serializable {
    private List<ProzessSchrittParameter> parameterList;

    @Inject
    ProzessSchrittParameterDAO prozessSchrittParameterDAO;

    @PostConstruct
    public void init() {
        this.parameterList = prozessSchrittParameterDAO.getAll();
    }


    public List<ProzessSchrittParameter> getPSP() {
        return parameterList;
    }

    public List<QualitativeEigenschaft> getEigenschaften(ProzessSchrittParameter p) {
        return p.getQualitativeEigenschaften();
    }

    public void loscheParameter(ProzessSchrittParameter parameter) throws ProzessSchrittParameterNotFoundException {
        this.parameterList.remove(parameter);
        prozessSchrittParameterDAO.remove(parameter);
    }

    public ProzessSchrittParameter getPSPByID(int idPSP) throws ProzessSchrittParameterNotFoundException {
        return prozessSchrittParameterDAO.getPSPByID(idPSP);
    }

    public ProzessSchrittParameter findByName(String name) {
        // FIXME Use String as ID or convert to String
        return this.parameterList.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    public File toJSON(List<ProzessSchrittParameter> psp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("parameter.json");
        mapper.writeValue(file, psp);
        return file;
    }

    //////// Santiago Implementierung ////
//////////////////////////////////////////
    public void addProcessSP(ProzessSchrittParameter prozessSchrittParameter) throws DuplicateProzessSchrittParameterException {
        this.parameterList.add(prozessSchrittParameter);
        prozessSchrittParameterDAO.persist(prozessSchrittParameter);
    }

    public List<ProzessSchrittParameter> getAll() {
        try {
            log.info("Trying get all ProzessSchrittParameterDAO Class=ProzessSchrittParameterService");
            return prozessSchrittParameterDAO.getAll();
        } catch (Exception e) {
            log.info("error get all ProzessSchrittParameterDAO Class=ProzessSchrittParameterService");
            return null;
        }
    }

    public void update(ProzessSchrittParameter prozessSchrittParameter) {
        try {
            prozessSchrittParameterDAO.update(prozessSchrittParameter);
        } catch (ProzessSchrittParameterNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<ProzessSchrittParameter> findByQei(int idqEin) {
        List<ProzessSchrittParameter> pspList = prozessSchrittParameterDAO.getAll();
        List<ProzessSchrittParameter> prozessSchrittParameters = new ArrayList<>();

        //lista para guardar los PSP si tiene Abhangig
        for (ProzessSchrittParameter psp : pspList) {
            List<QualitativeEigenschaft> qualitativeEigenschaftList = psp.getQualitativeEigenschaften();
            for (QualitativeEigenschaft ql : qualitativeEigenschaftList) {
                if (ql.getId() == idqEin) {
                    prozessSchrittParameters.add(psp);
                    System.out.println("Post");
                }
            }
        }
        return prozessSchrittParameters;
    }


}
