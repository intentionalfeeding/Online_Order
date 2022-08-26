package com.meng.online_order.controller;

import com.meng.online_order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CheckoutController {

    private CartService cartService;

    @Autowired
    public CheckoutController(CartService cartService) {
        this.cartService = cartService;
    }


    @RequestMapping(value ="/checkout",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void checkout(){
        cartService.cleanCart();
    }
}
