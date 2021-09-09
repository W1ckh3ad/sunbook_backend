package de.sunbook.api.controllers.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> post(@RequestBody(required = true) BookModel model) throws SQLException, Exception {
        if (model == null) {
            throw new Exception("Request body missing");
        }
        var returnModel = bookService.post(model);
        return ResponseEntity.ok(returnModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable int id, @RequestBody(required = true) BookModel model)
            throws SQLException, Exception {
        if (model == null) {
            throw new Exception("Request body missing");
        }
        bookService.put(id, model);
        return ResponseEntity.ok("Book was updated successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patch(@PathVariable int id, @RequestBody(required = true) BookModel model)
            throws SQLException, Exception {
        if (model == null) {
            throw new Exception("Request updated missing");
        }
        bookService.patch(id, model);
        return ResponseEntity.ok("Book was added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws SQLException {
        bookService.delete(id);
        return ResponseEntity.ok("Book was removed successfully");
    }
}
