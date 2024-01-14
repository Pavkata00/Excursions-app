package web.server.app.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.server.app.travelagency.model.Destination;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinationsRepo extends JpaRepository<Destination, Long> {
    Optional<Destination> findLocationByCity(String city);
    List<Destination> findLocationByCountry(String country);
}
