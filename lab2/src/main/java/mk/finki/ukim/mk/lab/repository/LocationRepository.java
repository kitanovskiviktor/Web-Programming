package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LocationRepository {

    List<Location> locations;

    public LocationRepository() {
        locations = new ArrayList<Location>();
        locations.add(new Location("imeLokacija1", "adresaLokacija1", "1000", "opisLokacija1"));
        locations.add(new Location("imeLokacija2", "adresaLokacija2", "1001", "opisLokacija2"));
        locations.add(new Location("imeLokacija3", "adresaLokacija3", "1002", "opisLokacija3"));
        locations.add(new Location("imeLokacija4", "adresaLokacija4", "1003", "opisLokacija4"));
        locations.add(new Location("imeLokacija5", "adresaLokacija5", "1004", "opisLokacija5"));
    }

    public List<Location> findAll() {
        return locations;
    }

}
