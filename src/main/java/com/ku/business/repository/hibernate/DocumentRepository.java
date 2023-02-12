package com.ku.business.repository.hibernate;

import com.ku.business.entity.Document;
import com.ku.business.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentRepository {
    public static final String FIND_BY_ID_QUERY = """
        FROM Document d
            LEFT JOIN FETCH d.order
        WHERE d.id = :id
    """;
    public static final String FIND_ALL_QUERY = "FROM Document";
    public static final String INSERT_QUERY = """
        INSERT INTO documents (order_id, document_content) 
        VALUES(:orderId, :documentContent) 
    """;
    public static final String UPDATE_QUERY = """
        UPDATE documents
        SET order_id = :orderId, document_content = :documentContent  
        WHERE id = :id
    """;
    private final SessionFactory sessionFactory;

    public DocumentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Document findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_BY_ID_QUERY, Document.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't find document with id=%d!", id), e);
        }
    }

    public List<Document> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_ALL_QUERY, Document.class)
                    .list();
        } catch (Exception e) {
            throw new RepositoryException("Table documents is empty!", e);
        }
    }

    public void save(Document document) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                makeQueryForInsertOrUpdateCompanies(document, INSERT_QUERY, session)
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(
                        String.format("Failed to save document where id = %d!", document.getId()), e);
            }
        }
    }

    public NativeQuery<Document> makeQueryForInsertOrUpdateCompanies(Document document, String query, Session session) {
        return session.createNativeQuery(query, Document.class)
                .setParameter("orderId", document.getOrder().getId())
                .setParameter("documentContent", document.getDocumentContent());
    }

    public void update(Document document) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                makeQueryForInsertOrUpdateCompanies(document, UPDATE_QUERY, session)
                        .setParameter("id", document.getId())
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(
                        String.format("Can't update document with id=%d. This document is not exist!",
                                document.getId()), e);
            }
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Document document = session.getReference(Document.class, id);
                session.remove(document);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(
                        String.format("Can't delete document with id=%d. This document is not exist!", id), e);
            }
        }
    }
}
