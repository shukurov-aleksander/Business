package com.ku.business.repository.hibernate;

import com.ku.business.entity.Storage;
import com.ku.business.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class StorageRepository {
    public static final String FIND_BY_ID_QUERY = """
        FROM Storage s
            LEFT JOIN FETCH s.company
            LEFT JOIN FETCH s.service
        WHERE s.id = :id
    """;
    public static final String FIND_ALL_QUERY = "FROM Storage";
    public static final String INSERT_QUERY = """
        INSERT INTO Storages (quantity, company_id, service_id) 
        VALUES(:quantity, :companyId, :serviceId) 
    """;
    public static final String UPDATE_QUERY = """
        UPDATE storages
        SET quantity = :quantity, company_id = :companyId, service_id = :serviceId 
        WHERE id = :id
    """;
    private final SessionFactory sessionFactory;

    public StorageRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Storage findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_BY_ID_QUERY, Storage.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't find storage with id=%d!", id), e);
        }
    }

    public List<Storage> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_ALL_QUERY, Storage.class)
                    .list();
        } catch (Exception e) {
            throw new RepositoryException("Table storages is empty!", e);
        }
    }

    public void save(Storage storage) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                makeQueryForInsertOrUpdateCompanies(storage, INSERT_QUERY, session)
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(
                        String.format("Failed to save storages where id = %d!", storage.getId()), e);
            }
        }
    }

    public NativeQuery<Storage> makeQueryForInsertOrUpdateCompanies(Storage storage, String query, Session session) {
        return session.createNativeQuery(query, Storage.class)
                .setParameter("quantity", storage.getQuantity())
                .setParameter("companyId", storage.getCompany().getId())
                .setParameter("serviceId", storage.getService().getId());
    }

    public void update(Storage storage) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                makeQueryForInsertOrUpdateCompanies(storage, UPDATE_QUERY, session)
                        .setParameter("id", storage.getId())
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(
                        String.format("Can't update storage with id=%d. This storage is not exist!",
                                storage.getId()), e);
            }
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Storage storage = session.getReference(Storage.class, id);
                session.remove(storage);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(
                        String.format("Can't delete storage with id=%d. This storage is not exist!", id), e);
            }
        }
    }
}
