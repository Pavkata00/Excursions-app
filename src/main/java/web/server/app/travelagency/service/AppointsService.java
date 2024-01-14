package web.server.app.travelagency.service;

import web.server.app.travelagency.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointsService {
    List<Appointment> findAll();
    Optional<Appointment> findById(Long id);
    Optional<Appointment> save(String contactName, String phoneNumber, String holiday);
    Optional<Appointment> edit(Long id, String contactName, String phoneNumber, String holiday);
    void deleteById(Long id);
    List<Appointment> findAppointsByPhoneNumber(String phoneNumber);
}
