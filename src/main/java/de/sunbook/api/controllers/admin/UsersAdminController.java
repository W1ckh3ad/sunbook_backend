package de.sunbook.api.controllers.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.sunbook.api.models.tablemodels.UserModel;
import de.sunbook.api.services.UserService;

/*
This class defines the Controller for the Admin to add, change, finde or delete user
*/
@RestController
@RequestMapping("/api/admin/users")
public class UsersAdminController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> get() throws SQLException {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getSingle(@PathVariable int id) throws SQLException {
        return ResponseEntity.ok(userService.get(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@PathVariable int id, @RequestBody(required = true) UserModel model)
            throws SQLException, Exception {
        if (model == null) {
            throw new Exception("Request body missing");
        }
        userService.put(id, model);
        return ResponseEntity.ok("User " + model.getEmail() + " has been updated");
    }

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody(required = true) UserModel model) throws SQLException, Exception {
        if (model == null) {
            throw new Exception("Request body missing");
        }
        var returnModel = userService.post(model);
        return ResponseEntity.ok(returnModel);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws SQLException {
        userService.delete(id);
        return ResponseEntity.ok("User " + id + " has been removed");
    }

}
