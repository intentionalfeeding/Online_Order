package com.meng.online_order.service;

import com.meng.online_order.dao.OrderItemDao;
import com.meng.online_order.entity.Customer;
import com.meng.online_order.entity.MenuItem;
import com.meng.online_order.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    private MenuInfoService menuInfoService;

    private CustomerService customerService;

    private OrderItemDao orderItemDao;

    @Autowired
    public OrderItemService(MenuInfoService menuInfoService, CustomerService customerService, OrderItemDao orderItemDao) {
        this.menuInfoService = menuInfoService;
        this.customerService = customerService;
        this.orderItemDao = orderItemDao;
    }

    public void saveOrderItem(int menuId){
        final OrderItem orderItem = new OrderItem();
        final MenuItem menuItem = menuInfoService.getMenuItem(menuId);

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Customer customer = customerService.getCustomer(username);

        orderItem.setMenuItem(menuItem);
        orderItem.setCart(customer.getCart());
        orderItem.setQuantity(1);
        orderItem.setPrice(menuItem.getPrice());
        orderItemDao.save(orderItem);

    }


}
