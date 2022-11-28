package com.ku.business.repository.jdbc;

import com.ku.business.entity.*;
import com.ku.business.repository.jdbc.DetailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

public class DetailRepositoryTest {
    public PGSimpleDataSource dataSource = new PGSimpleDataSource();
    DetailRepository repository = new DetailRepository(getConnection());

    public DataSource getConnection() {
        this.dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres?characterEncoding=utf8");
        this.dataSource.setDatabaseName(this.dataSource.getDatabaseName());
        this.dataSource.setUser("postgres");
        this.dataSource.setPassword("postgres");
        return dataSource;
    }

    @Test
    public void testGetListOfDetails() {

        //given
        List<Detail> details = repository.findAll();

        //when
        boolean isNotEmpty = (details.isEmpty());

        //then
        Assertions.assertFalse(isNotEmpty);
    }

    @RepeatedTest(100)
    public void testReturnDetailById() {

        //given
        Long id = (long) (Math.random() * (1000 - 1)) + 1;
        Detail detail = repository.findById(id);
        System.out.println(detail);
        //when
        boolean isIdEqual = (Objects.equals(detail.getId(), id));

        //then
        Assertions.assertTrue(isIdEqual);
    }

    @Test
    public void testAddToTableDetails() {

        //given
        Detail first = new Detail(1025L,new Company(325L,null,"63456345",true,null,null,null), new Order(1L,null,null,null,null),OperationType.PURCHASE);
        repository.save(first);
        Detail second = repository.findById(1025L);
        repository.delete(1025L);

        //when
        boolean isEqual = (Objects.equals(first.getOperationType(), second.getOperationType()));

        //then
        Assertions.assertTrue(isEqual);
    }

    @Test
    public void testUpdateValueInTableDetails() {

        //given
        long id = 841L;//(long) (Math.random() * 1000 + 1);
        Detail first = repository.findById(id);
        repository.update(new Detail(841L,new Company(333L,null,null,false,null,null,null), new Order(698L,null,null,null,null),OperationType.BARTER));
        Detail second = repository.findById(id);

        //when
        boolean isEqual = (first.equals(second));
        repository.update(first);

        //then
        Assertions.assertFalse(isEqual);
    }

    @Test
    public void testDeleteFromTableDetails() {

        //given
        Detail detail = new Detail(1001L,new Company(333L,null,null,false,null,null,null), new Order(698L,null,null,null,null),OperationType.BARTER);
        repository.save(detail);
        Detail first = repository.findById(1001L);
        boolean isExist = first.getId() != null;
        repository.delete(841L);
        Detail second = repository.findById(1001L);

        //when
        boolean isExistAfterDelete = second.getId() != null;

        //then
        Assertions.assertNotEquals(isExist, isExistAfterDelete);
    }
}
