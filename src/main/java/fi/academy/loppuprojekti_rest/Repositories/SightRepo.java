package fi.academy.loppuprojekti_rest.Repositories;

import fi.academy.loppuprojekti_rest.Entities.Sights;
import org.springframework.data.repository.CrudRepository;

public interface SightRepo extends CrudRepository<Sights, Integer> {
}
