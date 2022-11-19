package com.ku.business.dao;

import com.ku.business.entity.Document;
import com.ku.business.entity.Order;
import com.ku.business.exception.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

public class DocumentRepositoryTest {
    public PGSimpleDataSource dataSource = new PGSimpleDataSource();
    DocumentRepository documentRepository = new DocumentRepository(getConnection());

    public DataSource getConnection() {
        this.dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres?characterEncoding=utf8");
        this.dataSource.setDatabaseName(this.dataSource.getDatabaseName());
        this.dataSource.setUser("postgres");
        this.dataSource.setPassword("postgres");
        return dataSource;
    }

    @Test
    public void testGetListOfDocuments() throws RepositoryException {

        //given
        List<Document> documents = documentRepository.findAll();

        //when
        boolean isNotEmpty = (documents.isEmpty());

        //then
        Assertions.assertFalse(isNotEmpty);
    }

    @RepeatedTest(100)
    public void testReturnDocumentById() throws RepositoryException {
        //given
        Long id = (long) (Math.random() * (1000 - 1)) + 1;
        Document document = documentRepository.findById(id);

        //when
        boolean isIdEqual = (Objects.equals(document.getId(), id));

        //then
        Assertions.assertTrue(isIdEqual);
    }

    @Test
    public void testSaveToTableDocuments() throws RepositoryException {

        //given
        Document first = new Document(1001L, new Order(325L, null, null, null, null), "Some content");
        documentRepository.save(first);
        Document second = documentRepository.findById(1001L);
        documentRepository.delete(1001L);

        //when
        boolean isEqual = (Objects.equals(first.getDocumentContent(), second.getDocumentContent()));

        //then
        Assertions.assertTrue(isEqual);
    }

    @Test
    public void testUpdateValueInTable() throws RepositoryException {

        //given
        long id = 1003L;// (long) (Math.random() * 1000 + 1);
        Document first = documentRepository.findById(id);
        documentRepository.update(new Document(id, new Order(285L, null, null, null, null), "Some another content"));
        Document second = documentRepository.findById(id);

        //when
        boolean isEqual = (first.equals(second));
        documentRepository.update(first);

        //then
        Assertions.assertFalse(isEqual);

    }

    @Test
    public void testDeleteFromTable() throws RepositoryException {

        //given
        Document document = new Document(1002L, new Order(333L, null, null, null, null), "Some usefully content");
        documentRepository.save(document);
        Document first = documentRepository.findById(1002L);
        boolean isExist = first.getId() != null;
        documentRepository.delete(1003L);
        Document second = documentRepository.findById(1002L);

        //when
        boolean isExistAfterDelete = second.getId() != null;

        //then
        Assertions.assertNotEquals(isExist, isExistAfterDelete);

    }
}
