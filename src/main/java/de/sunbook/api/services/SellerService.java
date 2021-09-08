package de.sunbook.api.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.sunbook.api.models.responsemodels.SellerResponseModel;
import de.sunbook.api.processors.UserProcessor;

@Service
public class SellerService {
    
@Autowired
    private BookService bookService;

    @Autowired
    private UserProcessor userProcessor;
    
    public SellerResponseModel getSeller(int id) throws SQLException {
        var model = userProcessor.selectSeller(id);
        var list = bookService.getBooksForUser(id);
        return new SellerResponseModel(model, list);
    }
}
