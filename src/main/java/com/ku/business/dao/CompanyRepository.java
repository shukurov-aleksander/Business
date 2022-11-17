package com.ku.business.dao;

import com.ku.business.entity.Company;
import com.ku.business.entity.Detail;
import com.ku.business.entity.OperationType;
import com.ku.business.entity.Storage;
import com.ku.business.exception.RepositoryException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.ku.business.dao.ICompanyRepository.*;

public class CompanyRepository {
    private final DataSource dataSource;
    public static final String FIND_BY_ID_QUERY = """
        SELECT c.id, c.company_name, c.tax_number, c.user_id, c.is_government_agency,
            s.id storage_id, s.quantity storage_quantity,
            d.id detail_id, d.operation_type detail_operation_type
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

    public CompanyRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Company findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return buildCompany(resultSet);
            }
        } catch (Exception s) {
            throw new RepositoryException(String.format("Can't find company with id=%d!", id), s);
        }
    }

    private Company buildCompany(ResultSet resultSet) {
        try {
            List<Storage> storages = new ArrayList<>();
            List<Detail> details = new ArrayList<>();
            resultSet.next();
            Company company = buildCompanyWithoutLists(resultSet);
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
            return company;
        } catch (Exception s) {
            throw new RepositoryException("Result set is empty!", s);
        }
    }

    private Company buildCompanyWithoutLists(ResultSet resultSet) {
        try {
            Company company = new Company();
            company.setId(resultSet.getLong(COMPANY_ID_COLUMN));
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
        storage.setQuantity(resultSet.getInt(STORAGE_QUANTITY));
        return storage;
    }

    private Detail buildDetails(ResultSet resultSet) throws Exception {
        Detail detail = new Detail();
        detail.setId(resultSet.getLong(DETAIL_ID_COLUMN));
        detail.setOperationType(OperationType.valueOf(resultSet.getString(DETAIL_OPERATION_TYPE_COLUMN)));
        return detail;
    }

    public List<Company> findAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            List<Company> companies = new ArrayList<>();
            while (resultSet.next()) {
                companies.add(buildCompanyWithoutLists(resultSet));
            }
            return companies;
        } catch (Exception e) {
            throw new RepositoryException("Table companies is empty!", e);
        }
    }

    public void save(Company company) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)
        ) {
            updateCompany(company, preparedStatement).executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Company with tax number=%s already exist", company.getTaxNumber()), e);
        }
    }

    public void update(Company company) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            updateCompany(company, preparedStatement);
            preparedStatement.setLong(5, company.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't update company with id=%d. This company is not exist!", company.getId()), e);
        }
    }

    public PreparedStatement updateCompany(Company company, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.setString(2, company.getTaxNumber());
            preparedStatement.setLong(3, company.getUserId());
            preparedStatement.setBoolean(4, company.isGovernmentAgency());
        } catch (Exception e) {
            throw new RepositoryException(String.format("Company with tax number=%s already exist", company.getTaxNumber()), e);
        }
        return preparedStatement;
    }

    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY)
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't delete company with id=%d. This company is not exist!", id), e);
        }
    }
}
