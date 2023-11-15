package chekins.com.test2.service;

import chekins.com.test2.model.Location;
import chekins.com.test2.repository.LocationRepository;
import com.redis.om.spring.autocomplete.Suggestion;
import com.redis.om.spring.repository.query.autocomplete.AutoCompleteOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface LocationService {
    List<Suggestion> autocomplete(String query);

    List<Location> findAll();
}

