package web.server.app.travelagency.service.impl;

import org.springframework.stereotype.Service;
import web.server.app.travelagency.model.Excursion;
import web.server.app.travelagency.model.Appointment;
import web.server.app.travelagency.repository.ExcursionsRepo;
import web.server.app.travelagency.repository.AppointsRepo;
import web.server.app.travelagency.service.AppointsService;

import java.util.List;
import java.util.Optional;

@Service
public class AppointsServiceImpl implements AppointsService {
    private final AppointsRepo appointsRepo;
    private final ExcursionsRepo excursionsRepo;

    public AppointsServiceImpl(AppointsRepo appointsRepo, ExcursionsRepo excursionsRepo) {
        this.appointsRepo = appointsRepo;
        this.excursionsRepo = excursionsRepo;
    }

    @Override
    public List<Appointment> findAll() {
        return this.appointsRepo.findAll();
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return this.appointsRepo.findById(id);
    }

    @Override
    public Optional<Appointment> save(String contactName, String phoneNumber, String holiday) {
        Excursion existingExcursion = this.excursionsRepo.findById(Long.valueOf(holiday)).orElseThrow(RuntimeException::new);
        Appointment appointment = new Appointment(contactName, phoneNumber, existingExcursion);
        this.appointsRepo.save(appointment);
        return Optional.of(appointment);
    }

    @Override
    public Optional<Appointment> edit(Long id, String contactName, String phoneNumber, String holiday) {
        Excursion existingExcursion = this.excursionsRepo.findById(Long.valueOf(holiday)).orElseThrow(RuntimeException::new);
        Optional<Appointment> optReservation = this.appointsRepo.findById(id);
        if (optReservation.isEmpty()) {
            return Optional.empty();
        }
        Appointment appointment = optReservation.get();
        appointment.setContactName(contactName);
        appointment.setPhoneNumber(phoneNumber);
        appointment.setHoliday(existingExcursion);
        this.appointsRepo.save(appointment);
        return Optional.of(appointment);
    }

    @Override
    public void deleteById(Long id) {
        this.appointsRepo.deleteById(id);
    }

    @Override
    public List<Appointment> findAppointsByPhoneNumber(String phoneNumber) {
        return this.appointsRepo.findAllByPhoneNumber(phoneNumber);
    }
}
