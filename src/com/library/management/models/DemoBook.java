package com.library.management.models;

import java.time.Year;

public class DemoBook extends Book {

    private String Link;

    public DemoBook(String title, String author, int isbn, String category, Year publishYear, String link) {
        super(title, author, isbn, category, publishYear);
        Link = link;
    }

    public String getLink() {
        return Link;
    }

    @Override
    public String toString() {
        return "DemoBook{" +
                "Link='" + Link + '\'' +
                "} " + super.toString();
    }
}
