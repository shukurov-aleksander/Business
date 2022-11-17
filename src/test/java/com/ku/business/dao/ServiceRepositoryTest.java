package com.ku.business.dao;

import com.ku.business.entity.Service;
import com.ku.business.exception.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

public class ServiceRepositoryTest {
    public PGSimpleDataSource dataSource = new PGSimpleDataSource();
    ServiceRepository serviceRepository = new ServiceRepository(getConnection());

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
        List<Service> services = serviceRepository.findAll();
        for (Service s: services
        ) {
            System.out.println(s);
        }

        //when
        boolean isNotEmpty = (services.isEmpty());

        //then
        Assertions.assertFalse(isNotEmpty);
    }

    @RepeatedTest(100)
    public void testReturnOrderById() throws RepositoryException {
        //given
        Long id = (long) (Math.random() * (1000 - 1)) + 1;
        Service service = serviceRepository.findById(id);
        System.out.println(service);

        //when
        boolean isIdEqual = (Objects.equals(service.getId(), id));

        //then
        Assertions.assertTrue(isIdEqual);
    }

    @Test
    public void testSaveToTableOrder() throws RepositoryException {

        //given
        Service first = new Service(1001L, "Service name", 52542L, "Some Service description");
        serviceRepository.save(first);
        Service second = serviceRepository.findById(1001L);
        //orderRepository.delete(1001L);

        //when
        boolean isEqual = (Objects.equals(first.getServiceName(), second.getServiceName()));

        //then
        Assertions.assertTrue(isEqual);
    }

    @Test
    public void testUpdateValueInTable() throws RepositoryException {

        //given
        long id = 1001L;// (long) (Math.random() * 1000 + 1);
        Service first = serviceRepository.findById(id);
        serviceRepository.update(new Service(1001L, "Different Service name", 52542L, "Some different Service description"));
        Service second = serviceRepository.findById(id);

        //when
        boolean isEqual = (first.equals(second));
        serviceRepository.update(first);

        //then
        Assertions.assertFalse(isEqual);

    }

    @Test
    public void testDeleteFromTable() throws RepositoryException {

        //given
        Service service = new Service(1001L, "Different Service name", 52542L, "Some different Service description");
        serviceRepository.save(service);
        Service first = serviceRepository.findById(1001L);
        boolean isExist = first.getId() != null;
        serviceRepository.delete(1001L);
        Service second = serviceRepository.findById(1001L);

        //when
        boolean isExistAfterDelete = second.getId() != null;

        //then
        Assertions.assertNotEquals(isExist, isExistAfterDelete);

    }
}
