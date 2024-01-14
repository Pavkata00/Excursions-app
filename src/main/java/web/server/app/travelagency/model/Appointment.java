package web.server.app.travelagency.model;

import javax.persistence.*;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String contactName;
    private String phoneNumber;
    @ManyToOne
    private Excursion excursion;

    public Appointment() {
    }

    public Appointment(String contactName, String phoneNumber, Excursion excursion) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.excursion = excursion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Excursion getHoliday() {
        return excursion;
    }

    public void setHoliday(Excursion excursion) {
        this.excursion = excursion;
    }
}
