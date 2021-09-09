package de.sunbook.api.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.sunbook.api.models.requestmodels.OrderRequestModel;
import de.sunbook.api.models.responsemodels.BookResponseModel;
import de.sunbook.api.models.responsemodels.OrderResponseModel;
import de.sunbook.api.models.tablemodels.ExtraProductModel;
import de.sunbook.api.models.tablemodels.OrderModel;
import de.sunbook.api.models.tablemodels.OrderPartModel;
import de.sunbook.api.models.tablemodels.UserModel;
import de.sunbook.api.processors.OrderPartProcessor;
import de.sunbook.api.processors.OrderProcesor;

@Service
public class OrderService {

    @Autowired
    private VoucherService voucherService;
    @Autowired
    private BookService bookService;
    @Autowired
    private OrderProcesor orderProcesor;
    @Autowired
    private OrderPartProcessor orderPartProcessor;
    @Autowired
    private UserService userService;

    public List<OrderResponseModel> get() {
        return null;
    }

    public OrderResponseModel get(int id) {
        return null;
    }

    public OrderResponseModel post(OrderRequestModel model, String username) throws Exception, SQLException {
        var vouchersToUseList = new ArrayList<ExtraProductModel>();
        var booksToBuy = new ArrayList<BookResponseModel>();
        var codesToBuy = new ArrayList<ExtraProductModel>();
        var user = userService.findUserByName(username);
        float value = 0;
        if (model.getVoucherCodes() != null) {
            for (var voucherCode : model.getVoucherCodes()) {
                var voucherToUser = voucherService.validateVoucher(voucherCode, username);
                vouchersToUseList.add(voucherToUser);
                value -= voucherToUser.getPrice();
            }

        }

        if (model.getBooks() != null) {
            for (var book : model.getBooks()) {
                var bookToBuy = bookService.get(book.getSellerId(), book.getBookId());
                var owner = new UserModel();
                owner.setUserId(book.getSellerId());
                bookToBuy.setOwner(owner);

                booksToBuy.add(bookToBuy);
                value += bookToBuy.getPrice();
            }
        }
        if (model.getVouchers() != null) {
            for (var voucher : model.getVouchers()) {
                value += voucher.getValue();
            }
        }

        if (value < 0) {
            throw new Exception("The value of the order can't be below 0 (including vouchers)");
        }

        var order = new OrderModel();
        order.setPaymentMethod(model.getPaymentMethod());
        order.setValue(value);
        order.setUserId(user.getUserId());
        var orderId = orderProcesor.insert(order);
        order.setId(orderId);

        for (var book : booksToBuy) {
            var orderPartModel = new OrderPartModel();
            orderPartModel.setBookId(book.getUid());
            orderPartModel.setUserId(book.getOwner().getUserId());
            orderPartModel.setOrderId(orderId);
            orderPartProcessor.insert(orderPartModel);

        }
        if (model.getVouchers() != null) {
            for (var voucher : model.getVouchers()) {
                var voucherModel = voucherService.createCredit(voucher, user);
                codesToBuy.add(voucherModel);
            }
        }
        var usedAt = new Date();
        for (ExtraProductModel extraProductModel : vouchersToUseList) {
            extraProductModel.setUsedAt(usedAt);
            extraProductModel.setUsedIn(orderId);
            voucherService.use(extraProductModel);
        }

        var returnModel = new OrderResponseModel();
        returnModel.setId(orderId);
        returnModel.setUserId(user.getUserId());
        returnModel.setBooks(booksToBuy);
        returnModel.setCodes(codesToBuy);
        returnModel.setValue(value);
        return returnModel;
    }
}
