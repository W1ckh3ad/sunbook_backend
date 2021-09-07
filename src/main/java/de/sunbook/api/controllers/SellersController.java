package de.sunbook.api.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.sunbook.api.services.SellerService;

@RestController
@RequestMapping("/api/sellers")
public class SellersController {
    @Autowired
    private SellerService sellerService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getSeller(@PathVariable int id) throws SQLException {
        return ResponseEntity.ok(sellerService.getSeller(id));
    }
}
