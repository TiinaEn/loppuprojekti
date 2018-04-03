package fi.academy.loppuprojekti_rest;

import fi.academy.loppuprojekti_rest.Entities.Restaurants;
import fi.academy.loppuprojekti_rest.Repositories.RestaurantRepo;
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
	CommandLineRunner initDatabase(RestaurantRepo restaurantRepo) {
		return args -> {
			Restaurants r = new Restaurants("Chinese Restaurant", "Great food, cheap and tasty", "Chinese");
			restaurantRepo.save(r);

			restaurantRepo.save(new Restaurants("PizzaPalace", "A good place for lunch", "Italian"));
			restaurantRepo.save(new Restaurants("Finnjävel", "Finnish fine dining", "Fine dining"));
		};
	}
}