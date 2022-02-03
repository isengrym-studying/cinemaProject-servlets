package com.example.cinema.model.entity;

/**
 * Class represents 'user' entity.
 *
 */
public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private byte[] password;
    private byte[] salt;
    private String role;

    public User(int id, String name, String surname, String email, byte[] password, byte[] salt, String role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.role = role;
    }

    public User(String name, String surname, String email, byte[] password, byte[] salt, String role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.role = role;
    }

    public User(User user) {
        this.id = user.id;
        this.name = user.name;
        this.surname = user.surname;
        this.email = user.email;
        this.password = user.password;
        this.role = user.role;
    }

    public User() { }


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    @Override
    public int hashCode() {
        return 31 * (email.hashCode() + password.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "User: " + this.email + " " + this.role;
    }
}