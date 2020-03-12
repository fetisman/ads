package org.example.advs.repos;

import org.example.advs.domain.AdvType;
import org.example.advs.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvTypeRepo extends JpaRepository<AdvType, Long> {
    AdvType findByAdvType(String advtype);

    List<AdvType> findByCatalog(Catalog catalog);
}
