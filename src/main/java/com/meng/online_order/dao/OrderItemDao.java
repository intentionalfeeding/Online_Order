package com.meng.online_order.dao;

import com.meng.online_order.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDao {

    private SessionFactory sessionFactory;

    @Autowired
    public OrderItemDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(OrderItem orderItem){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(orderItem);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            if (session != null) session.getTransaction().rollback();
        } finally {
            if (session != null){
                session.close();
            }
        }

    }
}
