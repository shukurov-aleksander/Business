package com.ku.business.dao;

import com.ku.business.entity.*;
import com.ku.business.exception.RepositoryException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.ku.business.dao.IOrderRepository.*;


public class OrderRepository {
private final DataSource dataSource;
    public static final String FIND_BY_ID_QUERY = """
        SELECT d.id, d.order_id, d.document_content,
            o.id order_order_id, o.order_status order_status, o.created_at_utc order_created_at_utc, o.completed_at_utc order_completed_at_utc
        FROM documents d
        LEFT JOIN orders o ON d.order_id = o.id
        WHERE d.id = ?
    """;
    public static final String FIND_ALL_QUERY = "SELECT * FROM orders";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM orders WHERE id = ?";
    public static final String INSERT_QUERY = """
        INSERT INTO orders (order_status, created_at_ut, compleated_at_utc) 
        VALUES (?, ?, ?)
    """;
    public static final String UPDATE_QUERY = """
        UPDATE orders
        SET order_status = ?, created_at_ut = ?, compleated_at_utc = ?
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
        Order order = buildOrderWithoutList(resultSet);
        List<Content> contents = new ArrayList<>();
        do {
            if (resultSet.getString(CONTENT_ID_COLUMN) != null && !contents.contains(buildContent(resultSet))) {
                contents.add(buildContent(resultSet));
            }
        } while (resultSet.next());
        order.setContents(contents);
        return order;
    }

    private Order buildOrderWithoutList(ResultSet resultSet) throws Exception {
        Order order = new Order();
        order.setId(resultSet.getLong(ORDER_ID_COLUMN));
        order.setCreatedAtUtc((resultSet.getTimestamp(ORDER_CREATE_AT_UTC_COLUMN)).toLocalDateTime());
        order.setCompletedAtUtc((resultSet.getTimestamp(ORDER_COMPLETED_AT_UTC_COLUMN)).toLocalDateTime());
        order.setOrderStatus(OrderStatus.valueOf(resultSet.getString(ORDER_STATUS_TYPE_COLUMN)));
        return order;
    }

    private Content buildContent(ResultSet resultSet) {
        try {
            Content content = new Content();
            content.setId(resultSet.getLong(CONTENT_ID_COLUMN));
            content.setQuantity(resultSet.getLong(CONTENT_QUANTITY_COLUMN));
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
                orders.add(new Order(buildOrderWithoutList(resultSet)));
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
            updateOrder(order, preparedStatement).executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException("Try to save order with null order content", e);
        }
    }

    public PreparedStatement updateOrder(Order order, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, String.format("%S::order_status", order.getOrderStatus()));
            preparedStatement.setDate(2, Date.valueOf(order.getCreatedAtUtc().toLocalDate()));
            preparedStatement.setDate(3, Date.valueOf(order.getCompletedAtUtc().toLocalDate()));
        } catch (Exception e) {
            throw new RepositoryException(String.format("Order with tax number=%s already exist", order.getId()), e);
        }
        return preparedStatement;
    }

    public void update(Order order) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            preparedStatement.setLong(1, order.getId());
            updateOrder(order, preparedStatement);
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
