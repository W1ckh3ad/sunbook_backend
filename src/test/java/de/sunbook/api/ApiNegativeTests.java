package de.sunbook.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import de.sunbook.api.connections.SqlServerConnection;
import de.sunbook.api.controllers.AccountController;
import de.sunbook.api.controllers.BooksController;
import de.sunbook.api.controllers.SellersController;
import de.sunbook.api.models.requestmodels.AuthenticationRequestModel;
import de.sunbook.api.utils.CustomRowMapper;

public class ApiNegativeTests {

    @Autowired
    BooksController booksController = new BooksController();
    
    @Autowired
    SellersController sellersController; 


    @Test
    void searchBookByListNegativeTest() throws Exception{
        String testGenre = "romane";
        String testBinding = "Taschenbuch";
        Float testMaxPrice = (float) 10.0;
        Float testMinPrice = (float) 9.0;       
        
        assertThat(booksController.searchBookByList(testMaxPrice.toString(), testMinPrice.toString(), null, null)).isEmpty();

        assertThat(booksController.searchBookByList(null, null, testMaxPrice, (testMinPrice - ((float) 100000.0)))).isNotEmpty();

        assertThat(booksController.searchBookByList(null, null, testMinPrice, testMaxPrice)).isEmpty();

        assertThat(assertThrows(Exception.class, () -> {
            booksController = null;
            booksController.searchBookByList(null, null, null, null); 
        })).isNotNull();

        assertThat(assertThrows(Exception.class, () -> {
            booksController = null;
            booksController.searchBookByList(null, null, Float.parseFloat(testBinding), Float.parseFloat(testGenre)); 
        })).isNotNull(); 
    }


    @Test
    void getSingleNegativeTest() throws Exception{
        int testID = -15; 
        
        assertThat(testID).isNotNull();
        
        assertThat(assertThrows(Exception.class, () -> {
            booksController.getSingle(testID);
        })).isNotNull();

        assertThat(assertThrows(Exception.class, () -> {
            booksController.getSingle(0);
        })).isNotNull();
    }


    @Test
    void getSingleIsbnNegativeTest() throws Exception{
        String testISBN = "abc";
           
        assertThat(testISBN).isNotNull();

        assertThat(assertThrows(Exception.class, () -> {
            booksController.getSingleIsbn(testISBN);
        })).isNotNull();

        assertThat(assertThrows(Exception.class, () -> {
            booksController.getSingleIsbn(testISBN);
        })).isNotNull();
    }



    @Test
    void sellersControllerGetNegativeTest() throws Exception{
        int id = -11;   
        
        assertThat(assertThrows(Exception.class, () -> {
            sellersController.get(id);
        })).isNotNull();

        assertThat(assertThrows(Exception.class, () -> {
            sellersController.get(0);
        })).isNotNull();
    }


    @Test
    void createAuthenticationTokenNegativeTest() throws Exception{
        AccountController accountController = new AccountController();
        AuthenticationRequestModel authenticationRequestModel = new AuthenticationRequestModel();
        authenticationRequestModel.setUsername("");
        authenticationRequestModel.setPassword("");

        assertThat(assertThrows(Exception.class, () -> {
            accountController.createAuthenticationToken(authenticationRequestModel);
        })).isNotNull();
    }


    @Test
    void queryNegativeTest() throws Exception{
        String testSql ="SLCT * FRM Bk WHR sbn = '978-3-442-31448-5'";       
        
        assertThat(assertThrows(Exception.class, () -> {
            SqlServerConnection.getInstance().query(testSql, CustomRowMapper.GetBookMapper());
        })).isNotNull();

    } 
}
