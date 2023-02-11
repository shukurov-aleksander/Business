package com.ku.business.repository.spring;

import com.ku.business.entity.Service;
import com.ku.business.exception.RepositoryException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ku.business.repository.hibernate.Repository.ID_COLUMN;
import static com.ku.business.repository.hibernate.Repository.SERVICE_DESCRIPTION_COLUMN;
import static com.ku.business.repository.hibernate.Repository.SERVICE_NAME_COLUMN;
import static com.ku.business.repository.hibernate.Repository.SUM_COLUMN;

@Repository
public class ServiceRepository implements CrudRepository<Service> {
    private final Connection connection;

    public static final String FIND_BY_ID_QUERY = """
        SELECT id, service_name, sum, service_description
        FROM services
        WHERE id = ?
    """;
    public static final String FIND_ALL_QUERY = "SELECT * FROM services";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM services WHERE id = ?";
    public static final String INSERT_QUERY = """
        INSERT INTO services (service_name, sum, service_description) 
        VALUES (?, ?, ?)
    """;
    public static final String UPDATE_QUERY = """
        UPDATE services
        SET service_name = ?, sum = ?, service_description = ?
        WHERE id = ?
    """;

    public ServiceRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Service> findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return buildService(resultSet);
            }
        } catch (Exception s) {
            throw new RepositoryException(String.format("Can't find Service with id=%d", id), s);
        }
    }

    private Optional<Service> buildService(ResultSet resultSet) throws Exception {
        Service service = new Service();
        service.setId(resultSet.getLong(ID_COLUMN));
        service.setSum(resultSet.getLong(SUM_COLUMN));
        service.setServiceName(resultSet.getString(SERVICE_NAME_COLUMN));
        service.setServiceDescription(resultSet.getString(SERVICE_DESCRIPTION_COLUMN));
        return Optional.of(service);
    }

    @Override
    public List<Service> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            List<Service> services = new ArrayList<>();
            while (resultSet.next()) {
                services.add(buildService(resultSet).get());
            }
            return services;
        } catch (Exception e) {
            throw new RepositoryException("Table services is empty!", e);
        }
    }

    @Override
    public void update(Service service, Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setLong(4, service.getId());
            makeQueryForInsertOrUpdateServices(service, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't update Service with id=%d", service.getId()), e);
        }
    }

    public PreparedStatement makeQueryForInsertOrUpdateServices(Service service, PreparedStatement preparedStatement) throws Exception {
        preparedStatement.setString(1, service.getServiceName());
        preparedStatement.setLong(2, service.getSum());
        preparedStatement.setString(3, service.getServiceDescription());
        return preparedStatement;
    }

    @Override
    public void save(Service service) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            makeQueryForInsertOrUpdateServices(service, preparedStatement).executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException("Try to save service with null order content", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException(String.format("Can't delete Service with id=%d. Service is not exist!", id), e);
        }
    }
}
