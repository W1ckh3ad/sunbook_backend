package de.sunbook.api.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import de.sunbook.api.models.requestmodels.RegisterRequestModel;
import de.sunbook.api.models.tablemodels.UserModel;
import de.sunbook.api.processors.UserProcessor;

@Service
public class UserService {
    @Autowired
    private UserProcessor userProcessor = new UserProcessor();

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserModel findUserByName(String username) throws SQLException {
        return userProcessor.selectUsername(username);
    }

    public void register(RegisterRequestModel req) throws SQLException, Exception {
        var possibleUser = findUserByName(req.getUsername());
        if (possibleUser != null) {
            throw new Exception("User is already registered");
        }

        var model = new UserModel();
        model.setEmail(req.getUsername());
        model.setPassword(passwordEncoder.encode(req.getPassword()));
        model.setFirstName(req.getFirstName());
        model.setLastName(req.getLastName());
        model.setStreet(req.getStreet());
        model.setPlz(req.getPlz());
        model.setCity(req.getCity());
        model.setHouseNum(req.getHouseNum());
        userProcessor.Register(model);
    }

    public List<UserModel> getAll() throws SQLException {
        return userProcessor.select();
    }

    public UserModel get(int id) throws SQLException {
        var model = userProcessor.select(id);
        if (model == null) {
            throw new NotFoundException("User doesnt Exist");
        }
        return model;
    }

    public void put(int id, UserModel model) throws SQLException, Exception {
        var user = findUserByName(model.getEmail());
        if (user.getUserId() != id) {
            throw new Exception("There already exitsts a User with this email adress");
        }
        model.setUserId(id);
        userProcessor.update(model);
    }

    public void patch(int id, UserModel model) throws SQLException, Exception {
        var oldModel = get(id);
        if (oldModel == null) {
            throw new NotFoundException("User does not exist");
        }
        var user = findUserByName(model.getEmail());
        if (user.getUserId() != id) {
            throw new Exception("There already exitsts a User with this email adress");
        }
        model.setUserId(id);
        userProcessor.update(model);
    }

    public UserModel post(UserModel model) throws SQLException, Exception {
        var user = findUserByName(model.getEmail());
        if (user != null) {
            throw new Exception("There already exitsts a User with this email adress");
        }
        var password = passwordEncoder.encode(model.getPassword());
        model.setPassword(password);
        var id = userProcessor.insert(model);
        model.setUserId(id);
        return model;
    }

    public void updateSelf(UserModel model, String username) throws SQLException, Exception {
        var user = findUserByName(username);
        int id = user.getUserId();
        var oldMail = user.getEmail();
        var newMail = model.getEmail();
        if (newMail != null && !oldMail.equals(newMail)) {
            var possibleUser = findUserByName(model.getEmail());

            if (possibleUser != null) {
                throw new Exception("There already exitsts a User with this email adress");
            }
        }
        model.setPassword(null);
        model.setRole(null);
        model.setUserId(id);
        userProcessor.update(model);
    }

    public void updatePassword(String password, String username) throws SQLException {
        int id = findUserByName(username).getUserId();
        String encoded = passwordEncoder.encode(password);
        var model = new UserModel();
        model.setUserId(id);
        model.setPassword(encoded);
        userProcessor.update(model);
    }

    public void updateRole(String role, String username) throws SQLException {
        UserModel oldModel = findUserByName(username);
        if (role.equals(oldModel.getRole()) || role.equals("admin")) {
            return;
        }
        if (role.equals("seller") || role.equals(null)) {
            var model = new UserModel();
            model.setUserId(oldModel.getUserId());
            model.setRole(role);
            userProcessor.update(model);
        }
    }

    public void delete(int id) throws SQLException {
        var oldModel = get(id);
        if (oldModel == null) {
            throw new NotFoundException("User does not exist");
        }
        userProcessor.delete(id);
    }
}
