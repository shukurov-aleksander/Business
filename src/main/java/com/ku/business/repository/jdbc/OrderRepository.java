package com.ku.business.repository.jdbc;

import com.ku.business.entity.Content;
import com.ku.business.entity.Order;
import com.ku.business.entity.OrderStatus;
import com.ku.business.exception.RepositoryException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.ku.business.repository.hibernate.Repository.*;

public class OrderRepository {
private final DataSource dataSource;
    public static final String FIND_BY_ID_QUERY = """
        SELECT o.id, o.order_status, o.created_at_utc, o.completed_at_utc,
            c.id content_id, c.quantity quantity 
        FROM orders o
            LEFT JOIN order_content_links ocl ON ocl.order_id = o.id
            LEFT JOIN contents c  ON c.id = ocl.content_id
        WHERE o.id = ?
    """;
    public static final String FIND_ALL_QUERY = "SELECT * FROM orders";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM orders WHERE id = ?";
    public static final String INSERT_QUERY = """
        INSERT INTO orders (order_status, created_at_utc, completed_at_utc) 
        VALUES (?::order_status_enum, ?, ?)
    """;
    public static final String UPDATE_QUERY = """
        UPDATE orders
        SET order_status = ?::order_status_enum, created_at_utc = ?, completed_at_utc = ?
        WHERE id = ?
    """;

    public OrderRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Order findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return buildOrder(resultSet);
            }
        } catch (Exception s) {
            throw new RepositoryException(String.format("Can't find Order with id=%d", id), s);
        }
    }

    private Order buildOrder(ResultSet resultSet) throws Exception {
        Order order = buildOrderWithoutEntities(resultSet);
        List<Content> contents = new ArrayList<>();
        do {
            if (resultSet.getString(ID_COLUMN) != null && !contents.contains(buildContent(resultSet))) {
                contents.add(buildContent(resultSet));
            }
        } while (resultSet.next());
        order.setContents(contents);
        return order;
    }

    private Order buildOrderWithoutEntities(ResultSet resultSet) throws Exception {
        Order order = new Order();
        order.setId(resultSet.getLong(ID_COLUMN));
        order.setCreatedAtUtc((resultSet.getTimestamp(ORDER_CREATED_AT_UTC_COLUMN)).toLocalDateTime());
        order.setCompletedAtUtc((resultSet.getTimestamp(ORDER_COMPLETED_AT_UTC_COLUMN)).toLocalDateTime());
        order.setOrderStatus(OrderStatus.valueOf(resultSet.getString(ORDER_STATUS_TYPE_COLUMN)));
        return order;
    }

    private Content buildContent(ResultSet resultSet) {
        try {
            Content content = new Content();
            content.setId(resultSet.getLong(CONTENT_ID_COLUMN));
            content.setQuantity(resultSet.getLong(QUANTITY_COLUMN));
            return content;
        } catch (Exception s) {
            throw new RepositoryException("Result set is empty!", s);
        }
    }

    public List<Order> findAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(buildOrderWithoutEntities(resultSet));
            }
            return orders;
        } catch (Exception e) {
            throw new RepositoryException("Table orders is empty!", e);
        }
    }

    public void save(Order order) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)
        ) {
            makeQueryForInsertOrUpdateOrders(order, preparedStatement).executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException("Try to save order with null created time", e);
        }
    }

    public PreparedStatement makeQueryForInsertOrUpdateOrders(Order order, PreparedStatement preparedStatement) throws Exception {
        preparedStatement.setString(1, order.getOrderStatus().toString());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(order.getCreatedAtUtc()));
        preparedStatement.setTimestamp(3, Timestamp.valueOf(order.getCompletedAtUtc()));
        return preparedStatement;
    }

    public void update(Order order) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            makeQueryForInsertOrUpdateOrders(order, preparedStatement);
            preparedStatement.setLong(4, order.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't update Order with id=%d", order.getId()), e);
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
            throw new RepositoryException(String.format("Can't find Order with id=%d", id), e);
        }
    }
}
