package web.server.app.travelagency.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.server.app.travelagency.dto.DestinationTransferObject;
import web.server.app.travelagency.model.Destination;
import web.server.app.travelagency.service.DestinationsService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/locations")
public class DestinationController {
    private final DestinationsService destinationsService;

    public DestinationController(DestinationsService destinationsService) {
        this.destinationsService = destinationsService;
    }

    @GetMapping
    public ResponseEntity<List<Destination>> findAll() {
        return ResponseEntity.ok(this.destinationsService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Destination> findById(@PathVariable Long id) {
        return this.destinationsService.findById(id)
                .map(l -> ResponseEntity.ok().body(l))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Destination> save(@RequestBody DestinationTransferObject destinationTransferObject) {
        Destination destination = this.destinationsService.save(destinationTransferObject.getStreet(),
                destinationTransferObject.getNumber(),
                destinationTransferObject.getCity(),
                destinationTransferObject.getCountry()).get();
        return ResponseEntity.ok(destination);
    }

    @PutMapping
    public ResponseEntity<Destination> edit(@RequestBody DestinationTransferObject destinationTransferObject) {
        Destination destination = this.destinationsService.edit(destinationTransferObject.getId(),
                destinationTransferObject.getStreet(),
                destinationTransferObject.getNumber(),
                destinationTransferObject.getCity(),
                destinationTransferObject.getCountry()).get();
        return ResponseEntity.ok().body(destination);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.destinationsService.deleteById(id);
        if (this.destinationsService.findById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
