package model;

public abstract class User {
    protected String userID;
    protected String name;
    protected String role;
    protected String contact;

    public User(String userID, String name, String role, String contact) {
        this.userID = userID;
        this.name = name;
        this.role = role;
        this.contact = contact;
    }

    public String getUserID() {
        return userID;
    }

    public String viewProfile() {
        return name + " (" + role + ") - " + contact;
    }
}
