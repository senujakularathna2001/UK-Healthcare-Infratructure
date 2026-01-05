package model;

public class Patient extends User {
    private String dob;
    private String profile;

    public Patient(String id, String name, String dob, String contact) {
        super(id, name, "Patient", contact);
        this.dob = dob;
    }

    public void registerProfile(String profile) {
        this.profile = profile;
    }

    public String viewRecords() {
        return "Patient Profile: " + profile;
    }
}
