package model;

public class Referral {

    private String referralID;
    private String gpID;
    private String specialistID;
    private String patientID;
    private String status;

    public Referral(String referralID, String gpID,
                    String specialistID, String patientID) {
        this.referralID = referralID;
        this.gpID = gpID;
        this.specialistID = specialistID;
        this.patientID = patientID;
        this.status = "PENDING";
    }

    public String getReferralID() {
        return referralID;
    }

    public String getGpID() {
        return gpID;
    }

    public String getSpecialistID() {
        return specialistID;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getStatus() {
        return status;
    }

    public String toFileString() {
        return referralID + "," +
                gpID + "," +
                specialistID + "," +
                patientID + "," +
                status;
    }
}
