package de.sunbook.api.controllers.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.sunbook.api.models.tablemodels.BookModel;
import de.sunbook.api.services.BookService;

@RestController
@RequestMapping("/api/admin/books")
public class BooksAdminController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public void post(@RequestBody BookModel model) throws SQLException {
        bookService.post(model);
        return;
    }

    @PutMapping("/{id}")
    public void put(@PathVariable int id, @RequestBody BookModel model) throws SQLException {
        bookService.put(id, model);
        return;
    }

    @PatchMapping("/{id}")
    public void patch(@PathVariable int id, @RequestBody BookModel model) throws SQLException {
        bookService.patch(id, model);
        return;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws SQLException {
        bookService.delete(id);
        return;
    }
}
