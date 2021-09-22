package de.sunbook.api.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.sunbook.api.models.requestmodels.OrderRequestModel;
import de.sunbook.api.services.OrderService;
import de.sunbook.api.services.VoucherService;

/*
This class defines the Controller for new orders and to validate the vouchers 
*/
@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private OrderService orderService;


    @PostMapping
    public ResponseEntity<?> post(@RequestBody OrderRequestModel request) throws Exception, SQLException {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var response = orderService.post(request, username);
        return ResponseEntity.ok(response);
    }

    //validates the voucher
    @GetMapping("/validateVoucher")
    public ResponseEntity<?> validateVoucher(@RequestParam String code) throws SQLException, Exception {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        voucherService.validateVoucher(code, username);
        return ResponseEntity.ok("code " + code + " is valid");
    }
}
