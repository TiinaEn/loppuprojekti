package fi.academy.loppuprojekti_rest.Repositories;

import fi.academy.loppuprojekti_rest.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, String> {
    User findByUsername(String username);
}
