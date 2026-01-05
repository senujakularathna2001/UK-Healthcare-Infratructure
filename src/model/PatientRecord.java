package model;

public class PatientRecord {
    private int recordID;
    private int patientID;
    private String details;

    public PatientRecord(int recordID, int patientID, String details) {
        this.recordID = recordID;
        this.patientID = patientID;
        this.details = details;
    }

    public void update(String newDetails) {
        this.details = newDetails;
    }
}
