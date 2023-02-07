package it.marczuk.cookbookapp;

import it.marczuk.cookbookapp.security.model.AppUser;
import it.marczuk.cookbookapp.security.repository.AppUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootApplication
public class CookBookAppApplication {

    private final AppUserRepo appUserRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CookBookAppApplication(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(CookBookAppApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public final void addAdminUser() {
        AppUser appUser = new AppUser();
        appUser.setUsername("admin");
        appUser.setPassword(passwordEncoder.encode("admin"));

        appUser.setRole("ROLE_USER");

        appUserRepo.save(appUser);
        log.info("Add user to repo");
    }
}
