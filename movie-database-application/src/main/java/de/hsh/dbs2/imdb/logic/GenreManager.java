package de.hsh.dbs2.imdb.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.hsh.dbs2.imdb.factories.GenreFactory;
import de.hsh.dbs2.imdb.util.EMFactory;
import entities.Genre;


public class GenreManager {

	/**
	 * Ermittelt eine vollstaendige Liste aller in der Datenbank abgelegten Genres
	 * Die Genres werden alphabetisch sortiert zurueckgeliefert.
	 * @return Alle Genre-Namen als String-Liste
	 * @throws Exception
	 */
	public List<String> getGenres() throws Exception {
	    System.out.println("getGenres");
	    
	    EntityManager em = EMFactory.getEntitymManager().createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    
	    List<String> genresS = new ArrayList<String>();
	    try {
	        tx.begin();
	        
	        List<Genre> genres = GenreFactory.findByGenreAll(em);
	        for (Genre g: genres) {
	            genresS.add(g.getGenre());
	        }
	        
	        Collections.sort(genresS);
	        
	        tx.commit();
	    } finally {
	        if (tx.isActive()) {
	            tx.rollback();
	        }
	        em.close();
	    }
	    return genresS;
	}
}
