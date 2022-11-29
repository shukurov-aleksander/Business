package com.ku.business.repository.hibernate;

import com.ku.business.HibernateUtil;
import com.ku.business.entity.Document;
import com.ku.business.entity.Order;
import com.ku.business.exception.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

public class DocumentRepositoryTest {

    @Test
    public void testGetListOfDocuments() throws RepositoryException {

        //given
        DocumentRepository repository = new DocumentRepository(HibernateUtil.getSessionFactory());
        List<Document> documents = repository.findAll();
        for (Document d: documents
             ) {
            System.out.println(d.toString());
        }

        //when
        boolean isNotEmpty = (documents.isEmpty());

        //then
        Assertions.assertFalse(isNotEmpty);
    }

    @RepeatedTest(100)
    public void testReturnDocumentById() throws RepositoryException {

        //given
        DocumentRepository repository = new DocumentRepository(HibernateUtil.getSessionFactory());
        Long id = (long) (Math.random() * (1000 - 1)) + 1;
        Document document = repository.findById(id);
        System.out.println(document.toString());

        //when
        boolean isIdEqual = (Objects.equals(document.getId(), id));

        //then
        Assertions.assertTrue(isIdEqual);
    }

    @Test
    public void testSaveToTableDocuments() throws RepositoryException {

        //given
        DocumentRepository repository = new DocumentRepository(HibernateUtil.getSessionFactory());
        Document first = new Document(1001L, new Order(325L, null, null, null, null), "Some content");
        repository.save(first);
        Document second = repository.findById(1001L);
        repository.delete(1001L);

        //when
        boolean isEqual = (Objects.equals(first.getDocumentContent(), second.getDocumentContent()));

        //then
        Assertions.assertTrue(isEqual);
    }

    @Test
    public void testUpdateValueInTable() throws RepositoryException {

        //given
        DocumentRepository repository = new DocumentRepository(HibernateUtil.getSessionFactory());
        long id = 1003L;// (long) (Math.random() * 1000 + 1);
        Document first = repository.findById(id);
        repository.update(new Document(id, new Order(285L, null, null, null, null), "Some another content"));
        Document second = repository.findById(id);

        //when
        boolean isEqual = (first.equals(second));
        repository.update(first);

        //then
        Assertions.assertFalse(isEqual);

    }

    @Test
    public void testDeleteFromTable() throws RepositoryException {

        //given
        DocumentRepository repository = new DocumentRepository(HibernateUtil.getSessionFactory());
        Document document = new Document(1002L, new Order(333L, null, null, null, null), "Some usefully content");
        repository.save(document);
        Document first = repository.findById(1002L);
        boolean isExist = first.getId() != null;
        repository.delete(1003L);
        Document second = repository.findById(1002L);

        //when
        boolean isExistAfterDelete = second.getId() != null;

        //then
        Assertions.assertNotEquals(isExist, isExistAfterDelete);

    }
}
