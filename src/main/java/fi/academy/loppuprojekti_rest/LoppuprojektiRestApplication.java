package fi.academy.loppuprojekti_rest;

import fi.academy.loppuprojekti_rest.Entities.Destination;
import fi.academy.loppuprojekti_rest.Entities.Role;
import fi.academy.loppuprojekti_rest.Entities.User;
import fi.academy.loppuprojekti_rest.Repositories.DestinationRepo;
import fi.academy.loppuprojekti_rest.Repositories.RoleRepository;
import fi.academy.loppuprojekti_rest.Repositories.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static fi.academy.loppuprojekti_rest.Entities.RoleName.ROLE_ADMIN;
import static fi.academy.loppuprojekti_rest.Entities.RoleName.ROLE_USER;

@SpringBootApplication
public class LoppuprojektiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoppuprojektiRestApplication.class, args);
	}


	@Bean
	CommandLineRunner initDatabase(DestinationRepo dr, UserRepo ur, RoleRepository rolerepo) {
		return args -> {
			if (1==1)return;

			User h = new User("Heidi", "Heidi", "heidi@heidi.fi", "basic", 1, " ");
			ur.save(h);
			Destination r = new Destination("restaurant", "Chinatown",  "China", "Beijing", "Great food, cheap and tasty", h);
			dr.save(r);
			dr.save(new Destination("restaurant", "JavaHut",  "Belgium", "Brussels", "Best coffee in town, free wifi", h));
			dr.save(new Destination("restaurant", "Ikea",  "Finland", "Vantaa", "Cheap meatballs", h));
			dr.save(new Destination("restaurant", "Rosso",  "Finland", "Helsinki", "Pizza and pasta", h));
            dr.save(new Destination("restaurant", "Farang",  "Finland", "Helsinki", "Expensive Asian meatballs", h));
            dr.save(new Destination("movies", "Tennispalatsi",  "Finland", "Helsinki", "Movies", h));
            dr.save(new Destination("hotel", "Clarion", null, 60.1598, 24.9220, "Finland", "Helsinki", "Great views over Helsinki. Comfy beds, didn't enjoy the breakfast though.",4,"www.clarion.com",h));
			/*rolerepo.save(new Role(ROLE_USER));
			rolerepo.save(new Role(ROLE_ADMIN));*/
			System.out.println("Roolit asetettu");
		};
	}
}