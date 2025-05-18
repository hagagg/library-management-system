package com.library.management.main;


import com.library.management.enums.UserRole;
import com.library.management.models.Book;
import com.library.management.models.BorrowedBook;
import com.library.management.models.users.*;
import com.library.management.services.BookService;
import com.library.management.services.BorrowService;
import com.library.management.services.FineService;
import com.library.management.services.users.AdminService;
import com.library.management.services.users.LibrarianService;
import com.library.management.services.users.MemberService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map <Integer , Admin>  adminMap = new HashMap<>();
        Map <Integer , User>  userMap =new HashMap<>();
        Map <Integer , Member> memberMap =new HashMap<>();
        Map <Integer , Librarian> librarianMap =new HashMap<>();
        Map <Integer , Book> bookMap =new HashMap<>();
        Map <Integer , BorrowedBook> borrowedBookMap =new HashMap<>();

        MemberService memberService = new MemberService(memberMap);
        BookService bookService = new BookService(bookMap);
        LibrarianService librarianService = new LibrarianService(librarianMap , bookService  ,   memberService);
        FineService fineService = new FineService(memberService);
        AdminService adminService = new AdminService(adminMap ,userMap ,memberMap , librarianMap , memberService , librarianService);
        BorrowService borrowService = new BorrowService(borrowedBookMap , memberService ,adminService , fineService , bookService );



        adminService.addUser(UserRole.ADMIN , 17 , "Ahmed Hagag" ,  "hagag@gmail" ,  true);

        adminService.addUser(UserRole.STUDENT , 10 , "Ahmed Alhalwagy" , "halwag@gmail" , true ,
                             LocalDate.of(2025 , 2 , 25) , new ArrayList<>() , 0);
        adminService.addUser(UserRole.STUDENT , 11 , "Habiba Araby" , "habiba@gmail" , false ,
                LocalDate.of(2024 , 11 , 20) , new ArrayList<>() , 0);

        adminService.addUser(UserRole.TEACHER , 12 , "Farah Ayman" , "farah@gmail" , true ,
                             LocalDate.of(2025 , 3 , 17) , new ArrayList<>() , 0);

        adminService.addUser(UserRole.LIBRARIAN , 51 , "Ahmed Elsawy" , "elsawy@gmail" , true );

        System.out.println("All USERS : ");
        for (User user : userMap.values()) {
            System.out.println(user);
        }

        // ADDING NEW BOOKS
        Book book1 = new Book("Head first java" , "Hansi Flick" , 111 , "Programming" , 100 , 100);
        Book book2 = new Book("Design Pattern" , "Pedri" , 112 , "Programming" , 100 , 100);
        Book book3 = new Book("Movie Directing" , " Chris Nolan" , 211 , "Cinema" , 50 , 50);

        System.out.println("------------------------------------------");
        librarianService.addBook(book1);
        librarianService.addBook(book2);
        librarianService.addBook(book3);

        System.out.println("All BOOKS :");
        for (Book book : bookMap.values()) {
            System.out.println(book);
        }



        // GET MEMBER BY ID
//        Member m1 = memberService.getMemberById(11);
//        System.out.println(m1);

        // GET ALL MEMBERS
//        System.out.println("------------------------------------------");
//        List<Member> memberList = memberService.getAllMembers();
//        System.out.println("All Members:");
//        for (Member member : memberList) {
//            System.out.println(member);
//        }




        System.out.println("------------------------------------------");
        borrowService.borrowBook(10 , 111);
        System.out.println("Available Copies: " + book1.getAvailableCopies());

        borrowService.borrowBook(10 , 112);

        borrowService.returnBook(10 , 111);
        System.out.println("Available Copies: " + book1.getAvailableCopies());

        System.out.println("------------------------------------------");
        System.out.println("Borrowed Books history : ");
        borrowService.viewBorrowingHistory(10);

        System.out.println("------------------------------------------");
        borrowService.viewOverdueBooks();





    }
}