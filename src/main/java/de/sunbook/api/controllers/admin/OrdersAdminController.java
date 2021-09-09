package de.sunbook.api.controllers.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.sunbook.api.services.OrderService;

@RestController
@RequestMapping("/api/admin/orders")
public class OrdersAdminController {

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public ResponseEntity<?> get() throws SQLException {
        return ResponseEntity.ok(orderService.get());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getSingle(@PathVariable int id) throws SQLException {
        return ResponseEntity.ok(orderService.get(id));
    }
}
