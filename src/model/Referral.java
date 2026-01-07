package model;

public class Referral {

    private String referralId;
    private String patientId;
    private String gpId;
    private String specialistId;
    private String facilityId;
    private String reason;
    private String gpNotes;
    private String specialistNotes;
    private String status;
    private String createdDate;

    public Referral(String referralId, String patientId, String gpId, String specialistId, String facilityId, String reason, String gpNotes, String specialistNotes, String status, String createdDate) {

        this.referralId = referralId;
        this.patientId = patientId;
        this.gpId = gpId;
        this.specialistId = specialistId;
        this.facilityId = facilityId;
        this.reason = reason;
        this.gpNotes = gpNotes;
        this.specialistNotes = specialistNotes;
        this.status = status;
        this.createdDate = createdDate;
    }

    public String getReferralId() {
        return referralId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getGpId() {
        return gpId;
    }

    public String getSpecialistId() {
        return specialistId;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public String getReason() {
        return reason;
    }

    public String getGpNotes() {
        return gpNotes;
    }

    public String getSpecialistNotes() {
        return specialistNotes;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSpecialistNotes(String specialistNotes) {
        this.specialistNotes = specialistNotes;
    }

    public String toCsv() {
        return String.join(",", referralId, patientId, gpId, specialistId, facilityId, reason, gpNotes, specialistNotes, status, createdDate);
    }
}
