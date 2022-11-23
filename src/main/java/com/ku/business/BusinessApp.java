package com.ku.business;

import com.ku.business.entity.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BusinessApp {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
           // Company company = new Company(1000L, "Company name №1000 .inc", "0000000000001000", true, 523L, null, null);
            Company company = new Company(1001L, "Eneka", "0000000000001001", true, 523L, null, null);
            //session.persist(company);
           session.persist(company);
           //session.remove(company);
            session.getTransaction().commit();
        }
    }
}
