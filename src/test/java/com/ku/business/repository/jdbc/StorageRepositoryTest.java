package com.ku.business.repository.jdbc;

import com.ku.business.entity.Company;
import com.ku.business.entity.Service;
import com.ku.business.entity.Storage;
import com.ku.business.exception.RepositoryException;
import com.ku.business.repository.jdbc.StorageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

public class StorageRepositoryTest {
    public PGSimpleDataSource dataSource = new PGSimpleDataSource();
    StorageRepository storageRepository = new StorageRepository(getConnection());

    public DataSource getConnection() {
        this.dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres?characterEncoding=utf8");
        this.dataSource.setDatabaseName(this.dataSource.getDatabaseName());
        this.dataSource.setUser("postgres");
        this.dataSource.setPassword("postgres");
        return dataSource;
    }

    @Test
    public void testGetListOfStorages() throws RepositoryException {

        //given
        List<Storage> storages = storageRepository.findAll();

        //when
        boolean isNotEmpty = (storages.isEmpty());

        //then
        Assertions.assertFalse(isNotEmpty);
    }

    @RepeatedTest(100)
    public void testReturnStorageById() throws RepositoryException {
        //given
        Long id = (long) (Math.random() * (1000 - 1)) + 1;
        Storage storage = storageRepository.findById(id);

        //when
        boolean isIdEqual = (Objects.equals(storage.getId(), id));

        //then
        Assertions.assertTrue(isIdEqual);
    }

    @Test
    public void testSaveToTableStorages() throws RepositoryException {

        //given
        Storage first = new Storage(1001L, 53252, new Company(328L,"Company name","2412141",true,532L,null,null), new Service(322L, "Service name", 52542L, "Some Service description"));
        storageRepository.save(first);
        Storage second = storageRepository.findById(1001L);
        storageRepository.delete(1001L);

        //when
        boolean isEqual = (Objects.equals(first.getQuantity(), second.getQuantity()));

        //then
        Assertions.assertTrue(isEqual);
    }

    @Test
    public void testUpdateValueInTable() throws RepositoryException {

        //given
        long id = 1002L;// (long) (Math.random() * 1000 + 1);
        Storage first = storageRepository.findById(id);
        storageRepository.update(new Storage(1002L, 51542141, new Company(643L, "Other Company name", "fas542", false, 543L, null, null), new Service(523L, "Different Service name", 45234L, "Some different Service description")));
        Storage second = storageRepository.findById(id);

        //when
        boolean isEqual = (first.equals(second));
        storageRepository.update(first);

        //then
        Assertions.assertFalse(isEqual);

    }

    @Test
    public void testDeleteFromTable() throws RepositoryException {

        //given
        Storage storage = new Storage(1001L, 51542141, new Company(643L, "Other Company name", "fas542", false, 543L, null, null), new Service(523L, "Different Service name", 45234L, "Some different Service description"));
        storageRepository.save(storage);
        Storage first = storageRepository.findById(1001L);
        boolean isExist = first.getId() != null;
        storageRepository.delete(1002L);
        Storage second = storageRepository.findById(1001L);

        //when
        boolean isExistAfterDelete = second.getId() != null;

        //then
        Assertions.assertNotEquals(isExist, isExistAfterDelete);

    }
}
