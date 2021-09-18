package de.sunbook.api.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.sunbook.api.models.responsemodels.SellerSingleResponseModel;
import de.sunbook.api.models.tablemodels.UserModel;
import de.sunbook.api.processors.UserProcessor;

@Service
public class SellerService {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserProcessor userProcessor;

    public List<UserModel> get() throws SQLException {
        return userProcessor.selectSeller();
    }

    public SellerSingleResponseModel getSeller(int id) throws SQLException {
        var model = userProcessor.selectSeller(id);
        var list = bookService.getBooksForUser(id);
        return new SellerSingleResponseModel(model, list);
    }
}
