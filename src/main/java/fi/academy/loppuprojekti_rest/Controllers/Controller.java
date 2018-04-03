package fi.academy.loppuprojekti_rest.Controllers;
import fi.academy.loppuprojekti_rest.Entities.Destination;
import fi.academy.loppuprojekti_rest.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/travelapp")
public class Controller {

@Autowired
    private DestinationRepo dr;


@GetMapping ("/destinations")
    public Iterable<Destination> findDestinations() {
        Iterable<Destination> iteDestination = dr.findAll();
        return iteDestination;
    }

@PostMapping ("/create")
    public ResponseEntity<?> createDestination (@RequestBody Destination destination) {
    Destination saved = dr.save(destination);
    String  address = "http://localhost:8080/create/"+saved.getId();
    return ResponseEntity.created(URI.create(address)).build();
}






}
