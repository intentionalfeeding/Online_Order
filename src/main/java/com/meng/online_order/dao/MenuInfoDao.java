package com.meng.online_order.dao;

import com.meng.online_order.entity.MenuItem;
import com.meng.online_order.entity.Restaurant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuInfoDao {

    private SessionFactory sessionFactory;

    @Autowired
    public MenuInfoDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Restaurant> getRestaurants(){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(Restaurant.class);
            query.from(Restaurant.class);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (session != null){
                session.close();
            }
        }

    }

    public List<MenuItem> getAllMenuItem(int restaurantId){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            Restaurant restaurant = session.get(Restaurant.class, restaurantId);
            if (restaurant != null){
                return restaurant.getMenuItemList();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null){
                session.close();
            }
        }

        return new ArrayList<>();

    }

    public MenuItem getMenuItem(int menuItemId){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            return session.get(MenuItem.class, menuItemId);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null){
                session.close();
            }
        }
        return null;

    }


}
