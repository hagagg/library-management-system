package com.library.management.services;

import com.library.management.models.BorrowedBook;
import com.library.management.models.users.Member;
import com.library.management.services.users.MemberService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.NoSuchElementException;

public class FineService {

    private final MemberService memberService;

    private final double DAILY_FINE = 5.0;

    public FineService(MemberService memberService) {
        this.memberService = memberService;
    }

    public double calculateTotalFine (int memberId) {
        Member member = memberService.getMemberById(memberId);
        double totalFine = 0;

        for (BorrowedBook borrowedBook : member.getBorrowedBooks()) {

            if (!borrowedBook.isReturned()) {
                LocalDate today = LocalDate.now();
                LocalDate dueDate = borrowedBook.getDueDate();
                if (today.isAfter(dueDate)) {
                    long days = ChronoUnit.DAYS.between(dueDate, today);
                    totalFine += days * DAILY_FINE ;
                }

            }
        }
        return totalFine;
    }

    public void payFine (int memberId , double paidAmount) {
        Member member = memberService.getMemberById(memberId);

        if (member == null) {
            throw new NoSuchElementException ("Member with id: " + memberId + " does not exist");
        }

        if (paidAmount <= 0) {
            throw new IllegalArgumentException ("Paid amount must be greater than 0!!");
        }

        if (paidAmount > member.getFineBalance()) {
            throw new IllegalArgumentException ("Paid amount cannot be greater than the total fine balance.");
        }

        member.setFineBalance(member.getFineBalance() - paidAmount);

        if (member.getFineBalance() == 0) {
            System.out.println("You have paid your total fine balance.");
        } else {
            System.out.println("You have paid : " + paidAmount + " Remaining fine balance to pay: " + member.getFineBalance());
        }

    }


}
