package com.baeldung.hexagonal.arch.example;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookManagementServiceImpl implements BookManagementService {

    BookRepository repo;
    private final AtomicInteger id = new AtomicInteger(0);

    @Autowired
    public BookManagementServiceImpl(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> findAllBooks() {
        return repo.findAllBooks();
    }

    public void saveBook(Book book) {
        book.setId(id.incrementAndGet());
        repo.saveBook(book);
    }

    public Book findById(int id) {
        return repo.findById(id);
    }

    public Book deleteBook(int id) {
        return repo.deleteBook(id);
    }

}
