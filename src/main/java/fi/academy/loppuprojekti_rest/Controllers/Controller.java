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

@GetMapping("/find") //hakusanalla ei löydy mitään -toiminto puuttuu vielä
    public ResponseEntity<?> filterDestinations (@RequestParam(name="n", required = false) String searchword) {
        if(searchword == null)
            return ResponseEntity.ok(destinationRepo.findAll());
        return ResponseEntity.ok(destinationRepo.findBySearchWord(searchword));
}
@PutMapping("/modify") //leenalla kesken
    public ResponseEntity<?> modifyDestination (){
    return null;
}


}





