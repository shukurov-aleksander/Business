package com.ku.business.repository.hibernate;

import com.ku.business.entity.Content;
import com.ku.business.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class ContentRepository {
    public static final String FIND_BY_ID_QUERY = """
        FROM Content c
            LEFT JOIN FETCH c.orders
            LEFT JOIN FETCH c.service
        WHERE c.id = :id
    """;
    public static final String FIND_ALL_QUERY = "FROM Content";
    public static final String INSERT_QUERY = """
        INSERT INTO contents (quantity, service_id)
        VALUES(:quantity, :serviceId)
    """;
    public static final String UPDATE_QUERY = """
        UPDATE contents
        SET quantity = :quantity, service_id = :serviceId
        WHERE id = :id
    """;
    private final SessionFactory sessionFactory;

    public ContentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Content findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_BY_ID_QUERY, Content.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't find content with id=%d!", id), e);
        }
    }

    public List<Content> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_ALL_QUERY, Content.class)
                    .list();
        } catch (Exception e) {
            throw new RepositoryException("Table content is empty!", e);
        }
    }

    public void save(Content content) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                makeQueryForInsertOrUpdateCompanies(content, INSERT_QUERY, session)
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(
                        String.format("Failed to save content where id = %d!",
                                content.getId()), e);
            }
        }
    }

    public NativeQuery<Content> makeQueryForInsertOrUpdateCompanies(Content content, String query, Session session) {
        return session.createNativeQuery(query, Content.class)
                .setParameter("quantity", content.getQuantity())
                .setParameter("serviceId", content.getService().getId());
    }

    public void update(Content content) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                makeQueryForInsertOrUpdateCompanies(content, UPDATE_QUERY, session)
                        .setParameter("id", content.getId())
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(
                        String.format("Can't update content with id=%d. This content is not exist!",
                                content.getId()), e);
            }
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Content content = session.getReference(Content.class, id);
                session.remove(content);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(
                        String.format("Can't delete content with id=%d. This content is not exist!", id), e);
            }
        }
    }
}
