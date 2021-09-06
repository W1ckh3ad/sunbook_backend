package de.sunbook.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.sunbook.api.models.AuthenticationResponseModel;
import de.sunbook.api.models.AuthenticationRequestModel;
import de.sunbook.api.services.MyUserDetailsService;
import de.sunbook.api.utils.JwtUtil;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

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

    @GetMapping(value = "/")
    public ResponseEntity<?> getUserData() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(auth);
    }

    @GetMapping(value = "/test/jwt")
    public ResponseEntity<?> getMethodName(@RequestParam String jwt) {
        return ResponseEntity.ok(jwtTokenUtil.extractUsername(jwt));
    }

}
