package de.sunbook.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import de.sunbook.api.connections.SqlServerConnection;
import de.sunbook.api.controllers.AccountController;
import de.sunbook.api.controllers.BooksController;
import de.sunbook.api.controllers.SellersController;
import de.sunbook.api.models.requestmodels.AuthenticationRequestModel;
import de.sunbook.api.models.responsemodels.BookResponseSingleModel;
import de.sunbook.api.models.tablemodels.BookModel;
import de.sunbook.api.services.SellerService;
import de.sunbook.api.utils.CustomRowMapper;

// This are the Api Standard Tests with special Use Cases or fundamental Functions
public class ApiStandardTests { 
    
    @Autowired
    BooksController booksController = new BooksController();

    @Autowired
    SellersController sellersController = new SellersController(); 

    @Autowired
    SellerService sellerService = new SellerService();

    @Autowired
    AccountController accountController = new AccountController();

    @Autowired
    AuthenticationRequestModel authenticationRequestModel = new AuthenticationRequestModel();
       

    /*
    Testcase 1
    Test of the searchBookByList Function
    Only valid input data
    test different combinations 
    compare between real value and expected value
    */
    @Test
    void searchBookByListTest() throws Exception{
        String testGenre = "romane";
        String testBinding = "Taschenbuch";
        Float testMaxPrice = (float) 10.0;
        Float testMinPrice = (float) 9.0;    
        
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        Date date = DateFor.parse("2021-05-24");

        BookResponseSingleModel expectedTestModel = new BookResponseSingleModel(1, "romane", "Die verschwundene Schwester", "Roman", "Lucinda Riley", 
        "Wer ist die geheimnisvolle verschwundene Schwester?Sieben Sterne umfasst das Sternbild der Plejaden, und die Schwestern d’Aplièse tragen ihre Namen. Stets war ihre siebte Schwester aber ein Rätsel für sie, denn Merope ist verschwunden, seit sie denken können. Eines Tages überbringt der Anwalt der Familie die verblüffende Nachricht, dass er eine Spur entdeckt hat: Ein Weingut in Neuseeland und die Zeichnung eines sternförmigen Rings weisen den Weg. Es beginnt eine Jagd quer über den Globus, denn Mary McDougal – die Frau, die als Einzige bestätigen kann, ob ihre Tochter Mary-Kate die verschwundene Schwester ist – befindet sich auf einer Weltreise. Während die Schwestern ihre Suche nach Neuseeland, Kanada, England, Frankreich und Irland führt, schlüpft ihnen Mary immer wieder durch die Finger. Und es scheint, als wolle sie unbedingt verhindern, gefunden zu werden …",
        "https://assets.thalia.media/img/artikel/30f20d721796708f7bf6f5c91fae544478e407fc-00-00.jpeg",
        "978-3-442-31448-5", "Goldmann", "Deutsch", date, "gebundene Ausgabe", (float) 22.0, null);
              
        assertThat(booksController.searchBookByList(testGenre, testBinding, testMaxPrice, testMinPrice)).isNotNull();
       
        assertThat(booksController.searchBookByList(null, null, null, null)).isNotNull(); 
        assertThat(booksController.searchBookByList(null, null, testMaxPrice, testMinPrice)).isNotNull();  
        assertThat(booksController.searchBookByList(null, testBinding, testMaxPrice, testMinPrice)).isNotNull();
        assertThat(booksController.searchBookByList(testGenre, testBinding, testMaxPrice, testMinPrice)).isNotNull();
        assertThat( booksController.searchBookByList(testGenre, null, null, null)).isNotNull();
        assertThat(booksController.searchBookByList(null, testBinding, null, null)).isNotNull();
        assertThat(booksController.searchBookByList(testGenre, testBinding, null, null)).isNotNull();
        assertThat(booksController.searchBookByList(testGenre, testBinding, null, null)).isNotNull();

        List<BookModel> listOfResults = booksController.searchBookByList("romane", "gebundene Ausgabe", (float) 22.1, (float) 21.9);
 
        assertThat(listOfResults).isNotNull();

        assertThat(listOfResults.contains(expectedTestModel));
    }


    /*
    Testcase 2
    Test of the getSingle Function
    Only valid input data
    compare between real value and expected value
    */
    @Test
    void getSingleTest() throws Exception{
        int testID = 1;
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        Date date = DateFor.parse("2021-05-24");

        BookResponseSingleModel expectedTestModel = new BookResponseSingleModel(testID, "romane", "Die verschwundene Schwester", "Roman", "Lucinda Riley", 
        "Wer ist die geheimnisvolle verschwundene Schwester?Sieben Sterne umfasst das Sternbild der Plejaden, und die Schwestern d’Aplièse tragen ihre Namen. Stets war ihre siebte Schwester aber ein Rätsel für sie, denn Merope ist verschwunden, seit sie denken können. Eines Tages überbringt der Anwalt der Familie die verblüffende Nachricht, dass er eine Spur entdeckt hat: Ein Weingut in Neuseeland und die Zeichnung eines sternförmigen Rings weisen den Weg. Es beginnt eine Jagd quer über den Globus, denn Mary McDougal – die Frau, die als Einzige bestätigen kann, ob ihre Tochter Mary-Kate die verschwundene Schwester ist – befindet sich auf einer Weltreise. Während die Schwestern ihre Suche nach Neuseeland, Kanada, England, Frankreich und Irland führt, schlüpft ihnen Mary immer wieder durch die Finger. Und es scheint, als wolle sie unbedingt verhindern, gefunden zu werden …",
        "https://assets.thalia.media/img/artikel/30f20d721796708f7bf6f5c91fae544478e407fc-00-00.jpeg",
        "978-3-442-31448-5", "Goldmann", "Deutsch", date, "gebundene Ausgabe", (float) 22.0, null);

        BookResponseSingleModel bookResponseModel = booksController.getSingle(testID);

        assertThat(bookResponseModel).isNotNull();
        assertThat(expectedTestModel.equals(bookResponseModel));
    }


    /*
    Testcase 3
    Test of the getSingleIsbn Function
    Only valid input data
    compare between real value and expected value
    */
    @Test
    void getSingleIsbnTest() throws Exception{
        String testISBN = "978-3-442-31448-5";
        
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        Date date = DateFor.parse("2021-05-24");

        BookModel expectedTestModel = new BookModel(1, "romane", "Die verschwundene Schwester", "Roman", "Lucinda Riley", 
        "Wer ist die geheimnisvolle verschwundene Schwester?Sieben Sterne umfasst das Sternbild der Plejaden, und die Schwestern d’Aplièse tragen ihre Namen. Stets war ihre siebte Schwester aber ein Rätsel für sie, denn Merope ist verschwunden, seit sie denken können. Eines Tages überbringt der Anwalt der Familie die verblüffende Nachricht, dass er eine Spur entdeckt hat: Ein Weingut in Neuseeland und die Zeichnung eines sternförmigen Rings weisen den Weg. Es beginnt eine Jagd quer über den Globus, denn Mary McDougal – die Frau, die als Einzige bestätigen kann, ob ihre Tochter Mary-Kate die verschwundene Schwester ist – befindet sich auf einer Weltreise. Während die Schwestern ihre Suche nach Neuseeland, Kanada, England, Frankreich und Irland führt, schlüpft ihnen Mary immer wieder durch die Finger. Und es scheint, als wolle sie unbedingt verhindern, gefunden zu werden …",
        "https://assets.thalia.media/img/artikel/30f20d721796708f7bf6f5c91fae544478e407fc-00-00.jpeg",
        testISBN, "Goldmann", "Deutsch", date, "gebundene Ausgabe", (float) 22.0);
       
        ResponseEntity<?> bookModel = booksController.getSingleIsbn(testISBN);

        assertThat(bookModel.getStatusCodeValue()).isEqualTo(200);
        assertThat(expectedTestModel.equals(bookModel.getBody()));
    }


    /*
    Testcase 4
    Test of the sellersControllerGet Function
    Only valid input data
    expect a valid output result
    */
    @Test
    void sellersControllerGetTest() throws Exception{
        int id = 1;   

        assertThat(sellersController.get(id)).isNotNull(); 
        assertThat(sellerService.getSeller(id)).isNotNull();

        ResponseEntity<?> responseEntity = sellersController.get(1);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }


    /*
    Testcase 5
    Test of the createAuthentification Function
    Only valid input data with a test user 
    expect a valid output result
    */
    @Test
    void createAuthenticationTokenTest() throws Exception{
          
        authenticationRequestModel.setUsername("lukas.reichelt@edu.fhdw.de");
        authenticationRequestModel.setPassword("12345678");
        
        assertThat(accountController.createAuthenticationToken(authenticationRequestModel)).isNotNull();

        ResponseEntity<?> responseEntity = accountController.createAuthenticationToken(authenticationRequestModel);
        
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }


    /*
    Testcase 6
    Test of the sqlConnection Function for the connection to the database 
    expect a valid output result that is not null
    */
    @Test
    void sqlConnectionTest() throws Exception{
        assertThat(SqlServerConnection.getInstance()).isNotNull();
    }


    /*
    Testcase 7
    Test of the query Function
    Only valid input data with a test book 
    expect a valid output result that is not null
    */
    @Test
    void queryTest() throws Exception{
        String testSql ="SELECT * FROM BOOK WHERE isbn = '978-3-442-31448-5'";
        SqlServerConnection.getInstance().query(testSql, CustomRowMapper.GetBookMapper());

        assertThat(SqlServerConnection.getInstance().query(testSql, CustomRowMapper.GetBookMapper())).isNotNull();
    } 
    
}
