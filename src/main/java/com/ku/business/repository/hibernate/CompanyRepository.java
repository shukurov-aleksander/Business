package com.ku.business.repository.hibernate;

import com.ku.business.HibernateUtil;
import com.ku.business.entity.Company;
import com.ku.business.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CompanyRepository {
    public static final String FIND_BY_ID_QUERY = """
        FROM Company c
            LEFT JOIN FETCH c.storages
            LEFT JOIN FETCH c.details
        WHERE c.id = :id
    """;
    public static final String FIND_ALL_QUERY = "FROM Company";

    private final SessionFactory sessionFactory;

    public CompanyRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Company findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(FIND_BY_ID_QUERY, Company.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception s) {
            throw new RepositoryException(String.format("Can't find company with id=%d!", id), s);
        }
    }

    public List<Company> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Company> companies = session.createQuery(FIND_ALL_QUERY, Company.class).list();
            return companies;
        } catch (Exception e) {
            throw new RepositoryException("Table companies is empty!", e);
        }
    }

    public void save(Company company) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.persist(company);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Failed to save Company where id = %d! Company with tax number=%s already exist.", company.getId(),company.getTaxNumber()), e);
            }
        }
    }

    public void update(Company company) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.merge(company);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Can't update company with id=%d. This company is not exist!", company.getId()), e);
            }
        }
    }

    public void delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.remove(findById(id));
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Can't delete company with id=%d. This company is not exist!", id), e);
            }
        }
    }
}
