package com.ku.business.repository.hibernate;

import com.ku.business.HibernateUtil;
import com.ku.business.entity.Document;
import com.ku.business.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class DocumentRepository {
    public static final String FIND_BY_ID_QUERY = """
        FROM Document d
            LEFT JOIN FETCH d.order
        WHERE d.id = :id
    """;
    public static final String FIND_ALL_QUERY = "FROM Document";

    private final SessionFactory sessionFactory;

    public DocumentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Document findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(FIND_BY_ID_QUERY, Document.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception s) {
            throw new RepositoryException(String.format("Can't find document with id=%d!", id), s);
        }
    }

    public List<Document> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(FIND_ALL_QUERY, Document.class).list();
        } catch (Exception e) {
            throw new RepositoryException("Table documents is empty!", e);
        }
    }

    public void save(Document document) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.persist(document);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Failed to save document where id = %d!", document.getId()), e);
            }
        }
    }

    public void update(Document document) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.merge(document);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Can't update document with id=%d. This document is not exist!", document.getId()), e);
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
                throw new RepositoryException(String.format("Can't delete document with id=%d. This document is not exist!", id), e);
            }
        }
    }
}
