package com.ecore.challenge;

import com.ecore.challenge.domain.Role;
import com.ecore.challenge.repository.RoleRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static com.ecore.challenge.config.Helper.convertJsonArrayToRole;

@SpringBootApplication
public class ChallengeCodeEcoreApplication implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Value("${roles}")
    private String roles;

    public static void main(final String[] args) {
        SpringApplication.run(ChallengeCodeEcoreApplication.class, args);
    }

    @Override
    public void run(final String... args) throws JSONException {
        final List<Role> defaultRoles = convertJsonArrayToRole(roles);
        roleRepository.saveAll(defaultRoles);

    }
}
