package com.ku.business.dao;

import com.ku.business.entity.Content;
import com.ku.business.entity.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

public class ContentRepositoryTest {
    public PGSimpleDataSource dataSource = new PGSimpleDataSource();
    ContentRepository repository = new ContentRepository(getConnection());

    public DataSource getConnection() {
        this.dataSource.setServerNames(new String[]{
                "Local Business database"
        });
        this.dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres?characterEncoding=utf8");
        this.dataSource.setDatabaseName(this.dataSource.getDatabaseName());
        this.dataSource.setUser("postgres");
        this.dataSource.setPassword("postgres");
        return dataSource;
    }

    @Test
    public void testGetListOfContents() {

        //given
        List<Content> contents = repository.findAll();

        //when
        boolean isNotEmpty = (contents.isEmpty());

        //then
        Assertions.assertFalse(isNotEmpty);
    }

    @RepeatedTest(100)
    public void testReturnContentById() {

        //given
        Long id = (long) (Math.random() * (1000 - 1)) + 1;
        Content content = repository.findById(id);

        //when
        boolean isIdEqual = (Objects.equals(content.getId(), id));

        //then
        Assertions.assertTrue(isIdEqual);
    }

    @Test
    public void testAddToContentTable() {

        //given
        Content first = new Content(1025L, 255L, new Service(412L, null, null, null), null);
        repository.save(first);
        Content second = repository.findById(1025L);
        repository.delete(1025L);

        //when
        boolean isEqual = (Objects.equals(first.getQuantity(), second.getQuantity()));

        //then
        Assertions.assertTrue(isEqual);
    }

    @Test
    public void testUpdateValueInContentTable() {

        //given
        long id = (long) (Math.random() * 1000 + 1);
        Content first = repository.findById(id);
        repository.update(new Content(1001L, 322L, new Service(965L, null, null, null), null));
        Content second = repository.findById(id);

        //when
        boolean isEqual = (first.equals(second));
        repository.update(first);

        //then
        Assertions.assertFalse(isEqual);
    }

    @Test
    public void testDeleteFromTableContents() {

        //given
        Content content = new Content(1001L, 322L, new Service(965L, null, null, null), null);
        repository.save(content);
        Content first = repository.findById(1001L);
        boolean isExist = first.getId() != null;
        repository.delete(1001L);
        Content second = repository.findById(1001L);

        //when
        boolean isExistAfterDelete = second.getId() != null;

        //then
        Assertions.assertNotEquals(isExist, isExistAfterDelete);
    }
}
