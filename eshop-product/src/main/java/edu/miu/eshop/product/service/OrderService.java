package edu.miu.eshop.product.service;

import edu.miu.eshop.product.dto.CheckoutDto;
import edu.miu.eshop.product.dto.OrderItemDto;
import edu.miu.eshop.product.entity.*;

import java.util.List;

public interface OrderService {
    Order createOrder(ShoppingCart cart, String userName);
    void createGuestOrder(List<CartItem> items, String email);

    Order getOrder(String orderNumber);

    List<Order> getAllOrders(String userName);

    void updateOrder(Order oldOrder);

    void deleteOrder(String orderNumber);

    void checkout(CheckoutDto checkoutDto);

    List<OrderItemDto> getOrderByVendor(String vendorId);
}
