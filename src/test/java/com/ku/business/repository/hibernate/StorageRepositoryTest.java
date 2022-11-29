package com.ku.business.repository.hibernate;

import com.ku.business.HibernateUtil;
import com.ku.business.entity.Company;
import com.ku.business.entity.Service;
import com.ku.business.entity.Storage;
import com.ku.business.exception.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

public class StorageRepositoryTest {

    @Test
    public void testGetListOfStorages() throws RepositoryException {

        //given
        StorageRepository repository = new StorageRepository(HibernateUtil.getSessionFactory());
        List<Storage> storages = repository.findAll();
        for (Storage s: storages
             ) {
            System.out.println(s.toString());
        }

        //when
        boolean isNotEmpty = (storages.isEmpty());

        //then
        Assertions.assertFalse(isNotEmpty);
    }

    @RepeatedTest(100)
    public void testReturnStorageById() throws RepositoryException {

        //given
        StorageRepository repository = new StorageRepository(HibernateUtil.getSessionFactory());
        Long id = (long) (Math.random() * (1000 - 1)) + 1;
        Storage storage = repository.findById(id);
        System.out.println(storage.toString());

        //when
        boolean isIdEqual = (Objects.equals(storage.getId(), id));

        //then
        Assertions.assertTrue(isIdEqual);
    }

    @Test
    public void testSaveToTableStorages() throws RepositoryException {

        //given
        StorageRepository repository = new StorageRepository(HibernateUtil.getSessionFactory());
        Storage first = new Storage(1001L, 53252, new Company(328L,"Company name","2412141",true,532L,null,null), new Service(322L, "Service name", 52542L, "Some Service description"));
        repository.save(first);
        Storage second = repository.findById(1001L);
        repository.delete(1001L);

        //when
        boolean isEqual = (Objects.equals(first.getQuantity(), second.getQuantity()));

        //then
        Assertions.assertTrue(isEqual);
    }

    @Test
    public void testUpdateValueInTable() throws RepositoryException {

        //given
        StorageRepository repository = new StorageRepository(HibernateUtil.getSessionFactory());
        long id = 1002L;// (long) (Math.random() * 1000 + 1);
        Storage first = repository.findById(id);
        repository.update(new Storage(1002L, 51542141, new Company(643L, "Other Company name", "fas542", false, 543L, null, null), new Service(523L, "Different Service name", 45234L, "Some different Service description")));
        Storage second = repository.findById(id);

        //when
        boolean isEqual = (first.equals(second));
        repository.update(first);

        //then
        Assertions.assertFalse(isEqual);

    }

    @Test
    public void testDeleteFromTable() throws RepositoryException {

        //given
        StorageRepository repository = new StorageRepository(HibernateUtil.getSessionFactory());
        Storage storage = new Storage(1001L, 51542141, new Company(643L, "Other Company name", "fas542", false, 543L, null, null), new Service(523L, "Different Service name", 45234L, "Some different Service description"));
        repository.save(storage);
        Storage first = repository.findById(1001L);
        boolean isExist = first.getId() != null;
        repository.delete(1002L);
        Storage second = repository.findById(1001L);

        //when
        boolean isExistAfterDelete = second.getId() != null;

        //then
        Assertions.assertNotEquals(isExist, isExistAfterDelete);

    }
}
