package fi.academy.loppuprojekti_rest;

import fi.academy.loppuprojekti_rest.Entities.Destination;
import fi.academy.loppuprojekti_rest.Entities.User;
import fi.academy.loppuprojekti_rest.Repositories.DestinationRepo;
import fi.academy.loppuprojekti_rest.Repositories.UserRepo;
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
	CommandLineRunner initDatabase(DestinationRepo dr, UserRepo ur) {
		return args -> {
			User t = new User("Tiina", "Tiina", "tiina@tiina.fi", "basic", 1, " ");
			ur.save(t);
			User h = new User("Heidi", "Heidi", "heidi@heidi.fi", "basic", 1, " ");
			ur.save(h);
			Destination r = new Destination("restaurant", "Chinatown",  "China", "Beijing", "Great food, cheap and tasty", t);
			dr.save(r);
			dr.save(new Destination("restaurant", "JavaHut",  "Belgium", "Brussels", "Best coffee in town, free wifi", t));
			dr.save(new Destination("restaurant", "Ikea",  "Finland", "Vantaa", "Cheap meatballs", h));
		};
	}
}