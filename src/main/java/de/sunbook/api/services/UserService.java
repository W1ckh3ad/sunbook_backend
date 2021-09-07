package de.sunbook.api.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.sunbook.api.models.requestmodels.RegisterRequestModel;
import de.sunbook.api.models.tablemodels.UserModel;
import de.sunbook.api.processors.UserProcessor;

@Service
public class UserService {
    @Autowired
    private UserProcessor userProcessor;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserModel findUserByName(String username) throws SQLException {
        return userProcessor.getUsername(username);
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
        return userProcessor.get();
    }

    public UserModel get(int id) throws SQLException {
        return userProcessor.get(id);
    }

    public void put(int id, UserModel model) throws SQLException {
        model.setUserId(id);
        userProcessor.put(model);
    }

    public void post(UserModel model) throws SQLException {
        userProcessor.post(model);
    }

    public void updateSelf(UserModel model, String username) throws SQLException {
        int id = userProcessor.getUsername(username).getUserId();
        model.setUserId(id);
        userProcessor.put(model);
    }

}
