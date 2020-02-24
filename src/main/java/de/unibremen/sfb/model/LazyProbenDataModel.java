package de.unibremen.sfb.model;

import de.unibremen.sfb.service.ProbenService;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import javax.inject.Inject;
import java.util.List;

public class LazyProbenDataModel extends LazyDataModel<Probe> {

    @Inject
    ProbenService probenService;

    @Override
    public List<Probe> load(int i, int i1, List<SortMeta> list, List<FilterMeta> list1) {
        return probenService.getProbenListe(i, i1);
    }
}
