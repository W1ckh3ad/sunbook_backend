package de.sunbook.api.services;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.sunbook.api.models.requestmodels.CreateVoucherRequestModel;
import de.sunbook.api.models.tablemodels.ExtraProductModel;
import de.sunbook.api.models.tablemodels.UserModel;
import de.sunbook.api.processors.ExtraProductProcessor;

@Service
public class VoucherService {
    @Autowired
    private UserService userService;

    @Autowired
    private ExtraProductProcessor extraProductProcessor;

    public List<ExtraProductModel> get() throws SQLException {
        return extraProductProcessor.select();
    }

    public ExtraProductModel get(int id) throws SQLException {
        return extraProductProcessor.select(id);
    }

    public ExtraProductModel validateVoucher(String code, String username) throws SQLException, Exception {
        var user = userService.findUserByName(username);
        var model = extraProductProcessor.select(code);
        var receiver = model.getReceiver();
        if (model == null || model.getUsedAt() != null || (receiver != null && receiver != user.getUserId())) {
            throw new Exception("Code isnt Valid");
        }
        return model;
    }

    public void use(ExtraProductModel model) throws SQLException {
        extraProductProcessor.update(model);
    }

    public ExtraProductModel createCredit(CreateVoucherRequestModel request, String creator) throws Exception {
        return post(request, creator, "Gutschrift");
    }

    public ExtraProductModel createCredit(CreateVoucherRequestModel request, UserModel creator) throws Exception {
        return post(request, creator, "Gutschrift");
    }

    public ExtraProductModel createGif(CreateVoucherRequestModel request, String creator) throws Exception {
        return post(request, creator, "Geschenkgutschein");
    }

    private ExtraProductModel post(CreateVoucherRequestModel request, String creator, String productName)
            throws Exception {
        var creatorModel = userService.findUserByName(creator);
        return post(request, creatorModel, productName);
    }

    private ExtraProductModel post(CreateVoucherRequestModel request, UserModel creator, String productName)
            throws Exception {
        var id = request.getReceiverId();
        var mail = request.getReceiverEmail();
        var value = request.getValue();
        if (id == null && mail == null) {
            throw new Exception("Invalid Request Body");
        }
        if (id == null && mail != null) {
            var user = userService.findUserByName(mail);
            id = user.getUserId();
        }
        var created = new Date();
        String codedString = id.toString() + String.valueOf(created.getTime()) + String.valueOf(value);
        var model = new ExtraProductModel();
        model.setCreatedAt(created);
        model.setCreator(creator.getUserId());
        model.setPrice(value);
        model.setProductCode(codedString);
        model.setProductName("Gutschein");
        model.setProductDescription(request.getDescription());
        model.setReceiver(id);
        var voucherId = extraProductProcessor.insert(model);
        model.setId(voucherId);
        return model;
    }
}
