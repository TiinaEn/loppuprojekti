package fi.academy.loppuprojekti_rest.Repositories;

import fi.academy.loppuprojekti_rest.Entities.Destination;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DestinationRepo extends CrudRepository <Destination, Integer> {
    Optional<Destination> findByName(String name);
}
