package com.ku.business.repository.hibernate;

import com.ku.business.entity.Company;
import com.ku.business.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository()
public class CompanyRepository {
    public static final String FIND_BY_ID_QUERY = """
        FROM Company c
            LEFT JOIN FETCH c.storages
            LEFT JOIN FETCH c.details
        WHERE c.id = :id
    """;
    public static final String FIND_ALL_QUERY = "FROM Company";
    public static final String INSERT_QUERY = """
        INSERT INTO companies (company_name, tax_number, user_id, is_government_agency) 
        VALUES(:companyName, :taxNumber, :userId, :isGovernmentAgency) 
    """;
    public static final String UPDATE_QUERY = """
        UPDATE companies
        SET company_name = :companyName, tax_number = :taxNumber, user_id = :userId, is_government_agency = :isGovernmentAgency  
        WHERE id = :id
    """;

    private final SessionFactory sessionFactory;

    public CompanyRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Company findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_BY_ID_QUERY, Company.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't find company with id=%d!", id), e);
        }
    }

    public List<Company> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_ALL_QUERY, Company.class)
                    .list();
        } catch (Exception e) {
            throw new RepositoryException("Table companies is empty!", e);
        }
    }

    public void save(Company company) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                makeQueryForInsertOrUpdateCompanies(company, INSERT_QUERY, session).executeUpdate();
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(
                        String.format("Failed to save Company where id = %d! Company with tax number=%s already exist.",
                                company.getId(), company.getTaxNumber()), e);
            }
        }
    }

    public NativeQuery<Company> makeQueryForInsertOrUpdateCompanies(Company company, String query, Session session) {
        return session.createNativeQuery(query, Company.class)
                .setParameter("companyName", company.getCompanyName())
                .setParameter("taxNumber", company.getTaxNumber())
                .setParameter("userId", company.getUserId())
                .setParameter("isGovernmentAgency", company.isGovernmentAgency());
    }

    public void update(Company company) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                makeQueryForInsertOrUpdateCompanies(company, UPDATE_QUERY, session).setParameter("id", company.getId()).executeUpdate();
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(
                        String.format("Can't update company with id=%d. This company is not exist!", company.getId()), e);
            }
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Company company = session.getReference(Company.class, id);
                session.remove(company);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(
                        String.format("Can't delete company with id=%d. This company is not exist!", id), e);
            }
        }
    }
}
