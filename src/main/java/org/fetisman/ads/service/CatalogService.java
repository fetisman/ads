package org.fetisman.ads.service;

import org.fetisman.ads.domain.Catalog;
import org.fetisman.ads.repo.CatalogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
    @Autowired
    private CatalogRepo catalogRepo;

    public Catalog loadCatalogByTitle(String title){
        return catalogRepo.findByTitle(title);
    }

    public List<Catalog> catalogList(){return catalogRepo.findAll();}
}
