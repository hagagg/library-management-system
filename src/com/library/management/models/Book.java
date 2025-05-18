package com.library.management.models;

public class Book {

    private String title;
    private String author;
    private int isbn;
    private String category;
    private int totalCopies;
    private int availableCopies;

    public Book(String title, String author, int isbn, String category, int totalCopies, int availableCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
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

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn=" + isbn +
                ", category='" + category + '\'' +
                ", totalCopies=" + totalCopies +
                ", availableCopies=" + availableCopies +
                '}';
    }

    public void increaseAvailableCopies () {
        if ( availableCopies <  totalCopies ) {
            availableCopies ++;
        }
    }

    public void decreaseAvailableCopies () {
        if ( availableCopies > 0 ) {
            availableCopies --;
        }
    }
}
