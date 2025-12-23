package model.people;

public class Specialist extends Clinician {

    private String specialty;

    public Specialist(String id, String name, String email, String specialty) {
        super(id, name, email, "Specialist");
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }
}
