package de.hsh.dbs2.imdb.factories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Genre;

public class GenreFactory {

    
    public static List<Genre> findByGenreAll(EntityManager em) {
        TypedQuery<Genre> q = em.createQuery("SELECT g FROM Genre g", Genre.class);
        return q.getResultList();
    }
    
    public static Genre getGenreIdByGenre(EntityManager em, String genre) {
        TypedQuery<Genre> q = em.createQuery("SELECT g FROM Genre g WHERE g.genre = :genre", Genre.class);
        q.setParameter("genre", genre);
        return q.getSingleResult();
    }
}
