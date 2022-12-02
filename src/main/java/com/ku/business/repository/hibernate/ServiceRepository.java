package com.ku.business.repository.hibernate;

import com.ku.business.entity.Service;
import com.ku.business.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ServiceRepository {
    public static final String FIND_BY_ID_QUERY = """
        FROM Service s
        WHERE s.id = :id
    """;
    public static final String FIND_ALL_QUERY = "FROM Service";
    public static final String INSERT_QUERY = """
        INSERT INTO services (service_name, sum, service_description) 
        VALUES(:serviceName, :sum, :serviceDescription) 
    """;
    private final SessionFactory sessionFactory;

    public ServiceRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Service findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_BY_ID_QUERY, Service.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception s) {
            throw new RepositoryException(String.format("Can't find service with id=%d!", id), s);
        }
    }

    public List<Service> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_ALL_QUERY, Service.class).list();
        } catch (Exception e) {
            throw new RepositoryException("Table services is empty!", e);
        }
    }

    public void save(Service service) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.createNativeQuery(INSERT_QUERY, Service.class)
                        .setParameter("serviceName", service.getServiceName())
                        .setParameter("sum", service.getSum())
                        .setParameter("serviceDescription", service.getServiceDescription())
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Failed to save service where id = %d!", service.getId()), e);
            }
        }
    }

    public void update(Service service) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.merge(service);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Can't update service with id=%d. This service is not exist!", service.getId()), e);
            }
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Service service = session.getReference(Service.class, id);
                session.remove(service);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Can't delete service with id=%d. This service is not exist!", id), e);
            }
        }
    }
}
