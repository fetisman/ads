package org.fetisman.ads.repo;

import org.fetisman.ads.domain.AdvType;
import org.fetisman.ads.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvTypeRepo extends JpaRepository<AdvType, Long> {
    AdvType findByAdvType(String advtype);

    List<AdvType> findByCatalog(Catalog catalog);
}
