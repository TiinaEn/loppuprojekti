package fi.academy.loppuprojekti_rest.Repositories;

import fi.academy.loppuprojekti_rest.Entities.Destination;
import org.springframework.data.repository.CrudRepository;

public interface DestinationRepo extends CrudRepository <Destination, Integer> {
}
