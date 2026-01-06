package model;

public class Appointment {

    private String appointmentID;
    private String patientID;
    private String clinicianID;
    private String facilityID;
    private String appointmentDate;
    private String appointmentTime;
    private int durationMinutes;
    private String type;
    private String status;

    public Appointment(String appointmentID, String patientID, String clinicianID, String facilityID, String appointmentDate, String appointmentTime, int durationMinutes, String type, String status) {

        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.clinicianID = clinicianID;
        this.facilityID = facilityID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.durationMinutes = durationMinutes;
        this.type = type;
        this.status = status;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getClinicianID() {
        return clinicianID;
    }

    public String getFacilityID() {
        return facilityID;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String toCsv() {
        return String.join(",", appointmentID, patientID, clinicianID, facilityID, appointmentDate, appointmentTime, String.valueOf(durationMinutes), type, status);
    }
}
