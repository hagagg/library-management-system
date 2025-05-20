package com.library.management.models;

import java.time.Year;

public abstract class Book {

    private String title;
    private String author;
    private int isbn;
    private String category;
    private Year publishYear;

    public Book(String title, String author, int isbn, String category, Year publishYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.publishYear = publishYear;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getCategory() {
        return category;
    }

    public Year getPublishYear() {
        return publishYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn=" + isbn +
                ", category='" + category + '\'' +
                ", publishYear=" + publishYear +
                '}';
    }
}
