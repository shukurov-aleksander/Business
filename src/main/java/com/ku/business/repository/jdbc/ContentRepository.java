package com.ku.business.repository.jdbc;

import com.ku.business.entity.Content;
import com.ku.business.entity.Order;
import com.ku.business.entity.OrderStatus;
import com.ku.business.entity.Service;
import com.ku.business.exception.RepositoryException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.ku.business.repository.hibernate.Repository.*;

public class ContentRepository {
    private final DataSource dataSource;
    public static final String FIND_BY_ID_QUERY = """
        SELECT c.id, c.quantity, c.service_id,
            o.id order_id, o.order_status order_status, o.created_at_utc created_at_utc, o.completed_at_utc completed_at_utc,
            s.id service_id, s.service_name service_name, s.sum sum, s.service_description service_description
        FROM contents c
            LEFT JOIN order_content_links ocl ON ocl.content_id = c.id
            LEFT JOIN orders o ON o.id = ocl.order_id
            LEFT JOIN services s ON s.id = c.service_id
        WHERE c.id = ?
    """;
    public static final String FIND_ALL_QUERY = "SELECT * FROM contents";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM contents WHERE id = ?";
    public static final String INSERT_QUERY = """
        INSERT INTO contents (quantity, service_id) 
        VALUES (?, ?)
    """;
    public static final String UPDATE_QUERY = """
        UPDATE contents
        SET quantity = ?, service_id = ? 
        WHERE id = ?
    """;

    public ContentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Content findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return buildContent(resultSet);
            }
        } catch (Exception s) {
            throw new RepositoryException(String.format("Can't find Content with id=%d", id), s);
        }
    }

    private Content buildContent(ResultSet resultSet) {
        try {
            List<Order> orders = new ArrayList<>();
            resultSet.next();
            Content content = buildContentWithoutEntities(resultSet);
            if (resultSet.getString(SERVICE_ID_COLUMN) != null) {
                content.setServiceId(buildService(resultSet));
            }
            do {
                if (resultSet.getString(ORDER_ID_COLUMN) != null && !orders.contains(buildOrder(resultSet))) {
                    orders.add(buildOrder(resultSet));
                }
            } while (resultSet.next());
            content.setOrders(orders);
            return content;
        } catch (Exception s) {
            throw new RepositoryException("Result set is empty!", s);
        }
    }

    private Content buildContentWithoutEntities(ResultSet resultSet) {
        try {
            Content content = new Content();
            content.setId(resultSet.getLong(ID_COLUMN));
            content.setQuantity(resultSet.getLong(QUANTITY_COLUMN));
            return content;
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

    private Order buildOrder(ResultSet resultSet) throws Exception {
        Order order = new Order();
        order.setId(resultSet.getLong(ORDER_ID_COLUMN));
        order.setCreatedAtUtc((resultSet.getTimestamp(ORDER_CREATED_AT_UTC_COLUMN)).toLocalDateTime());
        order.setCompletedAtUtc((resultSet.getTimestamp(ORDER_COMPLETED_AT_UTC_COLUMN)).toLocalDateTime());
        order.setOrderStatus(OrderStatus.valueOf(resultSet.getString(ORDER_STATUS_TYPE_COLUMN)));
        return order;
    }

    public List<Content> findAll() {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            List<Content> contents = new ArrayList<>();
            while (resultSet.next()) {
                contents.add(buildContentWithoutEntities(resultSet));
            }
            return contents;
        } catch (Exception e) {
            throw new RepositoryException("Table contents is empty!", e);
        }
    }

    public void save(Content content) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)
        ) {
            makeQueryForInsertOrUpdateContents(content, preparedStatement).executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException("Try to save content with null service", e);
        }
    }

    public PreparedStatement makeQueryForInsertOrUpdateContents(Content content, PreparedStatement preparedStatement) throws Exception {
        preparedStatement.setLong(1, content.getQuantity());
        preparedStatement.setLong(2, content.getServiceId().getId());
        return preparedStatement;
    }

    public void update(Content content) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            makeQueryForInsertOrUpdateContents(content, preparedStatement);
            preparedStatement.setLong(3, content.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't update content with id=%d. Content is not exist!", content.getId()), e);
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
            throw new RepositoryException(String.format("Can't delete content with id=%d. Content is not exist!", id), e);
        }
    }
}

