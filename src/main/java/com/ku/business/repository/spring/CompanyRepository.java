package com.ku.business.repository.spring;

import com.ku.business.entity.Company;
import com.ku.business.entity.Detail;
import com.ku.business.entity.OperationType;
import com.ku.business.entity.Storage;
import com.ku.business.exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.ku.business.repository.hibernate.Repository.COMPANY_IS_GOVERNMENT_AGENCY_COLUMN;
import static com.ku.business.repository.hibernate.Repository.COMPANY_NAME_COLUMN;
import static com.ku.business.repository.hibernate.Repository.COMPANY_TAX_NUMBER_COLUMN;
import static com.ku.business.repository.hibernate.Repository.COMPANY_USER_ID_COLUMN;
import static com.ku.business.repository.hibernate.Repository.DETAIL_ID_COLUMN;
import static com.ku.business.repository.hibernate.Repository.DETAIL_OPERATION_TYPE_COLUMN;
import static com.ku.business.repository.hibernate.Repository.ID_COLUMN;
import static com.ku.business.repository.hibernate.Repository.QUANTITY_COLUMN;
import static com.ku.business.repository.hibernate.Repository.STORAGE_ID_COLUMN;

@Repository
public class CompanyRepository implements CrudRepository<Company> {
   private final Connection connection;
    public static final String FIND_BY_ID_QUERY = """
        SELECT c.id, c.company_name, c.tax_number, c.user_id, c.is_government_agency,
            s.id storage_id, s.quantity quantity,
            d.id detail_id, d.operation_type operation_type
        FROM companies c
            LEFT JOIN storages s ON s.company_id=c.id
            LEFT JOIN details d ON c.id=d.company_id
        WHERE c.id = ?
    """;
    public static final String FIND_ALL_QUERY = "SELECT * FROM companies";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM companies WHERE id = ?";
    public static final String INSERT_QUERY = """
        INSERT INTO companies (company_name, tax_number, user_id, is_government_agency) 
        VALUES (?, ?, ?, ?)
    """;
    public static final String UPDATE_QUERY = """
        UPDATE companies 
        SET company_name = ?, tax_number =?, user_id = ?, is_government_agency = ? 
        WHERE id = ?
    """;
   @Autowired
    public CompanyRepository(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Optional<Company> findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return buildCompany(resultSet);
            }
        } catch (Exception s) {
            throw new RepositoryException(String.format("Can't find company with id=%d!", id), s);
        }
    }

    private Optional<Company> buildCompany(ResultSet resultSet) {
        try {
            List<Storage> storages = new ArrayList<>();
            Set<Detail> details = new HashSet<>();
            resultSet.next();
            Company company = buildCompanyWithoutEntities(resultSet);
            do {
                if (resultSet.getString(STORAGE_ID_COLUMN) != null && !storages.contains(buildStorage(resultSet))) {
                    storages.add(buildStorage(resultSet));
                }
                if (resultSet.getString(DETAIL_ID_COLUMN) != null && !details.contains(buildDetails(resultSet))) {
                    details.add(buildDetails(resultSet));
                }
            } while (resultSet.next());
            company.setStorages(storages);
            company.setDetails(details);
            return Optional.of(company);
        } catch (Exception s) {
            throw new RepositoryException("Result set is empty!", s);
        }
    }

    private Company buildCompanyWithoutEntities(ResultSet resultSet) {
        try {
            Company company = new Company();
            company.setId(resultSet.getLong(ID_COLUMN));
            company.setCompanyName(resultSet.getString(COMPANY_NAME_COLUMN));
            company.setTaxNumber(resultSet.getString(COMPANY_TAX_NUMBER_COLUMN));
            company.setUserId(resultSet.getLong(COMPANY_USER_ID_COLUMN));
            company.setGovernmentAgency((resultSet.getBoolean(COMPANY_IS_GOVERNMENT_AGENCY_COLUMN)));
            return company;
        } catch (Exception s) {
            throw new RepositoryException("Result set is empty!", s);
        }
    }
    private Storage buildStorage(ResultSet resultSet) throws Exception {
        Storage storage = new Storage();
        storage.setId(resultSet.getLong(STORAGE_ID_COLUMN));
        storage.setQuantity(resultSet.getInt(QUANTITY_COLUMN));
        return storage;
    }

    private Detail buildDetails(ResultSet resultSet) throws Exception {
        Detail detail = new Detail();
        detail.setId(resultSet.getLong(DETAIL_ID_COLUMN));
        detail.setOperationType(OperationType.valueOf(resultSet.getString(DETAIL_OPERATION_TYPE_COLUMN)));
        return detail;
    }
    @Override
    public List<Company> findAll() {
        List<Company> companies = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                companies.add(buildCompanyWithoutEntities(resultSet));
            }
        } catch (Exception e) {
            throw new RepositoryException("Table companies is empty!", e);
        }
        return companies;
    }

    @Override
    public void update(Company company, Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            makeQueryForInsertOrUpdateCompanies(company, preparedStatement);
            preparedStatement.setLong(5, company.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't update company with id=%d. This company is not exist!", company.getId()), e);
        }
    }

    public PreparedStatement makeQueryForInsertOrUpdateCompanies(Company company, PreparedStatement preparedStatement) throws Exception {
        preparedStatement.setString(1, company.getCompanyName());
        preparedStatement.setString(2, company.getTaxNumber());
        preparedStatement.setLong(3, company.getUserId());
        preparedStatement.setBoolean(4, company.isGovernmentAgency());
        return preparedStatement;
    }

    @Override
    public void save(Company company) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            makeQueryForInsertOrUpdateCompanies(company, preparedStatement).executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Company with tax number=%s already exist", company.getTaxNumber()), e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't delete company with id=%d. This company is not exist!", id), e);
        }
    }
}
