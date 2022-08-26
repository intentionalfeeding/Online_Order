package com.meng.online_order.service;

import com.meng.online_order.dao.CartDao;
import com.meng.online_order.entity.Cart;
import com.meng.online_order.entity.Customer;
import com.meng.online_order.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private CustomerService customerService;

    private CartDao cartDao;

    @Autowired
    public CartService(CustomerService customerService, CartDao cartDao) {
        this.customerService = customerService;
        this.cartDao = cartDao;
    }

    public Cart getCart(){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Customer customer = customerService.getCustomer(username);

        if (customer != null) {
            Cart cart = customer.getCart();
            double totalPrice = 0;
            for (OrderItem item:cart.getOrderItemList()){
                totalPrice += item.getPrice()*item.getQuantity();
            }
            cart.setTotalPrice(totalPrice);
            return cart;
        }

        return new Cart();
    }

    public void cleanCart(){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Customer customer = customerService.getCustomer(username);
        if (customer != null) cartDao.removeAllCartItems(customer.getCart());
    }
}
