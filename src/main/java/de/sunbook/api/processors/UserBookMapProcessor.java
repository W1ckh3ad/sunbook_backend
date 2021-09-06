package de.sunbook.api.processors;

import java.sql.SQLException;
import java.util.List;

import de.sunbook.api.models.UserBookMapModel;
import de.sunbook.api.processors.abstracts.Processor;
import de.sunbook.api.processors.interfaces.ICrudProcessor;

public class UserBookMapProcessor extends Processor implements ICrudProcessor<UserBookMapModel> {
    public UserBookMapProcessor() {
        super("UserMapBook");
    }

    @Override
    public void Delete(int uid) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public List<UserBookMapModel> Get() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserBookMapModel Get(int uid) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int Post(UserBookMapModel model) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void Put(UserBookMapModel model) throws SQLException {
        // TODO Auto-generated method stub

    }
}
