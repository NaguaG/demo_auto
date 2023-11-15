package chekins.com.test2.service;

import chekins.com.test2.model.Location;
import chekins.com.test2.repository.LocationRepository;
import com.redis.om.spring.autocomplete.Suggestion;
import com.redis.om.spring.repository.query.autocomplete.AutoCompleteOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService{
    @Autowired
    LocationRepository locationRepository;
    @Override
    public List<Suggestion> autocomplete(String query) {
        return locationRepository
                .autoCompleteName(query, AutoCompleteOptions.get().withPayload());
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }


}
