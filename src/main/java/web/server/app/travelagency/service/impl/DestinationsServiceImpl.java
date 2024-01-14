package web.server.app.travelagency.service.impl;

import org.springframework.stereotype.Service;
import web.server.app.travelagency.model.Destination;
import web.server.app.travelagency.repository.DestinationsRepo;
import web.server.app.travelagency.service.DestinationsService;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationsServiceImpl implements DestinationsService {
    private final DestinationsRepo destinationsRepo;

    public DestinationsServiceImpl(DestinationsRepo destinationsRepo) {
        this.destinationsRepo = destinationsRepo;
    }

    @Override
    public List<Destination> findAll() {
        return this.destinationsRepo.findAll();
    }

    @Override
    public Optional<Destination> findById(Long id) {
        return this.destinationsRepo.findById(id);
    }

    @Override
    public Optional<Destination> save(String street, Integer number, String city, String country) {
        Destination destination = new Destination(street, number, city, country);
        destinationsRepo.save(destination);
        return Optional.of(destination);
    }

    @Override
    public Optional<Destination> edit(Long id, String street, Integer number, String city, String country) {
        Destination destination = this.destinationsRepo.findById(id).get();
        destination.setStreet(street);
        destination.setNumber(number);
        destination.setCity(city);
        destination.setCountry(country);

        this.destinationsRepo.save(destination);
        return Optional.of(destination);
    }

    @Override
    public void deleteById(Long id) {
        this.destinationsRepo.deleteById(id);
    }
}
