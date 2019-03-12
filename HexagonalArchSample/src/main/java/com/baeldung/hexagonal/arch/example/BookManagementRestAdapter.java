package com.baeldung.hexagonal.arch.example;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books/")
public class BookManagementRestAdapter implements BookManagementUserInterfacePort {

    private BookManagementService bookManagementService;

    @Autowired
    public void setBookManagementService(BookManagementService bms) {
        bookManagementService = bms;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<Book> list() {
        return bookManagementService.findAllBooks();
    }

    @PostMapping(path = "/add", produces = "application/json", consumes = "application/json")
    public Response addBook(String name, Date launched, Date purchasedDate, Double price) {
        add(name, launched, purchasedDate, price);
        RestResponseWrapper response = new RestResponseWrapper(); // build rest response using any rest client
        return response.sendResponse("Book Added Successfully");
    }

    public void add(String name, Date launched, Date purchasedDate, Double price) {
        BookRequestWrapper brw = new BookRequestWrapper(name, launched, purchasedDate, null, "REST", price);
        Book b = brw.getOrCreateBook();
        bookManagementService.saveBook(b);
    }
    
    
    public Book delete(int id) {
        return bookManagementService.deleteBook(id);
    }

    @GetMapping(path = "/delete/{id}", produces = "application/json")
    public Response deleteBook(@PathVariable int id) {
        Book b = delete(id);
        RestResponseWrapper response = new RestResponseWrapper();
        if (b!= null) return response.sendResponse("Book Deleted Successfully");
        return response.sendResponse("Failed to delete");
    }
    
    @GetMapping(path = "/{id}", produces = "application/json")
    public Response findBook(@PathVariable int id) {
        Book b = find(id);
        RestResponseWrapper response = new RestResponseWrapper();
        return response.sendResponse(b);
    }

    public Book find(@PathVariable int id) {
        return bookManagementService.findById(id);
    }
}
