package pl.markowski.tournament.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.markowski.tournament.model.AppUser;
import pl.markowski.tournament.repo.AppUserRepo;

@Configuration
public class Start {

    @Autowired
    public Start(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {

//        AppUser admin = new AppUser();
//        admin.setUsername("admin");
//        admin.setPassword(passwordEncoder.encode("admin"));
//        admin.setRole("ROLE_ADMIN");
//        appUserRepo.save(admin);
    }
}