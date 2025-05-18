package com.library.management.models.users;

import com.library.management.models.Book;

import java.util.Map;

public class Librarian extends User{

    public Librarian(int id, String name, String email, Boolean isActive) {
        super(id, name, email, isActive);
    }

    @Override
    public String toString() {
        return "Librarian{} " + super.toString();
    }
}
