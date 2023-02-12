package com.ku.business.repository.hibernate;

import com.ku.business.entity.Detail;
import com.ku.business.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetailRepository {
    public static final String FIND_BY_ID_QUERY = """
        FROM Detail d
            LEFT JOIN FETCH d.company
            LEFT JOIN FETCH d.order
        WHERE d.id = :id
    """;
    public static final String FIND_ALL_QUERY = "FROM Detail";
    public static final String INSERT_QUERY = """
        INSERT INTO details (operation_type, company_id, order_id) 
        VALUES(cast(:operationType AS operation_type_enum), :companyId, :orderId) 
    """;
    public static final String UPDATE_QUERY = """
        UPDATE details
        SET operation_type = cast(:operationType AS operation_type_enum), company_id = :companyId, order_id = :orderId 
        WHERE id = :id
    """;
    private final SessionFactory sessionFactory;

    public DetailRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Detail findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_BY_ID_QUERY, Detail.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't find detail with id=%d!", id), e);
        }
    }

    public List<Detail> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_ALL_QUERY, Detail.class)
                    .list();
        } catch (Exception e) {
            throw new RepositoryException("Table details is empty!", e);
        }
    }

    public boolean save(Detail detail) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                makeQueryForInsertOrUpdateCompanies(detail, INSERT_QUERY, session)
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Failed to save detail where id = %d!", detail.getId()), e);
            }
        }
        return true;
    }

    public NativeQuery<Detail> makeQueryForInsertOrUpdateCompanies(Detail detail, String query, Session session) {
        return session.createNativeQuery(query, Detail.class)
                .setParameter("operationType", detail.getOperationType().toString())
                .setParameter("companyId", detail.getCompany().getId())
                .setParameter("orderId", detail.getOrder().getId());
    }

    public void update(Detail detail) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                makeQueryForInsertOrUpdateCompanies(detail, UPDATE_QUERY, session)
                        .setParameter("id", detail.getId())
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(
                        String.format("Can't update detail with id=%d. This detail is not exist!", detail.getId()), e);
            }
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Detail detail = session.getReference(Detail.class, id);
                session.remove(detail);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(
                        String.format("Can't delete detail with id=%d. This detail is not exist!", id), e);
            }
        }
    }
}
