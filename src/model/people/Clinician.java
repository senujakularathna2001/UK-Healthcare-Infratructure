package model.people;

public abstract class Clinician extends Staff {

    public Clinician(String id, String name, String email, String role) {
        super(id, name, email, role);
    }
}
