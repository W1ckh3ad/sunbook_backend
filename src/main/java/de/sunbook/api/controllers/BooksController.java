package de.sunbook.api.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.sunbook.api.models.requestmodels.BookQueryModel;
import de.sunbook.api.models.responsemodels.BookResponseSingleModel;
import de.sunbook.api.models.tablemodels.BookModel;
import de.sunbook.api.models.tablemodels.UserBookMapModel;
import de.sunbook.api.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/books")
public class BooksController {

        @Autowired
        private BookService bookService;

        @GetMapping
        @Operation(description = "Select different optional Statements for the List Search", parameters = {
                        @Parameter(description = "Select a Genre", name = "genre", example = "krimis", required = false),
                        @Parameter(description = "Select a Binding", name = "binding", example = "gebundene Ausgabe", required = false),
                        @Parameter(description = "Select minPrice", name = "minPrice", example = "25.0", required = false),
                        @Parameter(description = "Select maxPrice", name = "maxPrice", example = "30.0", required = false) })
        public List<BookModel> searchBookByList(@RequestParam(required = false) String genre,
                        @RequestParam(required = false) String binding, @RequestParam(required = false) Float maxPrice,
                        @RequestParam(required = false) Float minPrice) throws SQLException {
                BookQueryModel query = new BookQueryModel(genre, binding, maxPrice, minPrice);
                return bookService.get(query);
        }

        @GetMapping("/{id}")
        public BookResponseSingleModel getSingle(@PathVariable int id) throws SQLException {
                return bookService.getWithSellers(id);
        }

        @GetMapping("/isbn/{isbn}")
        @Operation(description = "Search for a Title, Subtitle, Author or ISBM", parameters = {
                        @Parameter(name = "isbn", example = "978-3-442-31448-5") })
        public List<BookModel> getSingleIsbn(@PathVariable String isbn) throws SQLException {
                return null;
        }

        @PostMapping
        public void post(@RequestBody BookModel model) throws SQLException {
                return;
        }

        @PutMapping("/{id}")
        public void put(@PathVariable int uid, @RequestBody UserBookMapModel model) throws SQLException {
                return;
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable int uid) throws SQLException {
                return;
        }
}
