package com.library.management.services;

import com.library.management.models.*;
import com.library.management.models.users.Member;
import com.library.management.services.users.AdminService;
import com.library.management.services.users.MemberService;


import java.time.LocalDate;
import java.util.Map;
import java.util.NoSuchElementException;

public class BorrowService {

   private final Map < Integer , BorrowedBook> borrowedBooksMap ;
   private final MemberService memberService;
   private final AdminService adminService;
   private final FineService fineService;
   private final BookService bookService;

    public BorrowService(Map<Integer, BorrowedBook> borrowedBooksMap, MemberService memberService, AdminService adminService, FineService fineService, BookService bookService) {
        this.borrowedBooksMap = borrowedBooksMap;
        this.memberService = memberService;
        this.adminService = adminService;
        this.fineService = fineService;
        this.bookService = bookService;
    }

    @Override
    public String toString() {
        return "BorrowService{" +
                "borrowedBooksMap=" + borrowedBooksMap +
                ", memberService=" + memberService +
                ", adminService=" + adminService +
                ", fineService=" + fineService +
                ", bookService=" + bookService +
                '}';
    }


    public void borrowBook (int memberId , int bookIsbn) {

        Member member = memberService.getMemberById(memberId);
        Book book = bookService.getBookByIsbn(bookIsbn);

        if (member == null || !member.getActive()) {
            throw new NoSuchElementException("Member with ID " + memberId + " does not exist or not active!");
        }

        if (book == null || book.getAvailableCopies() <= 0) {
            throw new NoSuchElementException("Book with ISBN " + bookIsbn + " is not available.");
        }

        if (member.getBorrowedBooks().size() >= member.getBorrowLimit()) {
            throw new IllegalArgumentException("You have reached the maximum number of books you can borrow.");
        }

        LocalDate borrowedDate = LocalDate.now();
        LocalDate dueDate = borrowedDate.plusWeeks(2);

        BorrowedBook borrowedBook = new BorrowedBook (book , memberId, borrowedDate , dueDate  , false);

        member.addBorrowedBooks(borrowedBook);

        bookService.decreaseAvailableCopies(bookIsbn);

        System.out.println("You have borrowed the book with ISBN: " + bookIsbn + " successfully.");

    }

    public void returnBook (int memberId ,  int bookIsbn ) {
        Member member = memberService.getMemberById(memberId);

        if (member == null) {
            throw new NoSuchElementException("No member with ID " + memberId + " exists!");
        }

        BorrowedBook borrowedBook = null;
        for (BorrowedBook b : member.getBorrowedBooks()) {
            if (b.getBook().getIsbn() == bookIsbn && !b.isReturned()) {
                borrowedBook = b;
                break;
            }
        }

        if  (borrowedBook == null) {
            throw new NoSuchElementException("Book with ISBN " + bookIsbn + " wasn't borrowed!");
        }

        // Set book as returned and set the return date
        borrowedBook.setReturned(true);
        borrowedBook.setReturnDate(LocalDate.now());

        // Update available copies
        bookService.increaseAvailableCopies(bookIsbn);

        // Calculate fine
        double fine = fineService.calculateTotalFine(memberId);
        member.setFineBalance(member.getFineBalance() + fine);

        if (fine > 0) {
            System.out.println("Book has been successfully returned. your fine balance is now: " + fine);
        } else {
            System.out.println("Book Returned Successfully. There isn't any fine balance.");
        }

    }

    public void viewBorrowingHistory (int memberId ) {
        Member member = memberService.getMemberById(memberId);

        if (member == null ) {
           throw new NoSuchElementException("No member with ID " + memberId + " exists!");
        }

        if (member.getBorrowedBooks().isEmpty() ){
            throw new NoSuchElementException("No borrowed books for this member.");
        }

        for (BorrowedBook book : member.getBorrowedBooks()) {
            System.out.println(book);
        }

    }

    public void viewOverdueBooks () {
        boolean foundOverdue = false;

            for (BorrowedBook book : borrowedBooksMap.values()) {
                if (!book.isReturned() && LocalDate.now().isAfter(book.getDueDate())) {
                    Member member = memberService.getMemberById(book.getMemberId());

                    if (member != null) {
                        System.out.println( "Overdue book: " + book.getBook().getIsbn() + "by member: " + member.getId() );
                    } else {
                        throw new NoSuchElementException("Member with ID " + book.getMemberId() + " does not exist!");
                    }
                    foundOverdue = true;
                }
            }
            if (!foundOverdue) {
                System.out.println("There isn't any overdue books.");
            }

    }

}





