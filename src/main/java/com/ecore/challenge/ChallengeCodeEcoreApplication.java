package com.ecore.challenge;

import com.ecore.challenge.config.Helper;
import com.ecore.challenge.domain.Role;
import com.ecore.challenge.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.util.List;
import java.util.stream.Collectors;

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
        final List<Role> defaultRoles = Helper.convertJsonToList(new JSONArray(roles)).stream().map(jsonObject -> {
            try {
                return new Role(jsonObject.getString("id"),
                        jsonObject.getString("name"),
                        jsonObject.getString("description"));
            } catch (final JSONException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        roleRepository.saveAll(defaultRoles);

    }
}
