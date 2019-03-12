package com.baeldung.hexagonal.arch.example;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookManagementWebAdapter implements BookManagementUserInterfacePort {

    private BookManagementService service;

    @Autowired
    public void setBookManagementService(BookManagementService bms) {
        service = bms;
    }

    @GetMapping(path = "/list", produces = "application/json")
    public String showBooks(Model model) {
       model.addAttribute("bookList", list());
       return "books/bookSummary";
    }

    public List<Book> list() {
        return service.findAllBooks();
    }
    @PostMapping(path="/add", produces = "application/json", consumes="application/json")
    public String addBook(@ModelAttribute("bookForm") Book book, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "books/bookForm";
        }

        add(book.getName(), book.getDateLaunched(), book.getDatePurchased(), book.getPrice());
        if (book.getId() != 0) {
            redirectAttributes.addFlashAttribute("msg", "Book Added Succesfully");
        }
        return "redirect:/books/" + book.getId();
        
    }

    @GetMapping(path = "/delete/{id}", produces = "application/json")
    public String deleteBook(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Book b = delete(id);
        if (b != null) {
            redirectAttributes.addFlashAttribute("msg", "Book deleted Succesfully");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Unable to delete Book");
        }
        return "redirect:/books/list";
    }

    @GetMapping(path = "/details/{id}", produces = "application/json")
    public String getBook(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Book b = find(id);
        if (b == null) {
            redirectAttributes.addFlashAttribute("msg", "Book not found");
            return "redirect:/books/bookForm";
        }
        return "books/showdetail";
        
    }

    public void add(String name, Date launched, Date purchasedDate, Double price) {
        BookRequestWrapper brw = new BookRequestWrapper(name, launched, purchasedDate, null, "BROWSER", price);
        Book b = brw.getOrCreateBook();
        service.saveBook(b);
    }

    public Book find(int id) {
        return service.findById(id);
    }
    
    public Book delete(int id) {
        return service.deleteBook(id);
    }

}
