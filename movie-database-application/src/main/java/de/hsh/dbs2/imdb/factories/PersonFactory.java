package de.hsh.dbs2.imdb.factories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Person;

public class PersonFactory {
    public static List<Person> findByPersonAll(EntityManager em) {
        TypedQuery<Person> q = em.createQuery("SELECT p FROM Person p", Person.class);
        return q.getResultList();
    }
    
    public static Person getPersonIdByName(EntityManager em, String name) {
        TypedQuery<Person> q = em.createQuery("SELECT p FROM Person p WHERE p.name = :name", Person.class);
        q.setParameter("name", name);
        return q.getSingleResult();
    }
}
