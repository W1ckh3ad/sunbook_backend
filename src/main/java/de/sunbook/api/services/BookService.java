package de.sunbook.api.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.sunbook.api.models.requestmodels.BookQueryModel;
import de.sunbook.api.models.responsemodels.BookResponseModel;
import de.sunbook.api.models.responsemodels.BookResponseSingleModel;
import de.sunbook.api.models.responsemodels.UserBookModelForBookResponseSingleModel;
import de.sunbook.api.models.tablemodels.BookModel;
import de.sunbook.api.processors.BookProcessor;
import de.sunbook.api.processors.UserBookMapProcessor;

@Service
public class BookService {

    @Autowired
    private BookProcessor bookProcessor;

    @Autowired
    private UserBookMapProcessor userBookMapProcessor;

    public List<BookModel> get() throws SQLException {
        return bookProcessor.get();
    }

    public BookModel get(int id) throws SQLException {
        return bookProcessor.get(id);
    }

    public void put(int id, BookModel model) throws SQLException {
        model.setUid(id);
        bookProcessor.put(model);
    }

    public void post(BookModel model) throws SQLException {
        bookProcessor.post(model);
    }

    public void delete(int id) throws SQLException {
        bookProcessor.delete(id);
    }

    public List<BookModel> get(BookQueryModel model) throws SQLException {
        return bookProcessor.get(model);
    }

    public List<BookResponseModel> getBooksForUser(String username) throws SQLException {
        return userBookMapProcessor.getBooksForUserName(username);
    }

    public List<BookResponseModel> getBooksForUser(int id) throws SQLException {
        return userBookMapProcessor.getBooksForUserId(id);
    }

    public List<UserBookModelForBookResponseSingleModel> getShopsForBook(int id) throws SQLException {
        return userBookMapProcessor.getShopsForBook(id);
    }

    public BookResponseSingleModel getWithSellers(int id) throws SQLException {
        BookModel model = bookProcessor.get(id);
        var list = userBookMapProcessor.getShopsForBook(id);
        var response = new BookResponseSingleModel(model, list);
        return response;
    }
}
