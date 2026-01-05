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

    public Appointment(String appointmentID,
                       String patientID,
                       String clinicianID,
                       String facilityID,
                       String appointmentDate,
                       String appointmentTime,
                       int durationMinutes,
                       String type,
                       String status) {

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

    public String toFileString() {
        return appointmentID + "," +
                patientID + "," +
                clinicianID + "," +
                facilityID + "," +
                appointmentDate + "," +
                appointmentTime + "," +
                durationMinutes + "," +
                type + "," +
                status;
    }
}
