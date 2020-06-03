package org.fetisman.ads.service;

import org.fetisman.ads.domain.Adv;
import org.fetisman.ads.domain.AdvType;
import org.fetisman.ads.domain.User;
import org.fetisman.ads.repo.AdvRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvServiceTest {

    @MockBean
    private AdvRepo advRepo;

    @Autowired
    private AdvService advService;

    @Test
    public void addAdv() {
        Adv adv = new Adv();

        advService.addAdv(adv);

        Mockito.verify(advRepo, Mockito.times(1)).save(adv);
    }
}