package de.sunbook.api.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.sunbook.api.models.responsemodels.SellerResponseModel;
import de.sunbook.api.processors.UserProcessor;

@Service
public class SellerService {

    @Autowired
    private BookService bookService = new BookService();

    @Autowired
    private UserProcessor userProcessor = new UserProcessor();

    public List<SellerResponseModel> get() throws SQLException {
        var sellers = userProcessor.selectSeller();
        var list = new ArrayList<SellerResponseModel>();
        for (var seller : sellers) {
            list.add(getSeller(seller.getUserId()));
        }
        return list;
    }

    public SellerResponseModel getSeller(int id) throws SQLException {
        var model = userProcessor.selectSeller(id);
        var list = bookService.getBooksForUser(id);
        return new SellerResponseModel(model, list);
    }
}
