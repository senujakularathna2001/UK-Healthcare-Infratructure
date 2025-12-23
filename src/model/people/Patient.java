package model.people;

public class Patient extends Person {
    private String dateOfBirth;

    public Patient(String id, String name, String email, String dob) {
        super(id, name, email);
        this.dateOfBirth = dob;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
