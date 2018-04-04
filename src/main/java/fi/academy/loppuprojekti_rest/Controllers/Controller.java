package fi.academy.loppuprojekti_rest.Controllers;
import fi.academy.loppuprojekti_rest.Entities.Destination;
import fi.academy.loppuprojekti_rest.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/travelapp")
public class Controller {

    @Autowired
    private DestinationRepo destinationRepo;
    

@GetMapping ("/destinations")
    public Iterable<Destination> findDestinations() {
        Iterable<Destination> iteDestination = destinationRepo.findAll();
        return iteDestination;
    }
@PostMapping ("/create")
    public ResponseEntity<?> createDestination (@RequestBody Destination destination) {
    Destination saved = destinationRepo.save(destination);
    String  address = "http://localhost:8080/create/"+saved.getId();
    return ResponseEntity.created(URI.create(address)).build();
}
@DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeDestination(@PathVariable Integer id, @RequestBody Destination destination) {
    destination.setId(id);
    destinationRepo.delete(destination);
    return ResponseEntity.noContent().build();
}
@GetMapping ("/find/{name}")
    public ResponseEntity<Destination> findByName(@PathVariable("name") String name) {
    Optional<Destination> optDest = destinationRepo.findByName(name);
    if (optDest.isPresent())
        return ResponseEntity.ok(optDest.get());
    return ResponseEntity.notFound().build();

}






}
