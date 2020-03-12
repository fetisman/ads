package org.example.advs.service;

import org.example.advs.domain.Catalog;
import org.example.advs.repos.CatalogRepo;
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
