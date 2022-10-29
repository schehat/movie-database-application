package de.hsh.dbs2.imdb.logic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.hsh.dbs2.imdb.factories.GenreFactory;
import de.hsh.dbs2.imdb.factories.MovieFactory;
import de.hsh.dbs2.imdb.factories.PersonFactory;
import de.hsh.dbs2.imdb.logic.dto.*;
import de.hsh.dbs2.imdb.util.EMFactory;
import entities.Genre;
import entities.Movie;
import entities.MovieCharacter;

public class MovieManager {

	/**
	 * Ermittelt alle Filme, deren Filmtitel den Suchstring enthaelt.
	 * Wenn der String leer ist, sollen alle Filme zurueckgegeben werden.
	 * Der Suchstring soll ohne Ruecksicht auf Gross/Kleinschreibung verarbeitet werden.
	 * @param search Suchstring. 
	 * @return Liste aller passenden Filme als MovieDTO
	 * @throws Exception
	 */
	public List<MovieDTO> getMovieList(String search) throws Exception {
	    System.out.println("getMovieList");
	    
	    EntityManager em = EMFactory.getEntitymManager().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        ArrayList<MovieDTO> mDTO  = new ArrayList<MovieDTO>();
        try {
            tx.begin();
            
            ArrayList<Movie> movies = (ArrayList<Movie>) MovieFactory.findByMovieAll(em);
            for (Movie m: movies) {
                if (m.getTitle().contains(search) || search.equals("")) {
                    mDTO.add(getMovie(m.getMovieId()));
                }
            }
            
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
        return mDTO;
	}

	/**
	 * Speichert die uebergebene Version des Films neu in der Datenbank oder aktualisiert den
	 * existierenden Film.
	 * Dazu werden die Daten des Films selbst (Titel, Jahr, Typ) beruecksichtigt,
	 * aber auch alle Genres, die dem Film zugeordnet sind und die Liste der Charaktere
	 * auf den neuen Stand gebracht.
	 * @param movie Film-Objekt mit Genres und Charakteren.
	 * @throws Exception
	 */
	public void insertUpdateMovie(MovieDTO movieDTO) throws Exception {
	    System.out.println("insertUpdateMovie");
	    
	    EntityManager em = EMFactory.getEntitymManager().createEntityManager();
        EntityTransaction tx = em.getTransaction();
	    
        boolean alreadyPersist = false;
        try {
            tx.begin();
            
            Movie movie = new Movie(movieDTO.getId(), movieDTO.getTitle(), movieDTO.getYear(), movieDTO.getType());
            
            // if movieDTO exists in database delete all dependencies
            if (movieDTO.getId() != null) {
                alreadyPersist = true;
                deleteMovie(movieDTO.getId());
            }
            
            for (String gS : movieDTO.getGenres()) {
                // get genreId for this specific genre. In table genre is unique constraint for genre
                Genre genre = GenreFactory.getGenreIdByGenre(em, gS);
                movie.getGenres().add(genre);
            }
            
            int position = 1;
            for (CharacterDTO cDTO : movieDTO.getCharacters()) {
                MovieCharacter mc = new MovieCharacter();
                mc.setCharacter(cDTO.getCharacter());
                mc.setAlias(cDTO.getAlias());
                mc.setMovCharId(movie.getMovieId());
                mc.setPosition(position);
                ++position;
                mc.setMovie(movie);
                mc.setPerson(PersonFactory.getPersonIdByName(em, cDTO.getPlayer()));
                
                movie.getMovChars().add(mc);
            }
            
            if (alreadyPersist) {
                em.merge(movie);
                em.flush();
            } else {
                em.persist(movie);
            }
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
	}

	/**
	 * Loescht einen Film aus der Datenbank. Es werden auch alle abhaengigen Objekte geloescht,
	 * d.h. alle Charaktere und alle Genre-Zuordnungen.
	 * @param movie
	 * @throws Exception
	 */
	public void deleteMovie(long movieId) throws Exception {
	    System.out.println("deleteMovie");
	    
	    EntityManager em = EMFactory.getEntitymManager().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
                       
            Movie movie = MovieFactory.findByMovieId(em, movieId);
            for (MovieCharacter mc: movie.getMovChars()) {
                em.remove(mc);
            }
            em.remove(movie);
            
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
	}

	/**
	 * Liefert die Daten eines einzelnen Movies zurück
	 * @param movieId
	 * @return
	 * @throws Exception
	 */
	public MovieDTO getMovie(long movieId) throws Exception {
	    System.out.println("getMovie");
	    
	    // nested transaction 
	    EntityManager em = EMFactory.getEntitymManager().createEntityManager();
        EntityTransaction tx = em.getTransaction();
	    
	    MovieDTO mDTO = new MovieDTO();
	   
	    try {
            tx.begin();
	    
            Movie movie = MovieFactory.findByMovieId(em, movieId);
    	    mDTO.setId(movie.getMovieId());
            mDTO.setTitle(movie.getTitle());
            mDTO.setType(String.valueOf(movie.getType()));
            mDTO.setYear(movie.getYear());
            
            for (Genre g: movie.getGenres()) {
                mDTO.addGenre(g.getGenre());
            }
            
            for (MovieCharacter mc: movie.getMovChars()) {
                CharacterDTO cDTO = new CharacterDTO();
                cDTO.setCharacter(mc.getCharacter());
                cDTO.setAlias(mc.getAlias());
                cDTO.setPlayer(mc.getPerson().getName());
                
                mDTO.addCharacter(cDTO);
            }
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
		return mDTO;
	}
}
