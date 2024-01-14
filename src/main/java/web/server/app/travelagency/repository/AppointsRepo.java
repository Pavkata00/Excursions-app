package web.server.app.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.server.app.travelagency.model.Appointment;

import java.util.List;

@Repository
public interface AppointsRepo extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByPhoneNumber(String phoneNumber);
}
