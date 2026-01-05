package model;

public class TreatmentPlan {
    private String treatmentID;
    private String gpID;
    private String patientID;
    private String notes;
    private String date;

    public TreatmentPlan(String treatmentID, String gpID, String patientID, String notes, String date) {
        this.treatmentID = treatmentID;
        this.gpID = gpID;
        this.patientID = patientID;
        this.notes = notes;
        this.date = date;
    }
}
