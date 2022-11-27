package com.ku.business.dao;

import com.ku.business.HibernateUtil;
import com.ku.business.entity.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CompanyDao {
    public static final String FIND_BY_ID_QUERY = """
        SELECT DISTINCT c
        FROM Company c
            LEFT JOIN FETCH c.storages
            LEFT JOIN FETCH c.details
        WHERE c.id = :id
    """;

    private final SessionFactory sessionFactory;

    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Company findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()
        ) {
            return session.createQuery(FIND_BY_ID_QUERY, Company.class).setParameter("id",id).getSingleResult();
        }
    }

    public List<Company> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()
        ) {
            return session.createQuery("from Company", Company.class).list();
        }
    }

    public void save(Company company) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.persist(company);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(Company company) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.merge(company);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.remove(id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
