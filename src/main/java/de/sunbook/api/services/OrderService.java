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

    public List<OrderResponseModel> get() throws SQLException {
        var orders = orderProcesor.select();
        var list = new ArrayList<OrderResponseModel>();
        for (OrderModel orderModel : orders) {
            list.add(get(orderModel.getId()));
        }
        return list;
    }

    public OrderResponseModel get(int id) throws SQLException {
        var orderModel = orderProcesor.select(id);
        return get(orderModel);
    }

    public List<OrderResponseModel> get(String username) throws SQLException {
        var user = userService.findUserByName(username);
        var orders = orderProcesor.selectUserId(user.getUserId());
        var list = new ArrayList<OrderResponseModel>();
        for (OrderModel orderModel : orders) {
            list.add(get(orderModel.getId()));
        }
        return list;
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
                var voucherModel = voucherService.createGift(voucher, user);
                var orderPart = new OrderPartModel();
                orderPart.setExtraProductId(voucherModel.getId());
                orderPart.setOrderId(orderId);
                orderPartProcessor.insert(orderPart);
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
        returnModel.setPaymentMethod(order.getPaymentMethod());
        return returnModel;
    }

    private OrderResponseModel get(OrderModel orderModel) throws SQLException {

        var model = new OrderResponseModel();
        model.setId(orderModel.getId());
        var books = new ArrayList<BookResponseModel>();
        var boughtVouchers = new ArrayList<ExtraProductModel>();
        var orderParts = orderPartProcessor.selectOrderId(orderModel.getId());
        for (var part : orderParts) {
            var bookId = part.getBookId();
            var userId = part.getUserId();
            var voucherId = part.getExtraProductId();
            if (bookId != null && userId != null && bookId != 0 && userId != 0) {
                books.add(bookService.get(part.getUserId(), part.getBookId()));
            }
            if (voucherId != null && voucherId != 0) {
                boughtVouchers.add(voucherService.get(voucherId));
            }
        }

        var usedCodes = voucherService.getUsedForOrderId(orderModel.getId());
        model.setBooks(books);
        model.setCodes(boughtVouchers);
        model.setUsedCodes(usedCodes);
        return model;
    }

}
