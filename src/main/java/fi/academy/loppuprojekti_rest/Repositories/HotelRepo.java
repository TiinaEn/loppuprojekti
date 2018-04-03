package fi.academy.loppuprojekti_rest.Repositories;

import fi.academy.loppuprojekti_rest.Entities.Hotels;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepo extends CrudRepository <Hotels, Integer> {
}
