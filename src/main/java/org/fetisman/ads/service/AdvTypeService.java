package org.fetisman.ads.service;

import org.fetisman.ads.domain.AdvType;
import org.fetisman.ads.domain.Catalog;
import org.fetisman.ads.repo.AdvTypeRepo;
import org.fetisman.ads.repo.CatalogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvTypeService {

    @Autowired
    private AdvTypeRepo advTypeRepo;

    @Autowired
    private CatalogRepo catalogRepo;

    public AdvType loadAdvTypeByType(String adv_type){
        return advTypeRepo.findByAdvType(adv_type);
    }

    public List<AdvType> advTypeList(Catalog catalog){
        if (catalog != null && !catalog.getTitle().isEmpty() && catalog.getId() != 0) {
            return advTypeRepo.findByCatalog(catalog);
        }else {
            return advTypeRepo.findAll();
        }
    }

    public boolean saveUpdatedAdvType(AdvType advType, String advTypeTitle) {
        AdvType advTypeFromDB = advTypeRepo.findByAdvType(advTypeTitle);
        if (advTypeFromDB!= null){
            return false;
        }

        advType.setAdvType(advTypeTitle);

        advTypeRepo.save(advType);

        return true;
    }

    public boolean addAdvType(AdvType advType, Catalog catalog) {
        AdvType advTypeFromDB = advTypeRepo.findByAdvType(advType.getAdvType());
        if (advTypeFromDB != null) {
            return false;
        }
        advType.setCatalog(catalog);
        advTypeRepo.save(advType);

        return true;
    }
}
