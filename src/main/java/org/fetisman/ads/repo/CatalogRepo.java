package org.fetisman.ads.repo;

import org.fetisman.ads.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepo extends JpaRepository<Catalog, Long> {
    Catalog findByTitle(String title);
}
