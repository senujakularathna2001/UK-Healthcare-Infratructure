package model.people;

public abstract class Staff extends Person {
    protected String role;

    public Staff(String id, String name, String email, String role) {
        super(id, name, email);
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
