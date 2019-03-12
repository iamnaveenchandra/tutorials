package com.baeldung.hexagonal.arch.example;

import java.util.Date;
import java.util.List;

public interface BookManagementUserInterfacePort {

    public List<Book> list();

    public void add(String name, Date launched, Date purchasedDate, Double price);

    public Book delete(int id);

    public Book find(int id);
}
