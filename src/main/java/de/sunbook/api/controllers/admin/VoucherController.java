package de.sunbook.api.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.sunbook.api.models.requestmodels.CreateVoucherRequestModel;
import de.sunbook.api.models.responsemodels.CreateVoucherResponseModel;

@RestController
@RequestMapping("/api/admin/voucher")
public class VoucherController {

    @PostMapping
    public ResponseEntity<?> post(@RequestBody CreateVoucherRequestModel request) {
        var response = new CreateVoucherResponseModel();

        return ResponseEntity.ok(response);
    }

}
