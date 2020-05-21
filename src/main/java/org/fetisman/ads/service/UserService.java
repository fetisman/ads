package org.fetisman.ads.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.fetisman.ads.domain.Role;
import org.fetisman.ads.domain.User;
import org.fetisman.ads.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepo userRepo;

    private final MailSender mailSender;

    public UserService(UserRepo userRepo, MailSender mailSender) {
        this.userRepo = userRepo;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean addUser(User user) {
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if (userFromDB!= null){
            return false;
        }

        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        sendMessage(user);

        userRepo.save(user);

        return true;
    }

    public void saveUser(User user, Map<String, String> form) {

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Ads. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if(user == null) {
            return false;
        }
        user.setActive(true);
        user.setActivationCode(null);
        userRepo.save(user);
        return true;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public boolean updatePswd(User user, String password, String password0) {
        if (!passwordEncoder.matches(password0, user.getPassword())){
            return false;
        }
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);
        return true;
    }

    public void updateUserProfile(User user, String newFirstName, String newLastName) {

        user.setUserFirstName(newFirstName);
        user.setUserLastName(newLastName);
        userRepo.save(user);
    }
}
