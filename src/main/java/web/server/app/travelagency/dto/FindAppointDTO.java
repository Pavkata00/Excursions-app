package web.server.app.travelagency.dto;

public class FindAppointDTO {
    private String phoneNumber;

    public FindAppointDTO() {
    }

    public FindAppointDTO(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
