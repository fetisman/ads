package org.fetisman.ads.service;

import java.util.Collections;
import java.util.UUID;
import javax.persistence.NonUniqueResultException;
import org.fetisman.ads.domain.Catalog;
import org.fetisman.ads.domain.Role;
import org.fetisman.ads.domain.User;
import org.fetisman.ads.repo.CatalogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.util.StringUtils;

@Service
public class CatalogService {
    @Autowired
    private CatalogRepo catalogRepo;

    public Catalog loadCatalogByTitle(String title){
        return catalogRepo.findByTitle(title);
    }

    public List<Catalog> catalogList(){return catalogRepo.findAll();}

    public boolean saveUpdatedCatalog(Catalog catalog, String title) {
            Catalog catalogFromDB = catalogRepo.findByTitle(title);
            if (catalogFromDB!= null){
                return false;
            }

        catalog.setTitle(title);

        catalogRepo.save(catalog);

        return true;
    }

    public void deleteCatalog(Catalog catalog) {
        catalogRepo.delete(catalog);
    }

    public boolean addCatalog(Catalog catalog) {
        Catalog catalogFromDB = catalogRepo.findByTitle(catalog.getTitle());
        if (catalogFromDB!= null){
            return false;
        }

        catalogRepo.save(catalog);

        return true;
    }
}
