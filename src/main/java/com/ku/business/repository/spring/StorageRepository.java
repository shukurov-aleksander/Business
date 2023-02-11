package com.ku.business.repository.spring;

import com.ku.business.entity.Company;
import com.ku.business.entity.Service;
import com.ku.business.entity.Storage;
import com.ku.business.exception.RepositoryException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ku.business.repository.hibernate.Repository.COMPANY_ID_COLUMN;
import static com.ku.business.repository.hibernate.Repository.COMPANY_IS_GOVERNMENT_AGENCY_COLUMN;
import static com.ku.business.repository.hibernate.Repository.COMPANY_NAME_COLUMN;
import static com.ku.business.repository.hibernate.Repository.COMPANY_TAX_NUMBER_COLUMN;
import static com.ku.business.repository.hibernate.Repository.COMPANY_USER_ID_COLUMN;
import static com.ku.business.repository.hibernate.Repository.ID_COLUMN;
import static com.ku.business.repository.hibernate.Repository.QUANTITY_COLUMN;
import static com.ku.business.repository.hibernate.Repository.SERVICE_DESCRIPTION_COLUMN;
import static com.ku.business.repository.hibernate.Repository.SERVICE_ID_COLUMN;
import static com.ku.business.repository.hibernate.Repository.SERVICE_NAME_COLUMN;
import static com.ku.business.repository.hibernate.Repository.SUM_COLUMN;

@Repository
public class StorageRepository implements CrudRepository<Storage> {
    private final Connection connection;
    public static final String FIND_BY_ID_QUERY = """
        SELECT s.id, s.quantity, s2.id service_id, s2.service_name service_name, s2.sum sum, 
            s2.service_description service_description, c.id company_id, c.company_name company_name, 
            c.tax_number tax_number, c.user_id user_id, c.is_government_agency is_government_agency 
        FROM storages s
            LEFT JOIN services s2 ON s.service_id = s2.id
            LEFT JOIN companies c ON s.company_id = c.id
        WHERE s.id = ?
    """;
    public static final String FIND_ALL_QUERY = "SELECT * FROM storages";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM storages WHERE id = ?";
    public static final String INSERT_QUERY = """
        INSERT INTO storages (quantity, company_id, service_id) 
        VALUES (?, ?, ?)
    """;
    public static final String UPDATE_QUERY = """
        UPDATE storages 
        SET quantity = ?, company_id =?, service_id = ? 
        WHERE id = ?
    """;
    public StorageRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Storage> findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return buildStorage(resultSet);
            }
        } catch (Exception s) {
            throw new RepositoryException(String.format("Can't find Storage with id=%d!", id), s);
        }
    }
    private Optional<Storage> buildStorage(ResultSet resultSet) {
        try {
            resultSet.next();
            Storage storage = buildStorageWithoutEntities(resultSet);
            if (resultSet.getString(COMPANY_ID_COLUMN) != null) {
                storage.setCompany(buildCompany(resultSet));
            }
            if (resultSet.getString(SERVICE_ID_COLUMN) != null) {
                storage.setService(buildService(resultSet));
            }
            return Optional.of(storage);
        } catch (Exception s) {
            throw new RepositoryException("Result set is empty!", s);
        }
    }
    private Storage buildStorageWithoutEntities(ResultSet resultSet) {
        try {
            Storage storage = new Storage();
            storage.setId(resultSet.getLong(ID_COLUMN));
            storage.setQuantity(resultSet.getInt(QUANTITY_COLUMN));
            return storage;
        } catch (Exception s) {
            throw new RepositoryException("Result set is empty!", s);
        }
    }

    private Company buildCompany(ResultSet resultSet) {
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

    private Service buildService(ResultSet resultSet) throws Exception {
        Service service = new Service();
        service.setId(resultSet.getLong(SERVICE_ID_COLUMN));
        service.setSum(resultSet.getLong(SUM_COLUMN));
        service.setServiceName(resultSet.getString(SERVICE_NAME_COLUMN));
        service.setServiceDescription(resultSet.getString(SERVICE_DESCRIPTION_COLUMN));
        return service;
    }

    @Override
    public List<Storage> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            List<Storage> storages = new ArrayList<>();
            while (resultSet.next()) {
                storages.add(buildStorageWithoutEntities(resultSet));
            }
            return storages;
        } catch (Exception e) {
            throw new RepositoryException("Table storages is empty!", e);
        }
    }

    @Override
    public void update(Storage storage, Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            makeQueryForInsertOrUpdateStorages(storage, preparedStatement);
            preparedStatement.setLong(4, storage.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't update storage with id=%d. This storage is not exist!", storage.getId()), e);
        }
    }
    public PreparedStatement makeQueryForInsertOrUpdateStorages(Storage storage, PreparedStatement preparedStatement) throws Exception {
        preparedStatement.setLong(1, storage.getQuantity());
        preparedStatement.setLong(2, storage.getCompany().getId());
        preparedStatement.setLong(3, storage.getService().getId());
        return preparedStatement;
    }
    @Override
    public void save(Storage storage) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)
        ) {
            makeQueryForInsertOrUpdateStorages(storage, preparedStatement).executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Storage with company_id=%s already exist", storage.getCompany()), e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't delete Storage with id=%d. This Storage is not exist!", id), e);
        }
    }
}
