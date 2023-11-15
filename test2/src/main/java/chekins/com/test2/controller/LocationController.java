package chekins.com.test2.controller;

import chekins.com.test2.model.Location;
import chekins.com.test2.repository.LocationRepository;
import chekins.com.test2.service.LocationService;
import com.redis.om.spring.autocomplete.Suggestion;
import com.redis.om.spring.repository.query.autocomplete.AutoCompleteOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;

@CrossOrigin( //
        methods = { POST, GET, OPTIONS, DELETE, PATCH }, //
        maxAge = 3600, //
        allowedHeaders = { //
                "x-requested-with", "origin", "content-type", "accept", "accept-patch" //
        }, //
        origins = "*" //
)

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {
    @Autowired
    LocationService locationService;
    @GetMapping("/list")
    Iterable<Location> getAll() {
        return locationService.findAll();
    }
    @GetMapping("/search/{q}")
    public List<Suggestion> query(@PathVariable("q") String query) {
        List<Suggestion> suggestions = locationService.autocomplete(query);
        return suggestions;
    }

}
