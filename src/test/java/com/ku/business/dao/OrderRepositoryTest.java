package com.ku.business.dao;

import com.ku.business.entity.Order;
import com.ku.business.entity.OrderStatus;
import com.ku.business.exception.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrderRepositoryTest {
    public PGSimpleDataSource dataSource = new PGSimpleDataSource();
    OrderRepository orderRepository = new OrderRepository(getConnection());

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
    public void testGetListOfOrders() throws RepositoryException {

        //given
        List<Order> orders = orderRepository.findAll();
        for (Order p: orders
             ) {
            System.out.println(p);
        }

        //when
        boolean isNotEmpty = (orders.isEmpty());

        //then
        Assertions.assertFalse(isNotEmpty);
    }

    @RepeatedTest(100)
    public void testReturnOrderById() throws RepositoryException {
        //given
        Long id = (long) (Math.random() * (1000 - 1)) + 1;
        Order order = orderRepository.findById(id);
        System.out.println(order);

        //when
        boolean isIdEqual = (Objects.equals(order.getId(), id));

        //then
        Assertions.assertTrue(isIdEqual);
    }

    @Test
    public void testSaveToTableOrder() throws RepositoryException {

        //given
        Order first = new Order(1001L, LocalDateTime.of(2017, 2, 13, 15, 56), null, null, OrderStatus.CREATED);
        orderRepository.save(first);
        Order second = orderRepository.findById(1001L);
        //orderRepository.delete(1001L);

        //when
        boolean isEqual = (Objects.equals(first.getCreatedAtUtc(), second.getCreatedAtUtc()));

        //then
        Assertions.assertTrue(isEqual);
    }

    @Test
    public void testUpdateValueInTable() throws RepositoryException {

        //given
        long id = 1002L;// (long) (Math.random() * 1000 + 1);
        Order first = orderRepository.findById(id);
        orderRepository.update(new Order(1002L, LocalDateTime.of(2017, 2, 13, 15, 56), null, null, OrderStatus.CREATED));
        Order second = orderRepository.findById(id);

        //when
        boolean isEqual = (first.equals(second));
        orderRepository.update(first);

        //then
        Assertions.assertFalse(isEqual);

    }

    @Test
    public void testDeleteFromTable() throws RepositoryException {

        //given
        Order order = new Order(1001L, LocalDateTime.of(2017, 2, 13, 15, 56), null, null, OrderStatus.CREATED);
        orderRepository.save(order);
        Order first = orderRepository.findById(1002L);
        boolean isExist = first.getId() != null;
        orderRepository.delete(1002L);
        Order second = orderRepository.findById(1002L);

        //when
        boolean isExistAfterDelete = second.getId() != null;

        //then
        Assertions.assertNotEquals(isExist, isExistAfterDelete);

    }
}
