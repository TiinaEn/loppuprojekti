package fi.academy.loppuprojekti_rest.Controllers;

import fi.academy.loppuprojekti_rest.Repositories.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Restaurantcontroller {

@Autowired
    private RestaurantRepo rr;
}
