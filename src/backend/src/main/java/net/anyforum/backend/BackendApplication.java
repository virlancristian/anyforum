package net.anyforum.backend;

import net.anyforum.backend.repos.role.RoleDbRepo;
import net.anyforum.backend.repos.user.UserDataDbRepo;
import net.anyforum.backend.services.user.UserDbService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserDataDbRepo userDataDbRepo) {
		return args -> {
			System.out.println(userDataDbRepo.getUserByID("fka;k;adsf"));
		};
	}
}
