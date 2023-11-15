package chekins.com.test2.model;

import com.redis.om.spring.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

@Data
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
@Document
public class Location {
    @Id
    private String id;
    @NonNull
    @AutoComplete
  //  @Searchable
    private String name;
    @NonNull
    @AutoCompletePayload("name")
    private String fullName;
    @NonNull
    @AutoCompletePayload("name")
    private String type;
    @NonNull
    private String state;
    @NonNull
    private String country;
    @NonNull
    private String hierarchyPath;
    @NonNull
    @AutoCompletePayload("name")
    @GeoIndexed
    private Point loc;

}