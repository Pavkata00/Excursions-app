package web.server.app.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.server.app.travelagency.model.Destination;
import web.server.app.travelagency.model.Excursion;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExcursionsRepo extends JpaRepository<Excursion, Long> {
    List<Excursion> findHolidayByLocationAndStartDateAndDuration(Destination destination, LocalDate startDate, Integer duration);
}
