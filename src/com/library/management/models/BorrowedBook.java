package com.library.management.models;

import java.time.LocalDate;

public class BorrowedBook {

    private final Book book;
    private final int memberId;
    private final LocalDate borrowedDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;
    private boolean isReturned;

    public BorrowedBook(Book book, int memberId, LocalDate borrowedDate, LocalDate dueDate, boolean isReturned) {
        this.book = book;
        this.memberId = memberId;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.isReturned = isReturned;
    }

    public Book getBook() {
        return book;
    }

    public int getMemberId() {
        return memberId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    @Override
    public String toString() {
        return "BorrowedBook{" +
                "book=" + book +
                ", borrowedDate=" + borrowedDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", isReturned=" + isReturned +
                '}';
    }
}
