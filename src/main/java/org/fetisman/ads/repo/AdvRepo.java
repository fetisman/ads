package org.fetisman.ads.repo;

import org.fetisman.ads.domain.Adv;
import org.fetisman.ads.domain.AdvType;
import org.fetisman.ads.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvRepo extends CrudRepository<Adv, Long> {
    Page<Adv> findAll(Pageable pageable);

    @Query("from Adv a where a.advType = :type")
    Page<Adv> findByType(Pageable pageable, @Param("type") AdvType type);

    @Query("from Adv a where a.author = :author")
    Page<Adv> findByUser(Pageable pageable, @Param("author") User author);

    Page<Adv> findByAdvTypeIn(Pageable pageable, List<AdvType> advTypes);
}
