package com.library.management.models.users;

public class Admin extends User{

    public Admin(int id, String name, String email, Boolean isActive) {
        super(id, name, email, isActive);
    }

    @Override
    public String toString() {
        return "Admin{} " + super.toString();
    }
}
