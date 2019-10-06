package com.github.sergio5990.ita.hiber1.loader;

import javax.persistence.EntityManager;

import com.github.sergio5990.ita.hiber1.util.HibernateUtil;
import com.github.sergio5990.ita.hiber1.pojo.Person;

public class PersonLoader {

    public static void main(String[] args) throws Exception {
        Person person = new Person(29, "Sergey", "Kruk");
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        HibernateUtil.close();
    }
}


