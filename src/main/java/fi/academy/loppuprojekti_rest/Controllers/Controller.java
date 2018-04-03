package fi.academy.loppuprojekti_rest.Controllers;
import fi.academy.loppuprojekti_rest.Entities.Restaurants;
import fi.academy.loppuprojekti_rest.Repositories.*;
import jdk.nashorn.internal.runtime.arrays.IteratorAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/travelapp")
public class Controller {

@Autowired
    private RestaurantRepo rr;
@Autowired
    private HotelRepo hr;
@Autowired
    private ShopRepo sr;
@Autowired
    private SightRepo sir;
@Autowired
    private OtherRepo or;

@GetMapping ("/restaurants")
    public Iterable<Restaurants> haeRavintolat() {
        Iterable<Restaurants> iteRestaurants = rr.findAll();
        return iteRestaurants;
    }

@PostMapping ("/createRestaurant")
    public ResponseEntity<?> createRestaurant (@RequestBody Restaurants restaurant) {
    Restaurants saved = rr.save(restaurant);
    String  address = "http://localhost:8080/createRestaurant/"+saved.getId();
    return ResponseEntity.created(URI.create(address)).build();
}






}
