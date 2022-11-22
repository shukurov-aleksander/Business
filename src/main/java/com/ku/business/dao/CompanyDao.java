package com.ku.business.dao;

import com.ku.business.entity.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CompanyDao implements Dao<Long, Company>{
    private final SessionFactory sessionFactory;

    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Company findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Company.class,id);
    }

    @Override
    public List<Company> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createNativeQuery("SELECT * FROM companies", Company.class).getResultList();
    }

    @Override
    public void save(Company company) {
       try (Session session = sessionFactory.getCurrentSession()
       ) {
            session.persist(company);
       }
    }

    @Override
    public void update(Company company) {
        try (Session session = sessionFactory.getCurrentSession()
        ) {
            session.merge(company);
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.getCurrentSession()
        ) {
            session.remove(id);
            session.flush();
        }
    }
}
