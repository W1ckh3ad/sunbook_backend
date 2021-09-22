package de.sunbook.api.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import de.sunbook.api.models.requestmodels.AddBookToSellRequestModel;
import de.sunbook.api.models.requestmodels.BookQueryModel;
import de.sunbook.api.models.responsemodels.BookResponseModel;
import de.sunbook.api.models.responsemodels.BookResponseSingleModel;
import de.sunbook.api.models.responsemodels.UserBookModelForBookResponseSingleModel;
import de.sunbook.api.models.tablemodels.BookModel;
import de.sunbook.api.models.tablemodels.UserBookMapModel;
import de.sunbook.api.processors.BookProcessor;
import de.sunbook.api.processors.UserBookMapProcessor;
import de.sunbook.api.processors.UserProcessor;

/*
This is the Service for the Books, this class provides the Logic of the Programm, called by the Controllers 
*/
@Service
public class BookService {

    @Autowired
    private UserProcessor userProcessor;

    @Autowired
    private BookProcessor bookProcessor = new BookProcessor();

    @Autowired
    private UserBookMapProcessor userBookMapProcessor = new UserBookMapProcessor();

    public List<BookModel> get() throws SQLException {
        return bookProcessor.select();
    }

    public BookModel get(int id) throws SQLException {
        return bookProcessor.select(id);
    }

    public BookModel getIsbn(String isbn) throws SQLException {
        BookModel model = bookProcessor.select(isbn);
        var list = userBookMapProcessor.selectShopsForBook(model.getUid());
        var response = new BookResponseSingleModel(model, list);
        return response;
    }

    public void put(int id, BookModel model) throws SQLException {
        model.setUid(id);
        bookProcessor.update(model);
    }

    public void patch(int id, BookModel model) throws SQLException {
        model.setUid(id);
        bookProcessor.update(model);
    }

    public BookModel post(BookModel model) throws SQLException {
        var id = bookProcessor.insert(model);
        model.setUid(id);
        return model;
    }

    public void delete(int id) throws SQLException {
        bookProcessor.delete(id);
    }

    public List<BookModel> get(BookQueryModel model) throws SQLException {
        return bookProcessor.select(model);
    }

    public List<BookResponseModel> getBooksForUser(String username) throws SQLException {
        return userBookMapProcessor.selectBooksForUserName(username);
    }

    public List<BookResponseModel> getBooksForUser(int id) throws SQLException {
        return userBookMapProcessor.selectBooksForUserId(id);
    }

    public List<UserBookModelForBookResponseSingleModel> getShopsForBook(int id) throws SQLException {
        return userBookMapProcessor.selectShopsForBook(id);
    }

    public BookResponseSingleModel getWithSellers(int id) throws SQLException {
        BookModel model = bookProcessor.select(id);
        var list = userBookMapProcessor.selectShopsForBook(id);
        var response = new BookResponseSingleModel(model, list);
        return response;
    }

    public void postBookToSell(String username, AddBookToSellRequestModel request) throws SQLException {
        var user = userProcessor.selectUsername(username);
        var oldModel = userBookMapProcessor.select(request.getBookId(), user.getUserId());
        if (oldModel != null) {
            throw new NotFoundException("Book is already in selling list");
        }
        var model = new UserBookMapModel(request.getBookId(), user.getUserId(), request.getDescription());
        userBookMapProcessor.insert(model);
    }

    public void deleteBookToSell(String username, int id) throws SQLException {
        var user = userProcessor.selectUsername(username);
        var oldModel = userBookMapProcessor.select(id, user.getUserId());
        if (oldModel == null) {
            throw new NotFoundException("Book does not exist in selling list");
        }
        userBookMapProcessor.delete(id, user.getUserId());
    }

    public void updateBookToSell(String username, int id, AddBookToSellRequestModel request)
            throws SQLException, NotFoundException {
        var user = userProcessor.selectUsername(username);
        var model = new UserBookMapModel(id, user.getUserId(), request.getDescription());
        var exists = userBookMapProcessor.select(id, user.getUserId());
        if (exists == null) {
            throw new NotFoundException("Updating not existing data is not possible");
        }
        userBookMapProcessor.update(model);
    }

    public BookResponseModel getBookToSell(String username, int id) throws SQLException {
        var user = userProcessor.selectUsername(username);
        return get(user.getUserId(), id);
    }

    public BookResponseModel get(int userId, int bookId) throws SQLException {
        return userBookMapProcessor.selectBookForUserId(userId, bookId);
    }

}
