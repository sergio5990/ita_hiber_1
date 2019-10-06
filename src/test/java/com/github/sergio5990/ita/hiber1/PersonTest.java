package com.github.sergio5990.ita.hiber1;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import com.github.sergio5990.ita.hiber1.pojo.Person;
import com.github.sergio5990.ita.hiber1.util.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {
    @Test
    public void saveTest() {
        Person person = new Person(29, "sergey", "kruk");
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } catch (RollbackException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        Person personFromDb = em.find(Person.class, person.getId());
        assertEquals(person, personFromDb);
        em.getTransaction().commit();
    }

    @AfterAll
    public static void cleanUp() {
        HibernateUtil.close();
    }
}
