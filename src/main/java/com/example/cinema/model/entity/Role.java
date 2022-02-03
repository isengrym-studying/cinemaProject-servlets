package com.example.cinema.model.entity;

/**
 * Enum represents all available roles of users.
 *
 */
public enum Role {
    USER {
        {
            this.role = "User";
        }
    },
    ADMIN{
        {
            this.role = "Admin";
        }
    };

    String role;
    public String getString() {
        return role;
    }
}
