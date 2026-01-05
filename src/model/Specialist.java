package model;

public class Specialist extends User {
    private String field;

    public Specialist(String id, String name, String field, String contact) {
        super(id, name, "Specialist", contact);
        this.field = field;
    }
}
