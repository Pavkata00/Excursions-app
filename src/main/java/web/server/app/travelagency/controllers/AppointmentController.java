package web.server.app.travelagency.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.server.app.travelagency.dto.FindAppointDTO;
import web.server.app.travelagency.dto.ReservationTransferObject;
import web.server.app.travelagency.model.Appointment;
import web.server.app.travelagency.service.AppointsService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AppointmentController {
    private final AppointsService appointsService;

    public AppointmentController(AppointsService appointsService) {
        this.appointsService = appointsService;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Appointment>> getAll() {
        return ResponseEntity.ok(this.appointsService.findAll());
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<Appointment> findById(@PathVariable Long id) {
        return this.appointsService.findById(id)
                .map(r -> ResponseEntity.ok().body(r))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/find-reservation")
    public ResponseEntity<List<Appointment>> findAllByPhoneNum(@RequestBody FindAppointDTO reservationDTO) {
        return ResponseEntity.ok(this.appointsService.findAppointsByPhoneNumber(reservationDTO.getPhoneNumber()));
    }

    @PostMapping("/reservations")
    public ResponseEntity<Appointment> save(@RequestBody ReservationTransferObject reservationTransferObject) {
        Appointment appointment = this.appointsService.save(reservationTransferObject.getContactName(),
                reservationTransferObject.getPhoneNumber(),
                reservationTransferObject.getHoliday()).get();
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/reservations")
    public ResponseEntity<Appointment> edit(@RequestBody ReservationTransferObject reservationTransferObject) {
        Appointment appointment = this.appointsService.edit(reservationTransferObject.getId(),
                reservationTransferObject.getContactName(),
                reservationTransferObject.getPhoneNumber(),
                reservationTransferObject.getHoliday()).get();
        return ResponseEntity.ok().body(appointment);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.appointsService.deleteById(id);
        if (this.appointsService.findById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
