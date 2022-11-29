package com.ku.business.repository.hibernate;

import com.ku.business.HibernateUtil;
import com.ku.business.entity.Content;
import com.ku.business.entity.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

public class ContentRepositoryTest {

    @Test
    public void testGetListOfContents() {

        //given
        ContentRepository repository = new ContentRepository(HibernateUtil.getSessionFactory());
        List<Content> contents = repository.findAll();
        for (Content c: contents
             ) {
            System.out.println(c.toString());
        }
        //when
        boolean isNotEmpty = (contents.isEmpty());

        //then
        Assertions.assertFalse(isNotEmpty);
    }

    @RepeatedTest(100)
    public void testReturnContentById() {

        //given
        ContentRepository repository = new ContentRepository(HibernateUtil.getSessionFactory());
        Long id = (long) (Math.random() * (1000 - 1)) + 1;
        Content content = repository.findById(id);
        System.out.println(content);

        //when
        boolean isIdEqual = (Objects.equals(content.getId(), id));

        //then
        Assertions.assertTrue(isIdEqual);
    }

    @Test
    public void testAddToContentTable() {

        //given
        ContentRepository repository = new ContentRepository(HibernateUtil.getSessionFactory());
        Content first = new Content(1001L, 255L, new Service(412L, null, null, null), null);
        repository.save(first);
        Content second = repository.findById(1001L);
        repository.delete(1001L);

        //when
        boolean isEqual = (Objects.equals(first.getQuantity(), second.getQuantity()));

        //then
        Assertions.assertTrue(isEqual);
    }

    @Test
    public void testUpdateValueInContentTable() {

        //given
        ContentRepository repository = new ContentRepository(HibernateUtil.getSessionFactory());
        long id = (long) (Math.random() * 1000 + 1);
        Content first = repository.findById(id);
        repository.update(new Content(1002L, 322L, new Service(965L, null, null, null), null));
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
        ContentRepository repository = new ContentRepository(HibernateUtil.getSessionFactory());
        Content content = new Content(1001L, 322L, new Service(965L, null, null, null), null);
        repository.save(content);
        Content first = repository.findById(1001L);
        boolean isExist = first.getId() != null;
        repository.delete(1002L);
        Content second = repository.findById(1001L);

        //when
        boolean isExistAfterDelete = second.getId() != null;

        //then
        Assertions.assertNotEquals(isExist, isExistAfterDelete);
    }
}
