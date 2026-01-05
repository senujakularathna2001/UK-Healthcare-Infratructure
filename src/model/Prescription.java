package model;

public class Prescription {
    private String prescriptionID;
    private String medication;
    private float dosage;
    private String gpID;
    private String patientID;

    public Prescription(String prescriptionID, String medication, float dosage, String gpID, String patientID) {
        this.prescriptionID = prescriptionID;
        this.medication = medication;
        this.dosage = dosage;
        this.gpID = gpID;
        this.patientID = patientID;
    }

    public boolean checkDrugInteractions() {
        if (medication.equalsIgnoreCase("Aspirin")) {
            System.out.println("Warning: Aspirin may interact with blood thinners.");
            return true;
        }
        return false;
    }


    public String toFileString() {
        return prescriptionID + "," + medication + "," + dosage + "," + gpID + "," + patientID;
    }
}
