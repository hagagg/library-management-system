package com.library.management.models.users;

import com.library.management.models.BorrowedBook;

import java.time.LocalDate;
import java.util.List;

public class Student extends Member {

    public Student(int id, String name, String email, Boolean isActive, LocalDate memberShipDate, List<BorrowedBook> borrowedBooks, double fineBalance) {
        super(id, name, email, isActive, 3 , memberShipDate, borrowedBooks, fineBalance);
    }

    @Override
    public String toString() {
        return "Student{} " + super.toString();
    }
}
