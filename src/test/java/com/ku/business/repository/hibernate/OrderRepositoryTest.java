package com.ku.business.repository.hibernate;

import com.ku.business.HibernateUtil;
import com.ku.business.entity.Order;
import com.ku.business.entity.OrderStatus;
import com.ku.business.exception.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrderRepositoryTest {


    @Test
    public void testGetListOfOrders() throws RepositoryException {

        //given
        OrderRepository repository = new OrderRepository(HibernateUtil.getSessionFactory());
        List<Order> orders = repository.findAll();
        for (Order o: orders
             ) {
            System.out.println(o);
        }

        //when
        boolean isNotEmpty = (orders.isEmpty());

        //then
        Assertions.assertFalse(isNotEmpty);
    }

    @RepeatedTest(100)
    public void testReturnOrderById() throws RepositoryException {

        //given
        OrderRepository repository = new OrderRepository(HibernateUtil.getSessionFactory());
        Long id = (long) (Math.random() * (1000 - 1)) + 1;
        Order order = repository.findById(id);

        //when
        boolean isIdEqual = (Objects.equals(order.getId(), id));

        //then
        Assertions.assertTrue(isIdEqual);
    }

    @Test
    public void testSaveToTableOrder() throws RepositoryException {

        //given
        OrderRepository repository = new OrderRepository(HibernateUtil.getSessionFactory());
        Order first = new Order(1001L, LocalDateTime.of(2017, 2, 13, 15, 56), LocalDateTime.of(2019, 8, 13, 15, 56), null, OrderStatus.CREATED);
        repository.save(first);
        Order second = repository.findById(1001L);
        repository.delete(1001L);

        //when
        boolean isEqual = (Objects.equals(first.getCreatedAtUtc(), second.getCreatedAtUtc()));

        //then
        Assertions.assertTrue(isEqual);
    }

    @Test
    public void testUpdateValueInTable() throws RepositoryException {

        //given
        OrderRepository repository = new OrderRepository(HibernateUtil.getSessionFactory());
        long id = 1001L;// (long) (Math.random() * 1000 + 1);
        Order first = repository.findById(id);
        repository.update(new Order(1001L, LocalDateTime.of(2016, 6, 28, 5, 13), LocalDateTime.of(2018, 8, 12, 12, 30), null, OrderStatus.CREATED));
        Order second = repository.findById(id);

        //when
        boolean isEqual = (first.equals(second));
        repository.update(first);

        //then
        Assertions.assertFalse(isEqual);

    }

    @Test
    public void testDeleteFromTable() throws RepositoryException {

        //given
        OrderRepository repository = new OrderRepository(HibernateUtil.getSessionFactory());
        Order order = new Order(1001L, LocalDateTime.of(2017, 2, 13, 15, 56), null, null, OrderStatus.CREATED);
        repository.save(order);
        Order first = repository.findById(1002L);
        boolean isExist = first.getId() != null;
        repository.delete(1001L);
        Order second = repository.findById(1002L);

        //when
        boolean isExistAfterDelete = second.getId() != null;

        //then
        Assertions.assertNotEquals(isExist, isExistAfterDelete);

    }
}
