package com.ku.business.repository.hibernate;

import com.ku.business.HibernateUtil;
import com.ku.business.entity.Document;
import com.ku.business.entity.Order;
import com.ku.business.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OrderRepository {
    public static final String FIND_BY_ID_QUERY = """
       FROM Order o
           LEFT JOIN FETCH o.contents
       WHERE o.id = :id
    """;
    public static final String FIND_ALL_QUERY = "FROM Order";

    private final SessionFactory sessionFactory;

    public OrderRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Order findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(FIND_BY_ID_QUERY, Order.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception s) {
            throw new RepositoryException(String.format("Can't find order with id=%d!", id), s);
        }
    }

    public List<Order> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(FIND_ALL_QUERY, Order.class).list();
        } catch (Exception e) {
            throw new RepositoryException("Table orders is empty!", e);
        }
    }

    public void save(Order order) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.persist(order);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Failed to save order where id = %d!", order.getId()), e);
            }
        }
    }

    public void update(Order order) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.merge(order);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Can't update order with id=%d. This order is not exist!", order.getId()), e);
            }
        }
    }

    public void delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.remove(id);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Can't delete order with id=%d. This order is not exist!", id), e);
            }
        }
    }
}
