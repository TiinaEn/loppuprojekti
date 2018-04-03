package fi.academy.loppuprojekti_rest;

import fi.academy.loppuprojekti_rest.Entities.Destination;
import fi.academy.loppuprojekti_rest.Repositories.DestinationRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoppuprojektiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoppuprojektiRestApplication.class, args);
	}


	@Bean
	CommandLineRunner initDatabase(DestinationRepo dr) {
		return args -> {
			Destination r = new Destination("restaurant", "Chinatown",  "China", "Peking", "Great food, cheap and tasty");
			dr.save(r);
			dr.save(new Destination("restaurant", "Chinatown",  "China", "Peking", "Great food, cheap and tasty"));
			dr.save(new Destination("restaurant", "Chinatown",  "China", "Peking", "Great food, cheap and tasty"));
		};
	}
}