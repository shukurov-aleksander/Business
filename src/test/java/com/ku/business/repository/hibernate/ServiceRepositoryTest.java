package com.ku.business.repository.hibernate;

import com.ku.business.HibernateUtil;
import com.ku.business.entity.Service;
import com.ku.business.exception.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

public class ServiceRepositoryTest {

    @Test
    public void testGetListOfOrders() throws RepositoryException {

        //given
        ServiceRepository repository = new ServiceRepository(HibernateUtil.getSessionFactory());
        List<Service> services = repository.findAll();
        for (Service s: services
             ) {
            System.out.println(s.toString());
        }

        //when
        boolean isNotEmpty = (services.isEmpty());

        //then
        Assertions.assertFalse(isNotEmpty);
    }

    @RepeatedTest(100)
    public void testReturnOrderById() throws RepositoryException {

        //given
        ServiceRepository repository = new ServiceRepository(HibernateUtil.getSessionFactory());
        Long id = (long) (Math.random() * (1000 - 1)) + 1;
        Service service = repository.findById(id);
        System.out.println(service.toString());

        //when
        boolean isIdEqual = (Objects.equals(service.getId(), id));

        //then
        Assertions.assertTrue(isIdEqual);
    }

    @Test
    public void testSaveToTableOrder() throws RepositoryException {

        //given
        ServiceRepository repository = new ServiceRepository(HibernateUtil.getSessionFactory());
        Service first = new Service(1001L, "Service name", 52542L, "Some Service description");
        repository.save(first);
        Service second = repository.findById(1001L);
        repository.delete(1001L);

        //when
        boolean isEqual = (Objects.equals(first.getServiceName(), second.getServiceName()));

        //then
        Assertions.assertTrue(isEqual);
    }

    @Test
    public void testUpdateValueInTable() throws RepositoryException {

        //given
        ServiceRepository repository = new ServiceRepository(HibernateUtil.getSessionFactory());
        long id = 1002L;// (long) (Math.random() * 1000 + 1);
        Service first = repository.findById(id);
        repository.update(new Service(1002L, "Different Service name", 52542L, "Some different Service description"));
        Service second = repository.findById(id);

        //when
        boolean isEqual = (first.equals(second));
        repository.update(first);

        //then
        Assertions.assertFalse(isEqual);

    }

    @Test
    public void testDeleteFromTable() throws RepositoryException {

        //given
        ServiceRepository repository = new ServiceRepository(HibernateUtil.getSessionFactory());
        Service service = new Service(1001L, "Different Service name", 52542L, "Some different Service description");
        repository.save(service);
        Service first = repository.findById(1001L);
        boolean isExist = first.getId() != null;
        repository.delete(1002L);
        Service second = repository.findById(1001L);

        //when
        boolean isExistAfterDelete = second.getId() != null;

        //then
        Assertions.assertNotEquals(isExist, isExistAfterDelete);

    }
}
