package de.sunbook.api.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.sunbook.api.models.requestmodels.AuthenticationRequestModel;
import de.sunbook.api.models.requestmodels.RegisterRequestModel;
import de.sunbook.api.models.responsemodels.AuthenticationResponseModel;
import de.sunbook.api.models.tablemodels.UserModel;
import de.sunbook.api.services.BookService;
import de.sunbook.api.services.MyUserDetailsService;
import de.sunbook.api.services.UserService;
import de.sunbook.api.utils.JwtUtil;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestModel request)
            throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                    request.getPassword(), new ArrayList<>()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or passowrd", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponseModel(jwt));
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

    @PutMapping()
    public ResponseEntity<?> updateUserDate(@RequestBody UserModel model) throws SQLException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.updateSelf(model, username);
        return ResponseEntity.ok("Account has been updated");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody RegisterRequestModel request) throws Exception {
        userService.register(request);
        return ResponseEntity.ok("User " + request.getUsername() + " has registered");
    }

}
