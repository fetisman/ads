package org.fetisman.ads.service;

import org.fetisman.ads.domain.Adv;
import org.fetisman.ads.domain.AdvType;
import org.fetisman.ads.domain.User;
import org.fetisman.ads.repo.AdvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvService {
    @Autowired
    private AdvRepo advRepo;

    public Page<Adv> advList(Pageable pageable, AdvType filter){
        if (filter != null && !filter.getAdvType().isEmpty()) {
            return advRepo.findByType(pageable, filter);
        } else {
            return advRepo.findAll(pageable);
        }
    }

    public Page<Adv> advList(Pageable pageable, List<AdvType> advTypes){
        return advRepo.findByAdvTypeIn(pageable, advTypes);
    }

    public Page<Adv> advListForUser(Pageable pageable, User author){
        return advRepo.findByUser(pageable, author);
    }

    public void addAdv(Adv adv){
        advRepo.save(adv);
    }
}
