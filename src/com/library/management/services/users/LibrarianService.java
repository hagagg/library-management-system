package com.library.management.services.users;

import com.library.management.models.Book;
import com.library.management.models.users.Librarian;
import com.library.management.models.users.Member;
import com.library.management.services.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class LibrarianService {

    Map<Integer, Librarian> librarianMap;
    private final BookService bookService;
    private final MemberService memberService;

    public LibrarianService(Map<Integer, Librarian> librarianMap, BookService bookService , MemberService memberService) {
        this.librarianMap = librarianMap;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @Override
    public String toString() {
        return "LibrarianService{" +
                "librarianMap=" + librarianMap +
                ", bookService=" + bookService +
                '}';
    }

    public Librarian getLibrarianbyId (int librarianId) {
        Librarian librarian = librarianMap.get(librarianId);

        if (librarian != null) {
            return librarian;
        } else {
            throw new NoSuchElementException("Librarian with id " + librarianId + " not found");
        }

    }

    List <Librarian> getAllLibrarians() {
        return new ArrayList<>(librarianMap.values());
    }

    public List <Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    public void addBook (Book book) {
        bookService.addBook(book);
    }

    public void removeBook (int bookIsbn) {
        bookService.removeBook(bookIsbn);
    }

    public void updateBook (Book book) {
        bookService.updateBook(book);
    }

    public List <Book> getAllBooks(){
        return bookService.getAllBooks();
    }


}
