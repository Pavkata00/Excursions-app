package web.server.app.travelagency.service;

import web.server.app.travelagency.model.Destination;

import java.util.List;
import java.util.Optional;

public interface DestinationsService {
    List<Destination> findAll();
    Optional<Destination> findById(Long id);
    Optional<Destination> save(String street, Integer number, String city, String country);
    Optional<Destination> edit(Long id, String street, Integer number, String city, String country);
    void deleteById(Long id);
}
