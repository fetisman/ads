package org.fetisman.ads.repo;

import org.fetisman.ads.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String userName);

    User findByUsernameAndUserLastName(String userName, String userLastName);

    User findByActivationCode(String code);
}
