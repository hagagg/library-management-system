package com.library.management.models.users;

import com.library.management.models.BorrowedBook;

import java.time.LocalDate;
import java.util.List;

public abstract class Member extends User {

    private int borrowLimit;
    private double fineBalance;
    private List<BorrowedBook> borrowedBooks;
    private LocalDate memberShipDate;

    public Member(int id, String name, String email, Boolean isActive, int borrowLimit, LocalDate memberShipDate, List<BorrowedBook> borrowedBooks, double fineBalance) {
        super(id, name, email, isActive);
        this.borrowLimit = borrowLimit;
        this.memberShipDate = memberShipDate;
        this.borrowedBooks = borrowedBooks;
        this.fineBalance = fineBalance;
    }

    @Override
    public String toString() {
        return "Member{" +
                super.toString() +
                "borrowLimit=" + borrowLimit +
                ", fineBalance=" + fineBalance +
                ", borrowedBooks=" + borrowedBooks +
                ", memberShipDate=" + memberShipDate +
                '}' ;
    }

    public int getBorrowLimit() {
        return borrowLimit;
    }

    public double getFineBalance() {
        return fineBalance;
    }

    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public LocalDate getMemberShipDate() {
        return memberShipDate;
    }

    public void setFineBalance(double fineBalance) {
        this.fineBalance = fineBalance;
    }

    public void addBorrowedBooks (BorrowedBook borrowedBooks) {
        this.borrowedBooks.add (borrowedBooks);
    }

    public void removeBorrowedBooks (BorrowedBook borrowedBooks) {
        this.borrowedBooks.remove (borrowedBooks);
    }






}
