package org.example.advs.service;

import org.example.advs.domain.Adv;
import org.example.advs.domain.AdvType;
import org.example.advs.domain.User;
import org.example.advs.repo.AdvRepo;
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
}
