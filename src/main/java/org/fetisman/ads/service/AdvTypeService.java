package org.fetisman.ads.service;

import org.fetisman.ads.domain.AdvType;
import org.fetisman.ads.domain.Catalog;
import org.fetisman.ads.repo.AdvTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvTypeService {

    @Autowired
    private AdvTypeRepo advTypeRepo;

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

}
