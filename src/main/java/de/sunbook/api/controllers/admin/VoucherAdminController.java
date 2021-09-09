package de.sunbook.api.controllers.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.sunbook.api.models.requestmodels.CreateVoucherRequestModel;
import de.sunbook.api.services.VoucherService;

@RestController
@RequestMapping("/api/admin/vouchers")
public class VoucherAdminController {

    @Autowired
    private VoucherService voucherService;

    @GetMapping
    private ResponseEntity<?> get() throws SQLException {
        
        return ResponseEntity.ok(voucherService.get());
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> get(@PathVariable int id) throws SQLException {
        return ResponseEntity.ok(voucherService.get(id));
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody CreateVoucherRequestModel request) throws Exception {
        var creator = SecurityContextHolder.getContext().getAuthentication().getName();
        var response = voucherService.createCredit(request, creator);

        return ResponseEntity.ok(response);
    }

}
