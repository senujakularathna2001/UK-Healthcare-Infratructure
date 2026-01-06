package model;

public class Patient {

    private String patientId;
    private String nhsNumber;
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private String postcode;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String registrationDate;
    private String gpSurgeryId;

    public Patient(String patientId, String nhsNumber, String firstName, String lastName, String dob, String gender, String phone, String email, String address, String postcode, String emergencyContactName, String emergencyContactNumber, String registrationDate, String gpSurgeryId) {
        this.patientId = patientId;
        this.nhsNumber = nhsNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNumber = emergencyContactNumber;
        this.registrationDate = registrationDate;
        this.gpSurgeryId = gpSurgeryId;
    }

    public String getPatientId()
    {
        return patientId;
    }

    public String getNhsNumber()
    {
        return nhsNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getGpSurgeryId() {
        return gpSurgeryId;
    }

    public String toCsv() {
        return String.join(",",
                patientId, nhsNumber, firstName, lastName, dob, gender,
                phone, email, address, postcode,
                emergencyContactName, emergencyContactNumber,
                registrationDate, gpSurgeryId);
    }
}
