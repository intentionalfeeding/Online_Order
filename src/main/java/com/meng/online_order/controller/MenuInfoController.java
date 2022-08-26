package com.meng.online_order.controller;

import com.meng.online_order.entity.MenuItem;
import com.meng.online_order.entity.Restaurant;
import com.meng.online_order.service.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuInfoController {

    private MenuInfoService menuInfoService;

    @Autowired
    public MenuInfoController(MenuInfoService menuInfoService) {
        this.menuInfoService = menuInfoService;
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    @ResponseBody
    public List<Restaurant> getRestaurant(){
        return menuInfoService.getRestaurants();

    }

    @RequestMapping(value = "/restaurant/{restaurantId}/menu",method= RequestMethod.GET)
    @ResponseBody
    public List<MenuItem> getMenus(@PathVariable("restaurantId") int resaurantId){
        return menuInfoService.getAllMenuItem(resaurantId);
    }
}
