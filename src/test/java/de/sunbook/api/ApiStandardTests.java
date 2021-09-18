package de.sunbook.api;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.assertj.core.api.Assertions.assertThat;
import de.sunbook.api.connections.SqlServerConnection;
import de.sunbook.api.controllers.AccountController;
import de.sunbook.api.controllers.BooksController;
import de.sunbook.api.controllers.SellersController;
import de.sunbook.api.models.requestmodels.AuthenticationRequestModel;
import de.sunbook.api.models.requestmodels.BookQueryModel;
import de.sunbook.api.models.responsemodels.BookResponseSingleModel;

import de.sunbook.api.models.tablemodels.BookModel;
import de.sunbook.api.processors.UserProcessor;
import de.sunbook.api.services.BookService;
import de.sunbook.api.services.SellerService;
import de.sunbook.api.utils.CustomRowMapper;


public class ApiStandardTests {

    //bookscontroller funktionen aufrufen und die response entity mit einer vorherigen erstellten vergleichen
    //sqlSeverConnection query, execute, getInstance
    //account controller authentificate getUserData
    //OrdersController ?
    //einfach nur ausführen ohne die response zu vergleichen       

    @MockBean
    @Autowired
    BooksController booksController = new BooksController();

    @MockBean
    @Autowired
    BookService bookService = new BookService();

    private BookQueryModel bookQueryModel = new BookQueryModel();
    
    //@Autowired 
    AuthenticationRequestModel authenticationRequestModel = new AuthenticationRequestModel();
    
    @Autowired
    @InjectMocks
    SellersController sellersController = new SellersController(); 


    @MockBean
    @Autowired
    @InjectMocks
    private SellerService sellerService = new SellerService();

    @MockBean
    private UserProcessor userProcessor = new UserProcessor();


    public AccountController accountController = new AccountController();


    @Autowired
    SqlServerConnection sqlServerConnection;


    @Autowired 
    private MockMvc mockMvc; 
       

    @MockBean 
    private SellerService sellerServiceMocked;

       

    @Test
    void searchBookByListTest() throws Exception{
        String testGenre = "romane";
        String testBinding = "Taschenbuch";
        Float testMaxPrice = (float) 10.0;
        Float testMinPrice = (float) 9.0;    
        Date ab = new Date();
        BookModel a = new BookModel(1, "genre", "title", "subtitle"," author","description", "picture", "isbn", "publisher", "language", ab, "binding", (float) 10.0);
        
        List<BookModel> c = booksController.searchBookByList(testGenre, testBinding, testMaxPrice, testMinPrice);
      //  when(booksController.searchBookByList(testGenre, testBinding, testMaxPrice, testMinPrice)).thenCallRealMethod();
        booksController.searchBookByList(testGenre, testBinding, testMaxPrice, testMinPrice);
       
        booksController.searchBookByList(null, null, null, null); 
        booksController.searchBookByList(null, null, testMaxPrice, testMinPrice);  
        booksController.searchBookByList(null, testBinding, testMaxPrice, testMinPrice);
        booksController.searchBookByList(testGenre, testBinding, testMaxPrice, testMinPrice);
        booksController.searchBookByList(testGenre, null, null, null);
        booksController.searchBookByList(null, testBinding, null, null);
        booksController.searchBookByList(testGenre, testBinding, null, null);
    }


    @Test
    void getSingleTest() throws Exception{
        int testID = 1;
        BookResponseSingleModel bookResponseModel = booksController.getSingle(testID);   
        /*
        BookResponseSingleModel a = new BookResponseSingleModel(1, "genre", "title", "subtitle", "author",
            "description", "picture", testISBN, "publisher", "language", new Date(System.currentTimeMillis()),
            "binding", (float)10.4, null);
    */

    }


    @Test
    void getSingleIsbnTest() throws Exception{
        String testISBN = "978-3-442-31448-5";
        
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        Date date = DateFor.parse("2021-05-24");

        BookModel testModel = new BookModel(1, "romane", "Die verschwundene Schwester", "Roman", "Lucinda Riley", 
        "Wer ist die geheimnisvolle verschwundene Schwester?Sieben Sterne umfasst das Sternbild der Plejaden, und die Schwestern d’Aplièse tragen ihre Namen. Stets war ihre siebte Schwester aber ein Rätsel für sie, denn Merope ist verschwunden, seit sie denken können. Eines Tages überbringt der Anwalt der Familie die verblüffende Nachricht, dass er eine Spur entdeckt hat: Ein Weingut in Neuseeland und die Zeichnung eines sternförmigen Rings weisen den Weg. Es beginnt eine Jagd quer über den Globus, denn Mary McDougal – die Frau, die als Einzige bestätigen kann, ob ihre Tochter Mary-Kate die verschwundene Schwester ist – befindet sich auf einer Weltreise. Während die Schwestern ihre Suche nach Neuseeland, Kanada, England, Frankreich und Irland führt, schlüpft ihnen Mary immer wieder durch die Finger. Und es scheint, als wolle sie unbedingt verhindern, gefunden zu werden …",
        "https://assets.thalia.media/img/artikel/30f20d721796708f7bf6f5c91fae544478e407fc-00-00.jpeg",
        testISBN, "Goldmann", "Deutsch", date, "gebundene Ausgabe", (float) 22.0);
       

        ResponseEntity<?> bookModel = booksController.getSingleIsbn(testISBN);

        assertThat(bookModel.getStatusCodeValue()).isEqualTo(200);
        assertThat(testModel.equals(bookModel.getBody()));
    }



    @Test
    void sellersControllerGetTest() throws Exception{
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        int id = 1;   
        sellersController.get(id);
        SellersController s = new SellersController();
        sellerService.getSeller(id);

        //when(sellerService.getSeller(id));
        //addEmployee(any(Employee.class))).thenReturn(true); ResponseEntity.ok(sellerService.getSeller(id));
        ResponseEntity<?> responseEntity = sellersController.get(1);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

    }


    @Test
    void createAuthenticationTokenTest() throws Exception{
        //noch ausfüllen, bei Account Controller gab es noch viel mehr aber die  
        authenticationRequestModel.setUsername("lukas.reichelt@edu.fhdw.de"); // brauche einen Usernamen und Passwort das richtig ist
        authenticationRequestModel.setPassword("12345678");
        //accountController.authenticationManager = darf nicht null sein; Zeile 65 und 41 in Account Controller
        accountController.createAuthenticationToken(authenticationRequestModel);
        ResponseEntity<?> responseEntity = accountController.createAuthenticationToken(authenticationRequestModel);
    }

    
    @Test
    void sqlConnectionTest() throws Exception{
        SqlServerConnection.getInstance();
    } //query


    @Test
    void queryTest() throws Exception{
        String testSql ="SELECT * FROM Book WHERE isbn = '978-3-442-31448-5'";
        SqlServerConnection.getInstance().query(testSql, CustomRowMapper.GetBookMapper()); //bad sql grammar
    } 
    


    

   
    
}
