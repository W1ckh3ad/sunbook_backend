package de.sunbook.api.processors;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import de.sunbook.api.models.tablemodels.ExtraProductModel;
import de.sunbook.api.processors.abstracts.Processor;
import de.sunbook.api.processors.interfaces.ICrudProcessor;
import de.sunbook.api.utils.sqlstringbuilder.ExtraProductSqlStringBuilder;

@Service
public class ExtraProductProcessor extends Processor implements ICrudProcessor<ExtraProductModel> {
    ExtraProductSqlStringBuilder sqlStringBuilder;
    public ExtraProductProcessor() {
        super("ExtraProduct");
        sqlStringBuilder = new ExtraProductSqlStringBuilder(table);
    }
    @Override
    public void delete(int uid) throws SQLException {
        // TODO Auto-generated method stub
        
    }
    @Override
    public int insert(ExtraProductModel model) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public List<ExtraProductModel> select() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public ExtraProductModel select(int uid) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void update(ExtraProductModel model) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    
}
