package com.ku.business.dao;

import com.ku.business.entity.Document;
import com.ku.business.entity.Order;
import com.ku.business.entity.OrderStatus;
import com.ku.business.exception.RepositoryException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.ku.business.dao.IDocumentRepository.*;

public class DocumentRepository {
    private final DataSource dataSource;
    public static final String FIND_BY_ID_QUERY = """
        SELECT d.id, d.order_id, d.document_content,
            o.id order_order_id, o.order_status order_status, o.created_at_utc order_created_at_utc, 
            o.completed_at_utc order_completed_at_utc
        FROM documents d
        LEFT JOIN orders o ON d.order_id = o.id
        WHERE d.id = ?
    """;
    public static final String FIND_ALL_QUERY = "SELECT * FROM documents";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM documents WHERE id = ?";
    public static final String INSERT_QUERY = """
        INSERT INTO documents (order_id, document_content) 
        VALUES (?,?)
    """;
    public static final String UPDATE_QUERY = """
        UPDATE documents
        SET order_id = ?, document_content = ? 
        WHERE id = ?
    """;

    public DocumentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Document findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return buildDocuments(resultSet);
            }
        } catch (Exception s) {
            throw new RepositoryException(String.format("Can't find Document with id=%d", id), s);
        }
    }

    private Document buildDocuments(ResultSet resultSet) throws Exception {
        Document document = buildDocumentsWithoutEntities(resultSet);
        if (resultSet.getString(ORDER_ID_COLUMN) != null) {
            document.setOrderId(buildOrder(resultSet));
        }
        return document;
    }

    private Document buildDocumentsWithoutEntities(ResultSet resultSet) throws Exception{
        Document document = new Document();
        document.setId(resultSet.getLong(DOCUMENT_ID_COLUMN));
        document.setDocumentContent(resultSet.getString(DOCUMENT_DOCUMENT_CONTENT));
        return document;
    }

    private Order buildOrder(ResultSet resultSet) throws Exception {
        Order order = new Order();
        order.setId(resultSet.getLong(ORDER_ID_COLUMN));
        order.setCreatedAtUtc((resultSet.getTimestamp(ORDER_CREATE_AT_UTC_COLUMN)).toLocalDateTime());
        order.setCompletedAtUtc((resultSet.getTimestamp(ORDER_COMPLETED_AT_UTC_COLUMN)).toLocalDateTime());
        order.setOrderStatus(OrderStatus.valueOf(resultSet.getString(ORDER_STATUS_TYPE_COLUMN)));
        return order;
    }

    public List<Document> findAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            List<Document> documents = new ArrayList<>();
            while (resultSet.next()) {
                documents.add(new Document(buildDocumentsWithoutEntities(resultSet)));
            }
            return documents;
        } catch (Exception e) {
            throw new RepositoryException("Table details is empty!", e);
        }
    }

    public void save(Document document) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)
        ) {
            preparedStatement.setLong(1, document.getOrderId().getId());
            preparedStatement.setString(2, document.getDocumentContent());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException("Try to save document with null document content", e);
        }
    }

    public void update(Document document) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            preparedStatement.setLong(1, document.getOrderId().getId());
            preparedStatement.setString(2, document.getDocumentContent());
            preparedStatement.setLong(3, document.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't update Document with id=%d", document.getId()), e);
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
            throw new RepositoryException(String.format("Can't find document with id=%d", id), e);
        }
    }
}
