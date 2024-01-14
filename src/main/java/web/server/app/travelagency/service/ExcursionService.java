package web.server.app.travelagency.service;

import web.server.app.travelagency.model.Excursion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExcursionService {
    List<Excursion> findAll();
    List<Excursion> findExcursionByCriteria(String locationName, LocalDate startDate, Integer duration);
    Optional<Excursion> findById(Long id);
    Optional<Excursion> save(String location, String title, LocalDate startDate, Integer duration, Double price, Integer freeSlots);
    Optional<Excursion> edit(Long id, String location, String title, LocalDate startDate, Integer duration, Double price, Integer freeSlots);
    void deleteById(Long id);
}
