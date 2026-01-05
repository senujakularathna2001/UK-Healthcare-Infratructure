package model;

public class AdminStaff extends User {

    public AdminStaff(String id, String name, String contact) {
        super(id, name, "Admin", contact);
    }

    public void generateReport() {
        System.out.println("Report generated.");
    }
}
