package de.sunbook.api.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.sunbook.api.models.requestmodels.AddBookToSellRequestModel;
import de.sunbook.api.models.requestmodels.AuthenticationRequestModel;
import de.sunbook.api.models.requestmodels.RegisterRequestModel;
import de.sunbook.api.models.requestmodels.UpdatePasswordRequestModel;
import de.sunbook.api.models.requestmodels.UpdateRoleRequestModel;
import de.sunbook.api.models.responsemodels.AuthenticationResponseModel;
import de.sunbook.api.models.tablemodels.UserModel;
import de.sunbook.api.services.BookService;
import de.sunbook.api.services.MyUserDetailsService;
import de.sunbook.api.services.OrderService;
import de.sunbook.api.services.UserService;
import de.sunbook.api.utils.JwtUtil;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    
    @Autowired
    private AuthenticationManager authenticationManager = new AuthenticationManager(){
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            return null;
        }      
    };

    @Autowired
    private MyUserDetailsService userDetailsService = new MyUserDetailsService();

    @Autowired
    private JwtUtil jwtTokenUtil = new JwtUtil();

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody(required = true) AuthenticationRequestModel request)
            throws Exception {
        try {
            if (request == null) {
                throw new Exception("Request body missing");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                    request.getPassword(), new ArrayList<>()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or passowrd", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponseModel(jwt));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody(required = true) RegisterRequestModel request) throws Exception {
        if (request == null) {
            throw new Exception("Request body missing");
        }
        userService.register(request);
        return ResponseEntity.ok("User " + request.getUsername() + " has registered");
    }

    @GetMapping()
    public ResponseEntity<?> getUserData() throws SQLException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(userService.findUserByName(username));
    }

    @GetMapping("/books")
    public ResponseEntity<?> getOwnBooks() throws SQLException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(bookService.getBooksForUser(username));
    }

    @PostMapping("/books")
    public ResponseEntity<?> postBookToSell(@RequestBody(required = true) AddBookToSellRequestModel model)
            throws SQLException, Exception {
        if (model == null) {
            throw new Exception("Request body missing");
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        bookService.postBookToSell(username, model);
        return ResponseEntity.ok("Book with id (" + model.getBookId() + ") has been added to your product list");
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBookToSell(@PathVariable int id) throws SQLException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(bookService.getBookToSell(username, id));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBookToSell(@PathVariable int id) throws SQLException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        bookService.deleteBookToSell(username, id);
        return ResponseEntity.ok("Book with id (" + id + ") has been removed from your product list");
    }

    @PatchMapping("/books/{id}")
    public ResponseEntity<?> patchBookToSell(@PathVariable int id,
            @RequestBody(required = true) AddBookToSellRequestModel model) throws SQLException, Exception {
        if (model == null) {
            throw new Exception("Request body missing");
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        bookService.updateBookToSell(username, id, model);
        return ResponseEntity.ok("Book with id (" + id + ") has been updated in your product list");
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<?> putBookToSell(@PathVariable int id,
            @RequestBody(required = true) AddBookToSellRequestModel model) throws SQLException, Exception {
        if (model == null) {
            throw new Exception("Request body missing");
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        bookService.updateBookToSell(username, id, model);
        return ResponseEntity.ok("Book with id (" + id + ") has been updated in your product list");
    }

    @PatchMapping()
    public ResponseEntity<?> patch(@RequestBody(required = true) UserModel model) throws SQLException, Exception {
        if (model == null) {
            throw new Exception("Request body missing");
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.updateSelf(model, username);
        return ResponseEntity.ok("Account has been updated");
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<?> updatePassword(@RequestBody(required = true) UpdatePasswordRequestModel model)
            throws SQLException, Exception {
        if (model == null) {
            throw new Exception("Request body missing");
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.updatePassword(model.getPassword(), username);
        return ResponseEntity.ok("Password has been updated");
    }

    @PatchMapping("/changeRole")
    public ResponseEntity<?> updateRole(@RequestBody(required = true) UpdateRoleRequestModel model)
            throws SQLException, Exception {
        if (model == null) {
            throw new Exception("Request body missing");
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.updatePassword(model.getRole(), username);
        return ResponseEntity.ok("Role has been updated");
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders() throws SQLException{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(orderService.get(username));
    }

}
