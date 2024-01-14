package web.server.app.travelagency.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private LocalDate startDate;
    private Integer duration;
    private Double price;
    private Integer freeSlots;
    @ManyToOne
    private Destination destination;

    public Excursion() {
    }

    public Excursion(String title, LocalDate startDate, Integer duration, Double price, Integer freeSlots, Destination destination) {
        this.title = title;
        this.startDate = startDate;
        this.duration = duration;
        this.price = price;
        this.freeSlots = freeSlots;
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getFreeSlots() {
        return freeSlots;
    }

    public void setFreeSlots(Integer freeSlots) {
        this.freeSlots = freeSlots;
    }

    public Destination getLocation() {
        return destination;
    }

    public void setLocation(Destination destination) {
        this.destination = destination;
    }
}
