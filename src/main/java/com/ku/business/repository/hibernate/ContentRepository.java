package com.ku.business.repository.hibernate;

import com.ku.business.HibernateUtil;
import com.ku.business.entity.Content;
import com.ku.business.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ContentRepository {
    public static final String FIND_BY_ID_QUERY = """
        FROM Content c
            LEFT JOIN FETCH c.orders
            LEFT JOIN FETCH c.service
        WHERE c.id = :id
    """;
    public static final String FIND_ALL_QUERY = "FROM Content";

    private final SessionFactory sessionFactory;

    public ContentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Content findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(FIND_BY_ID_QUERY, Content.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception s) {
            throw new RepositoryException(String.format("Can't find content with id=%d!", id), s);
        }
    }

    public List<Content> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(FIND_ALL_QUERY, Content.class).list();
        } catch (Exception e) {
            throw new RepositoryException("Table content is empty!", e);
        }
    }

    public void save(Content content) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.persist(content);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Failed to save content where id = %d!", content.getId()), e);
            }
        }
    }

    public void update(Content content) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.merge(content);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Can't update content with id=%d. This content is not exist!", content.getId()), e);
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
                throw new RepositoryException(String.format("Can't delete content with id=%d. This company is not exist!", id), e);
            }
        }
    }
}
