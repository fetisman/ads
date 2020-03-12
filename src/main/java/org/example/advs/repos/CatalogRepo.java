package org.example.advs.repos;

import org.example.advs.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepo extends JpaRepository<Catalog, Long> {
    Catalog findByTitle(String title);
}
