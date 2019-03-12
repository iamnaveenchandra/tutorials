package com.baeldung.hexagonal.arch.example;

import java.util.List;

public interface BookRepository {

    public List<Book> findAllBooks();

    public void saveBook(Book book);

    public Book findById(int id);

    public Book deleteBook(int id);
}