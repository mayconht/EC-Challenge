package com.ecore.challenge;

import com.ecore.challenge.domain.Role;
import com.ecore.challenge.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ChallengeCodeEcoreApplication  implements CommandLineRunner {

	public static void main(final String[] args) {
		SpringApplication.run(ChallengeCodeEcoreApplication.class, args);
	}


	@Autowired
	RoleRepository roleRepository;

	@Override
	public void run(final String... args) {
		final Role role1 = new Role("6ec4c0f7-ab65-49ed-9e78-90752b5d2df8", "Developer", "Developer Role");
		final Role role2 = new Role("0bacfe69-9de5-415d-9293-a7c49fa5d861", "Product Owner", "Product Owner Role");
		final Role role3 = new Role("218b6b45-a792-49e6-a844-1a2b34eb7827", "Tester", "Tester Role");

		role1.getUser().addAll(Arrays.asList("fd282131-d8aa-4819-b0c8-d9e0bfb1b75c","fa1529de-5f20-49a7-ad25-a494008dd322","aa569071-6ade-4ff6-b567-6466fcbae74a"));
		role2.getUser().add("1b140966-5a01-49da-872e-71a769f98941");
		role3.getUser().addAll(Arrays.asList("fddcde65-70b2-49f9-8b4d-5126adc345c1", "fecaeebc-a7cf-4f33-8114-328566d605f6"));

		roleRepository.saveAll(Arrays.asList(role1, role2, role3));
	}
}
