package com.library.management.models;

import java.time.Year;

public class EBook extends Book {

    private String fileType;

    public EBook(String title, String author, int isbn, String category, Year publishYear, String fileType) {
        super(title, author, isbn, category, publishYear);
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

    @Override
    public String toString() {
        return "EBook{" +
                "fileType='" + fileType + '\'' +
                "} " + super.toString();
    }
}
