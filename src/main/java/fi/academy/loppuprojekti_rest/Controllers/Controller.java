package fi.academy.loppuprojekti_rest.Controllers;

import fi.academy.loppuprojekti_rest.Entities.Destination;
import fi.academy.loppuprojekti_rest.Entities.User;
import fi.academy.loppuprojekti_rest.Exception.AppException;
import fi.academy.loppuprojekti_rest.Repositories.*;
import fi.academy.loppuprojekti_rest.Security.CurrentUser;
import fi.academy.loppuprojekti_rest.Security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/travelapp")
public class Controller {

    @Autowired
    private DestinationRepo destinationRepo;
    @Autowired
    private UserRepo userRepo;

    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/destinations")
    public Iterable<Destination> findDestinations(@CurrentUser UserPrincipal userPrincipal) {
       // Iterable<Destination> iteDestination = destinationRepo.findAllByUser(authentication.getUser().getUsername);
        Iterable<Destination> iteDestination = destinationRepo.findByCountry();
        return iteDestination;
    /*    Optional <User> user = userRepo.findByUsername(userPrincipal.getName());
        Iterable<Destination> iteDestination = destinationRepo.findAllByUser(UserPrincipal);
        return iteDestination;*/

    }

    @GetMapping ("/destinations/{id}")
    public ResponseEntity<Destination> findOneDestination(@PathVariable(name = "id") Integer id) {
        Optional<Destination> optDest = destinationRepo.findById(id);
        if (!optDest.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optDest.get());
    }

    @PostMapping("/destinations/modify/{id}")
    public ResponseEntity<?> modifyDestination (@PathVariable(name = "id") Integer id, @RequestBody Destination destination) {
        destination.setId(id);
        destinationRepo.save(destination);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/destinations/delete/{id}")
    public ResponseEntity<?> removeDestination(@PathVariable (name = "id") Integer id) {
        if (!destinationRepo.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        destinationRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDestination(@CurrentUser UserPrincipal userPrincipal, @RequestBody Destination destination) {
        Optional<User> u = userRepo.findByUsername(userPrincipal.getUsername());
        destination.setUser(u.get());
        Destination saved = destinationRepo.save(destination);
        String  address = "http://localhost:8080/travelapp/destinations/"+saved.getId();
        return ResponseEntity.created(URI.create(address)).build();
    }

    @GetMapping("/find/{searchword}")
    public ResponseEntity<?> filterDestinations( @CurrentUser UserPrincipal userPrincipal, @PathVariable(name = "searchword") String searchword) {
        if (userPrincipal == null) throw new AppException("User not registered");
        Optional<User> u = userRepo.findByUsername(userPrincipal.getUsername());
        User user;
        if (u.isPresent())
            user = u.get();
        else
            throw new AppException("User not registered");
        if (searchword == null)
            return ResponseEntity.ok(destinationRepo.findAllByUser(user));

        return ResponseEntity.ok(destinationRepo.findBySearchWord(searchword, user));
    }

}





