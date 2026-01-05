package model;

public class Nurse extends User {
    private String department;

    public Nurse(String id, String name, String department, String contact) {
        super(id, name, "Nurse", contact);
        this.department = department;
    }

    public void updateTreatmentNote(String note) {
        System.out.println("Treatment updated: " + note);
    }
}
