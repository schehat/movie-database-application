package de.hsh.dbs2.imdb.factories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Movie;

public class MovieFactory {
    
    public static List<Movie> findByMovieAll(EntityManager em) {
        TypedQuery<Movie> q = em.createQuery("SELECT m FROM Movie m", Movie.class);
        return q.getResultList();
    }
    
    public static Movie findByMovieId(EntityManager em, long movieId) {
        TypedQuery<Movie> q = em.createQuery("SELECT m FROM Movie m WHERE m.movieId = :id", Movie.class);
        q.setParameter("id", movieId);
        return q.getSingleResult();
    }
}
