package fi.academy.loppuprojekti_rest.Configurations;

import fi.academy.loppuprojekti_rest.Entities.User;

public interface UserService {
    public User findUserByUsername(String name);
    public void saveUser(User user);
}
