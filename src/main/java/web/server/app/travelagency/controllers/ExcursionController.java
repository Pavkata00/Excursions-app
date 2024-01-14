package web.server.app.travelagency.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.server.app.travelagency.dto.ExcursionTransferDTO;
import web.server.app.travelagency.model.Excursion;
import web.server.app.travelagency.service.ExcursionService;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/holidays")
public class ExcursionController {

    private final ExcursionService excursionService;

    public ExcursionController(ExcursionService excursionService) {
        this.excursionService = excursionService;
    }

    @GetMapping
    public ResponseEntity<List<Excursion>> findAll() {
        return ResponseEntity.ok(this.excursionService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Excursion> findById(@PathVariable Long id) {
        return this.excursionService.findById(id)
                .map(h -> ResponseEntity.ok().body(h))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(params = {"location", "startDate", "duration"})
    public ResponseEntity<List<Excursion>> getAllByCriteria(@RequestParam String location,
                                                            @RequestParam String startDate,
                                                            @RequestParam Integer duration) {
        List<Excursion> excursions = this.excursionService.findExcursionByCriteria(location, LocalDate.parse(startDate), duration);
        if (excursions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(excursions);
    }

    @PostMapping
    public ResponseEntity<Excursion> save(@RequestBody ExcursionTransferDTO excursionTransferDTO) {
        Excursion excursion = this.excursionService.save(excursionTransferDTO.getLocation(),
                excursionTransferDTO.getTitle(),
                excursionTransferDTO.getStartDate(),
                excursionTransferDTO.getDuration(),
                excursionTransferDTO.getPrice(),
                excursionTransferDTO.getFreeSlots()).get();
        return ResponseEntity.ok(excursion);
    }

    @PutMapping
    public ResponseEntity<Excursion> edit(@RequestBody ExcursionTransferDTO excursionTransferDTO) {
        Excursion excursion = this.excursionService.edit(excursionTransferDTO.getId(),
                excursionTransferDTO.getLocation(),
                excursionTransferDTO.getTitle(),
                excursionTransferDTO.getStartDate(),
                excursionTransferDTO.getDuration(),
                excursionTransferDTO.getPrice(),
                excursionTransferDTO.getFreeSlots()).get();
        return ResponseEntity.ok().body(excursion);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.excursionService.deleteById(id);
        if (this.excursionService.findById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
