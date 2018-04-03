package fi.academy.loppuprojekti_rest.Repositories;

import fi.academy.loppuprojekti_rest.Entities.Restaurants;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepo extends CrudRepository<Restaurants, Integer> {
}
