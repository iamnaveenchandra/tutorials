package com.baeldung.hexagonal.arch.example;

import java.util.Date;

public class BookRequestWrapper {

    String reqType;
    String error;
    Book book;
    String bookName;
    Date bookLaunchedDate;
    Date bookPurchasedDate;
    Double bookPrice;

    public BookRequestWrapper(String name, Date lDate, Date pDate, String error, String reqType, Double price) {
        this.reqType = reqType;
        bookName = name;
        bookLaunchedDate = lDate;
        bookPurchasedDate = pDate;
        bookPrice = price;
    }

    public Book getOrCreateBook() {
        if (book == null)
            book = new Book();
        book.setName(bookName);
        book.setDateLaunched(bookLaunchedDate);
        book.setDatePurchased(bookPurchasedDate);
        book.setPrice(bookPrice);
        return book;
    }
}
