package com.baeldung.hexagonal.arch.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {

    // in memory solution
    private Map<Integer, Book> dataStore = new HashMap<>();

    @Override
    public List<Book> findAllBooks() {
        return dataStore.values()
            .stream()
            .collect(Collectors.toList());
    }

    public void saveBook(Book book) {
        dataStore.putIfAbsent(book.getId(), book);
    }

    public Book findById(int id) {
        return dataStore.get(id);
    }

    public Book deleteBook(int id) {
        return dataStore.remove(id);
    }

}
