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
    public static final String COMPANY_ID_COLUMN = "id";
    public static final String COMPANY_NAME_COLUMN = "company_name";
    public static final String COMPANY_TAX_NUMBER_COLUMN = "tax_number";
    public static final String COMPANY_USER_ID_COLUMN = "user_id";
    public static final String COMPANY_IS_GOVERNMENT_AGENCY_COLUMN = "is_government_agency";
    public static final String STORAGE_ID_COLUMN = "storage_id";
    public static final String STORAGE_QUANTITY = "storage_quantity";
    public static final String DETAIL_ID_COLUMN = "detail_id";
    public static final String DETAIL_OPERATION_TYPE_COLUMN = "detail_operation_type";

    public CompanyRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Company findById(Long id) {
        Company company;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)
        ) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                company = buildCompany(resultSet);
            }
            return company;
        } catch (Exception s) {
            throw new RepositoryException("Can't find company with id=" + id, s);
        }
    }

    private Company buildCompany(ResultSet resultSet) {
        Company company;
        List<Storage> storages = new ArrayList<>();
        List<Detail> details = new ArrayList<>();
        Storage storage;
        Detail detail;
        try {
            resultSet.next();
            company = buildCompanyWithoutLists(resultSet);
            do {
                storage = buildStorage(resultSet);
                if (!storages.contains(storage) && !storage.equals(new Storage())) {
                    storages.add(storage);
                }
                detail = buildDetails(resultSet);
                if (!details.contains(detail) && !detail.equals(new Detail()) ) {
                    details.add(detail);
                }
            } while (resultSet.next());
                company.setStorages(storages);
                company.setDetails(details);
        } catch (Exception s) {
            throw new RepositoryException("Result set is empty!", s);
        }
        return company;
    }

    private Storage buildStorage(ResultSet resultSet) {
        Storage storage = new Storage();
        try {
            if (resultSet.getString(STORAGE_ID_COLUMN) != null) {
                storage.setId(resultSet.getLong(STORAGE_ID_COLUMN));
                storage.setQuantity(resultSet.getInt(STORAGE_QUANTITY));
            }
            return storage;
        } catch (Exception s) {
            throw new RepositoryException("Result set is empty!", s);
        }
    }

    private Detail buildDetails(ResultSet resultSet) {
        Detail detail = new Detail();
        try {
            if (resultSet.getString(DETAIL_ID_COLUMN) != null) {
            detail.setId(resultSet.getLong(DETAIL_ID_COLUMN));
            detail.setOperationType(OperationType.valueOf(resultSet.getString(DETAIL_OPERATION_TYPE_COLUMN)));
            }
            return detail;
        } catch (Exception s) {
            throw new RepositoryException("Result set is empty!", s);
        }
    }

    public List<Company> findAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            List<Company> companies = new ArrayList<>();
            while (resultSet.next()) {
                companies.add(new Company(buildCompanyWithoutLists(resultSet)));
            }
            return companies;
        } catch (Exception e) {
            throw new RepositoryException("Table companies is empty!", e);
        }
    }

    private Company buildCompanyWithoutLists(ResultSet resultSet) {
        Company company = new Company();
        try {
            company.setId(resultSet.getLong(COMPANY_ID_COLUMN));
            company.setCompanyName(resultSet.getString(COMPANY_NAME_COLUMN));
            company.setTaxNumber(resultSet.getString(COMPANY_TAX_NUMBER_COLUMN));
            company.setUserId(resultSet.getLong(COMPANY_USER_ID_COLUMN));
            company.setGovernmentAgency((resultSet.getBoolean(COMPANY_IS_GOVERNMENT_AGENCY_COLUMN)));
        } catch (Exception s) {
            throw new RepositoryException("Result set is empty!", s);
        }
        return company;
    }

    public void save(Company company) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)
        ) {
            statement.setString(1, company.getCompanyName());
            statement.setString(2, company.getTaxNumber());
            statement.setLong(3, company.getUserId());
            statement.setBoolean(4, company.isGovernmentAgency());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException("Company with tax number=" + company.getTaxNumber() + " already exist", e);
        }
    }

    public void update(Company company) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            statement.setString(1, company.getCompanyName());
            statement.setString(2, company.getTaxNumber());
            statement.setLong(3, company.getUserId());
            statement.setBoolean(4, company.isGovernmentAgency());
            statement.setLong(5, company.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException("Can't update company with id=" + company.getId() + ". This company is not exist!", e);
        }
    }

    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY)
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Can't delete company with id=" + id + ". This company is not exist!", e);
        }
    }
}
