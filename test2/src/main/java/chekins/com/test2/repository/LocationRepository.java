package chekins.com.test2.repository;

import chekins.com.test2.model.Location;
import com.redis.om.spring.autocomplete.Suggestion;
import com.redis.om.spring.repository.RedisDocumentRepository;
import com.redis.om.spring.repository.query.autocomplete.AutoCompleteOptions;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import java.util.List;

public interface LocationRepository extends RedisDocumentRepository<Location, String>{
    List<Location> autoCompleteName(String query);
    List<Suggestion> autoCompleteName(String query, AutoCompleteOptions options);
   // Iterable<Location> findByLocationNear(Point point, Distance distance);
}
