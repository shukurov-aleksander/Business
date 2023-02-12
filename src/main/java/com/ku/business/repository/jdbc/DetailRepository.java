package com.ku.business.repository.jdbc;

import com.ku.business.entity.Detail;
import com.ku.business.entity.OperationType;
import com.ku.business.entity.Order;
import com.ku.business.entity.Company;
import com.ku.business.entity.OrderStatus;
import com.ku.business.exception.RepositoryException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.ku.business.repository.hibernate.Repository.COMPANY_ID_COLUMN;
import static com.ku.business.repository.hibernate.Repository.COMPANY_IS_GOVERNMENT_AGENCY_COLUMN;
import static com.ku.business.repository.hibernate.Repository.COMPANY_NAME_COLUMN;
import static com.ku.business.repository.hibernate.Repository.COMPANY_TAX_NUMBER_COLUMN;
import static com.ku.business.repository.hibernate.Repository.COMPANY_USER_ID_COLUMN;
import static com.ku.business.repository.hibernate.Repository.DETAIL_OPERATION_TYPE_COLUMN;
import static com.ku.business.repository.hibernate.Repository.ID_COLUMN;
import static com.ku.business.repository.hibernate.Repository.ORDER_COMPLETED_AT_UTC_COLUMN;
import static com.ku.business.repository.hibernate.Repository.ORDER_CREATED_AT_UTC_COLUMN;
import static com.ku.business.repository.hibernate.Repository.ORDER_ID_COLUMN;
import static com.ku.business.repository.hibernate.Repository.ORDER_STATUS_TYPE_COLUMN;

@Repository(value = "detailRep")
public class DetailRepository {
    private final Connection connection;
        public static final String FIND_BY_ID_QUERY = """
        SELECT d.id, d.operation_type,
            c.id company_id, c.company_name company_name, c.tax_number tax_number, c.user_id user_id, c.is_government_agency is_government_agency,
            o.id order_id, o.order_status order_status, o.created_at_utc created_at_utc, o.completed_at_utc completed_at_utc
        FROM details d
            LEFT JOIN companies c ON d.company_id = c.id
            LEFT JOIN orders o ON d.order_id = o.id
        WHERE d.id = ? and d.id IS NOT NULL 
    """;
        public static final String FIND_ALL_QUERY = "SELECT * FROM details";
        public static final String DELETE_BY_ID_QUERY = "DELETE FROM details WHERE id = ?";
        public static final String INSERT_QUERY = """
        INSERT INTO details (operation_type, company_id, order_id) 
        VALUES (?::operation_type_enum, ?, ?)
    """;
        public static final String UPDATE_QUERY = """
        UPDATE details
        SET operation_type = ?::operation_type_enum, company_id = ?, order_id = ?  
        WHERE id = ?
    """;

    public DetailRepository(Connection connection) {
        this.connection = connection;
    }

    public Detail findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return buildDetails(resultSet);
            }
        } catch (Exception s) {
            throw new RepositoryException(String.format("Can't find Detail with id=%d", id), s);
        }
    }

    private Detail buildDetails(ResultSet resultSet) throws Exception {
        Detail detail = buildDetailsWithoutEntities(resultSet);
        if (resultSet.getString(COMPANY_ID_COLUMN) != null) {
            detail.setCompany(buildCompany(resultSet));
        }
        if (resultSet.getString(ORDER_ID_COLUMN) != null) {
            detail.setOrder(buildOrder(resultSet));
        }
        return detail;
    }

    private Detail buildDetailsWithoutEntities(ResultSet resultSet) throws Exception {
        Detail detail = new Detail();
        detail.setId(resultSet.getLong(ID_COLUMN));
        detail.setOperationType(OperationType.valueOf(resultSet.getString(DETAIL_OPERATION_TYPE_COLUMN)));
        return detail;
    }

    private Company buildCompany(ResultSet resultSet) throws Exception {
        Company company = new Company();
        company.setId(resultSet.getLong(COMPANY_ID_COLUMN));
        company.setCompanyName(resultSet.getString(COMPANY_NAME_COLUMN));
        company.setTaxNumber(resultSet.getString(COMPANY_TAX_NUMBER_COLUMN));
        company.setUserId(resultSet.getLong(COMPANY_USER_ID_COLUMN));
        company.setGovernmentAgency((resultSet.getBoolean(COMPANY_IS_GOVERNMENT_AGENCY_COLUMN)));
        return company;
    }

    private Order buildOrder(ResultSet resultSet) throws Exception {
        Order order = new Order();
        order.setId(resultSet.getLong(ORDER_ID_COLUMN));
        order.setCreatedAtUtc((resultSet.getTimestamp(ORDER_CREATED_AT_UTC_COLUMN)).toLocalDateTime());
        order.setCompletedAtUtc((resultSet.getTimestamp(ORDER_COMPLETED_AT_UTC_COLUMN)).toLocalDateTime());
        order.setOrderStatus(OrderStatus.valueOf(resultSet.getString(ORDER_STATUS_TYPE_COLUMN)));
        return order;
    }

    public List<Detail> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            List<Detail> details = new ArrayList<>();
            while (resultSet.next()) {
                details.add(buildDetailsWithoutEntities(resultSet));
            }
            return details;
        } catch (Exception e) {
            throw new RepositoryException("Table details is empty!", e);
        }
    }

    public void save(Detail detail) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            makeQueryForInsertOrUpdateDetails(detail, preparedStatement).executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException("Try to save detail with null service", e);
        }
    }

    public PreparedStatement makeQueryForInsertOrUpdateDetails(Detail detail, PreparedStatement preparedStatement) throws Exception {
        preparedStatement.setString(1, detail.getOperationType().toString());
        preparedStatement.setLong(2, detail.getCompany().getId());
        preparedStatement.setLong(3, detail.getOrder().getId());
        return preparedStatement;
    }

    public void update(Detail detail) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            makeQueryForInsertOrUpdateDetails(detail, preparedStatement);
            preparedStatement.setLong(4, detail.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't find detail with id=%d", detail.getId()), e);
        }
    }

    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException(String.format("Can't find detail with id=%d", id), e);
        }
    }
}

