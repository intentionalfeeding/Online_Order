package com.meng.online_order.service;

import com.meng.online_order.dao.MenuInfoDao;
import com.meng.online_order.entity.MenuItem;
import com.meng.online_order.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuInfoService {
    private MenuInfoDao menuInfoDao;

    @Autowired
    public MenuInfoService(MenuInfoDao menuInfoDao) {
        this.menuInfoDao = menuInfoDao;
    }

    public List<Restaurant> getRestaurants(){
        return menuInfoDao.getRestaurants();
    }

    public List<MenuItem> getAllMenuItem(int RestaurantId){
        return menuInfoDao.getAllMenuItem(RestaurantId);
    }

    public MenuItem getMenuItem(int id){
        return menuInfoDao.getMenuItem(id);
    }
}
