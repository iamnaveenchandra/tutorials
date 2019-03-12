package com.baeldung.hexagonal.arch.example;

import java.util.Date;

public class Book {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getDateLaunched() {
        return dateLaunched;
    }

    public void setDateLaunched(Date dateLaunched) {
        this.dateLaunched = dateLaunched;
    }

    public Date getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    private Integer id;
    private String name;
    private String publisher;
    private Date dateLaunched;
    private Date datePurchased;
    private Double price;
    // setters and getters
}
