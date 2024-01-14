package web.server.app.travelagency.service.impl;

import org.springframework.stereotype.Service;
import web.server.app.travelagency.model.Excursion;
import web.server.app.travelagency.model.Destination;
import web.server.app.travelagency.repository.ExcursionsRepo;
import web.server.app.travelagency.repository.DestinationsRepo;
import web.server.app.travelagency.service.ExcursionService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ExcursionServiceImpl implements ExcursionService {
    private final ExcursionsRepo excursionsRepo;
    private final DestinationsRepo destinationsRepo;

    public ExcursionServiceImpl(ExcursionsRepo excursionsRepo, DestinationsRepo destinationsRepo) {
        this.excursionsRepo = excursionsRepo;
        this.destinationsRepo = destinationsRepo;
    }

    @Override
    public List<Excursion> findAll() {
        return this.excursionsRepo.findAll();
    }

    @Override
    public List<Excursion> findExcursionByCriteria(String locationName, LocalDate startDate, Integer duration) {
        Optional<Destination> locationByCity = this.destinationsRepo.findLocationByCity(locationName);
        if (locationByCity.isEmpty()) {
            List<Destination> destinationByCountry = this.destinationsRepo.findLocationByCountry(locationName);
            if (destinationByCountry.isEmpty()) {
                return Collections.emptyList();
            } else {
                for (Destination current : destinationByCountry) {
                    List<Excursion> excursions = this.excursionsRepo.findHolidayByLocationAndStartDateAndDuration(current,
                            startDate, duration);
                    if (!excursions.isEmpty()) {
                        return excursions;
                    }
                }
                return Collections.emptyList();
            }
        } else {
            return this.excursionsRepo.findHolidayByLocationAndStartDateAndDuration(locationByCity.get(),
                    startDate, duration);
        }
    }

    @Override
    public Optional<Excursion> findById(Long id) {
        return this.excursionsRepo.findById(id);
    }

    @Override
    public Optional<Excursion> save(String location, String title, LocalDate startDate, Integer duration, Double price, Integer freeSlots) {
        Destination existingDestination = destinationsRepo.findById(Long.valueOf(location)).orElseThrow(RuntimeException::new);
        Excursion excursion = new Excursion(title, startDate, duration, price, freeSlots, existingDestination);
        excursionsRepo.save(excursion);
        return Optional.of(excursion);
    }

    @Override
    public Optional<Excursion> edit(Long id, String location, String title, LocalDate startDate, Integer duration, Double price, Integer freeSlots) {
        Destination existingDestination = destinationsRepo.findById(Long.valueOf(location)).orElseThrow(RuntimeException::new);
        Excursion excursion = this.excursionsRepo.findById(id).get();
        excursion.setLocation(existingDestination);
        excursion.setTitle(title);
        excursion.setStartDate(startDate);
        excursion.setDuration(duration);
        excursion.setPrice(price);
        excursion.setFreeSlots(freeSlots);

        this.excursionsRepo.save(excursion);
        return Optional.of(excursion);
    }

    @Override
    public void deleteById(Long id) {
        this.excursionsRepo.deleteById(id);
    }
}
