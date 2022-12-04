package com.ku.business.repository.hibernate;

import com.ku.business.entity.Order;
import com.ku.business.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class OrderRepository {
    public static final String FIND_BY_ID_QUERY = """
       FROM Order o
           LEFT JOIN FETCH o.contents
       WHERE o.id = :id
    """;
    public static final String FIND_ALL_QUERY = "FROM Order";
    public static final String INSERT_QUERY = """
        INSERT INTO orders (order_status, created_at_utc, completed_at_utc) 
        VALUES(cast(:orderStatus AS order_status_enum), :created_at_utc, :completed_at_utc) 
    """;
    public static final String UPDATE_QUERY = """
        UPDATE orders
        SET order_status = cast(:orderStatus AS order_status_enum), created_at_utc = :created_at_utc, completed_at_utc = :completed_at_utc
        WHERE id = :id
    """;
    private final SessionFactory sessionFactory;

    public OrderRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Order findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_BY_ID_QUERY, Order.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't find order with id=%d!", id), e);
        }
    }

    public List<Order> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_ALL_QUERY, Order.class)
                    .list();
        } catch (Exception e) {
            throw new RepositoryException("Table orders is empty!", e);
        }
    }

    public void save(Order order) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                makeQueryForInsertOrUpdateCompanies(order, INSERT_QUERY, session)
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Failed to save order where id = %d!", order.getId()), e);
            }
        }
    }

    public NativeQuery<Order> makeQueryForInsertOrUpdateCompanies(Order order, String query, Session session) {
        return session.createNativeQuery(query, Order.class)
                .setParameter("orderStatus", order.getOrderStatus().toString())
                .setParameter("created_at_utc", order.getCreatedAtUtc())
                .setParameter("completed_at_utc", order.getCompletedAtUtc());
    }

    public void update(Order order) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                makeQueryForInsertOrUpdateCompanies(order, UPDATE_QUERY, session)
                        .setParameter("id", order.getId())
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(
                        String.format("Can't update order with id=%d. This order is not exist!", order.getId()), e);
            }
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Order order = session.getReference(Order.class, id);
                session.remove(order);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(
                        String.format("Can't delete order with id=%d. This order is not exist!", id), e);
            }
        }
    }
}
