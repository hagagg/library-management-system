package com.library.management.factories;

import com.library.management.enums.UserRole;
import com.library.management.models.BorrowedBook;
import com.library.management.models.users.*;

import java.time.LocalDate;
import java.util.List;

public class UserFactory {

    public static Admin createAdmin (int id , String name , String email , Boolean isActive ){
        return new Admin (id , name , email , isActive );
    }

    public static Librarian createLibrarian ( int id , String name , String email , Boolean isActive ){
        return new Librarian (id , name , email , isActive );
    }

    public static Student createStudent (int id , String name , String email , Boolean isActive ,
                                       LocalDate memberShipDate , List<BorrowedBook> borrowedBooks , double fineBalance ) {
        return new Student (id , name , email , isActive , memberShipDate , borrowedBooks , fineBalance);
    }

    public static Teacher createTeacher (int id , String name , String email , Boolean isActive ,
                                         LocalDate memberShipDate , List<BorrowedBook> borrowedBooks , double fineBalance ) {
        return new Teacher (id , name , email , isActive , memberShipDate , borrowedBooks , fineBalance);
    }




    public static User createUser(UserRole userRole , int id , String name , String email , Boolean isActive ,
                                  LocalDate memberShipDate , List<BorrowedBook> borrowedBooks , double fineBalance ) {

        return switch (userRole) {
            case ADMIN -> createAdmin(id , name , email , isActive );
            case LIBRARIAN -> createLibrarian(id , name , email , isActive );
            case STUDENT -> createStudent(id, name, email, isActive, memberShipDate, borrowedBooks, fineBalance);
            case TEACHER -> createTeacher(id, name, email, isActive, memberShipDate, borrowedBooks, fineBalance);
        };
    }


}
