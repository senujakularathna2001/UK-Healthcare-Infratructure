package model;

public class GP extends User {
    private String specialization;

    public GP(String id, String name, String specialization, String contact) {
        super(id, name, "GP", contact);
        this.specialization = specialization;
    }

    public Prescription createPrescription(String pid, String patientID, String medication, float dosage) {
        return new Prescription(pid, medication, dosage, userID, patientID);
    }
}
