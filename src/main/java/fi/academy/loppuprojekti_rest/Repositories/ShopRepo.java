package fi.academy.loppuprojekti_rest.Repositories;

import fi.academy.loppuprojekti_rest.Entities.Shops;
import org.springframework.data.repository.CrudRepository;

public interface ShopRepo extends CrudRepository<Shops, Integer> {
}
