package com.library.management.models;

import com.library.management.interfaces.Borrowable;

import java.time.Year;

public class PaperBook extends Book implements Borrowable {

    private int stock;

    public PaperBook(String title, String author, int isbn, String category, Year publishYear, int stock) {
        super(title, author, isbn, category, publishYear);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public boolean isAvailable() {
        return stock > 0;
    }

    public void decreaseStock () {
        if (stock <= 0) {
            throw new IllegalArgumentException("There is no stock!");
        }
        stock--;
    }

    public void increaseStock () {
        stock++;
    }

    @Override
    public String toString() {
        return "PaperBook{" +
                "stock=" + stock +
                "} " + super.toString();
    }
}
