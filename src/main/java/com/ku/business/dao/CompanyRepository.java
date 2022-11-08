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
                s.id storage__id, s.quantity storage__quantity,
                d.id detail__id, d.operation_type detail__ot
                FROM companies c
                LEFT JOIN storages s
                ON s.company_id=c.id
                LEFT JOIN details d
                ON c.id=d.company_id
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
    public static final String STORAGE_ID_COLUMN = "storage__id";
    public static final String STORAGE_QUANTITY = "storage__quantity";
    public static final String DETAIL_ID_COLUMN = "detail__id";
    public static final String DETAIL_OPERATION_TYPE_COLUMN = "detail__ot";


    public CompanyRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Company findById(Long id) {
        Company company;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                company = buildCompany(resultSet);
            }
        } catch (Exception s) {
            throw new RepositoryException("Can't find user with id=" + id, s);
        }
        return company;
    }

    public List<Company> findAll() {
        List<Company> companies = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                companies.add(findById(resultSet.getLong("id")));
            }
        } catch (Exception e) {
            throw new RepositoryException("Table companies is empty!", e);
        }
        return companies;
    }

    public void save(Company company) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)
        ) {
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.setString(2, company.getTaxNumber());
            preparedStatement.setLong(3, company.getUserId());
            preparedStatement.setBoolean(4, company.isGovernmentAgency());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException("Company with tax number=" + company.getTaxNumber() + " already exist", e);
        }
    }

    public void update(Company company) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.setString(2, company.getTaxNumber());
            preparedStatement.setLong(3, company.getUserId());
            preparedStatement.setBoolean(4, company.isGovernmentAgency());
            preparedStatement.setLong(5, company.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException("Can't find user with id=" + company.getId(), e);
        }
    }

    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY)
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Can't find user with id=" + id, e);
        }
    }

    private Company buildCompany(ResultSet resultSet) {
        Company company = new Company();
        List<Storage> storages = new ArrayList<>();
        List<Detail> details = new ArrayList<>();
        try {
            resultSet.next();
            company.setId(resultSet.getLong(COMPANY_ID_COLUMN));
            company.setCompanyName(resultSet.getString(COMPANY_NAME_COLUMN));
            company.setTaxNumber(resultSet.getString(COMPANY_TAX_NUMBER_COLUMN));
            company.setUserId(resultSet.getLong(COMPANY_USER_ID_COLUMN));
            company.setGovernmentAgency((resultSet.getBoolean(COMPANY_IS_GOVERNMENT_AGENCY_COLUMN)));
            do {
                if (!storages.contains(buildStorage(resultSet))) {
                    storages.add(buildStorage(resultSet));
                }
                if (!details.contains(buildDetails(resultSet))) {
                    details.add(buildDetails(resultSet));
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
            storage.setId(resultSet.getLong(STORAGE_ID_COLUMN));
            storage.setQuantity(resultSet.getInt(STORAGE_QUANTITY));
        } catch (Exception s) {
            throw new RepositoryException("Result set is empty!", s);
        }
        return storage;
    }

    private Detail buildDetails(ResultSet resultSet) {
        Detail detail = new Detail();
        String operationType;
        try {
            detail.setId(resultSet.getLong(DETAIL_ID_COLUMN));
            operationType = resultSet.getString(DETAIL_OPERATION_TYPE_COLUMN);
            if (operationType != null) {
                switch (operationType) {
                    case "PURCHASE" -> detail.setOperationType(OperationType.PURCHASE);
                    case "REMITTANCE" -> detail.setOperationType(OperationType.REMITTANCE);
                    case "BARTER" -> detail.setOperationType(OperationType.BARTER);
                    case "OUTSOURCING" -> detail.setOperationType(OperationType.OUTSOURCING);
                    default -> detail.setOperationType(null);
                }
            }
        } catch (Exception s) {
            throw new RepositoryException("Result set is empty!", s);
        }
        return detail;
    }
}
