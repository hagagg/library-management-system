package com.library.management.services;

import com.library.management.models.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


public class BookService {

    private final Map < Integer , Book> bookMap ;

    public BookService(Map<Integer, Book> bookMap) {
        this.bookMap = bookMap;
    }

    @Override
    public String toString() {
        return "BookService{" +
                "bookMap=" + bookMap +
                '}';
    }


    public void addBook (Book book) {

        if (!bookMap.containsKey(book.getIsbn())) {
            bookMap.put (book.getIsbn() , book);
            System.out.println("Book with ISBN: " + book.getIsbn() +" added successfully");
        } else {
            throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists");
        }
    }

    public void removeBook (int bookIsbn) {
        if (bookMap.containsKey(bookIsbn)) {
            bookMap.remove(bookIsbn);
            System.out.println("Book with ISBN: " + bookIsbn + " removed successfully");
        } else {
            throw new IllegalArgumentException("Book with ISBN " + bookIsbn + " does not exist");
        }
    }

    public Book getBookByIsbn (int isbn) {
        if (bookMap.containsKey(isbn)) {
            return bookMap.get(isbn);
        } else  {
            throw new NoSuchElementException("Book with ISBN " + isbn + " does not exist");
        }
    }

    public List <Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>(bookMap.values());

        if (bookList.isEmpty()) {
            throw new NoSuchElementException("There are no books!!");
        }
        return bookList;
    }

    // Get All Books that have this title
    public List<Book> getBookByTitle (String title) {
        List<Book> bookList = new ArrayList<>();

        for (Book book : bookMap.values()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                bookList.add(book);
            }
        }
        if (!bookList.isEmpty()) {
            return bookList;
        } else {
            throw new NoSuchElementException("No such Book with title " + title + " exists");
        }
    }

    // Get All Books by this author
    public List<Book> searchByAuthor (String author) {
        List<Book> bookList = new ArrayList<>();

        for (Book book : bookMap.values()) {
            if (book.getAuthor().toLowerCase().contains((author).toLowerCase())) {
                bookList.add(book);
            }
        }
        if (!bookList.isEmpty()) {
            return bookList;
        } else {
            throw new NoSuchElementException("No such Book with author " + author + " exists");
        }
    }

    public void updateBook (Book book) {
        if (bookMap.containsKey(book.getIsbn())) {
            bookMap.replace(book.getIsbn(), book);
            System.out.println("Book with ISBN: " + book.getIsbn() +" updated successfully");
        } else {
            throw new NoSuchElementException("Book with ISBN " + book.getIsbn() + " does not exist");
        }
    }



}
