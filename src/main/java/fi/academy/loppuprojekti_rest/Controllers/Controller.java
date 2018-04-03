package fi.academy.loppuprojekti_rest.Controllers;
import fi.academy.loppuprojekti_rest.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

/*@GetMapping ("/restaurants")
    public */





}
