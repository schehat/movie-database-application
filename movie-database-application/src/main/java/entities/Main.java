package entities;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import java.io.IOException;

import javax.persistence.*;


public class Main {
    
    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public static void main(String[] args) throws IOException {
        // testing
        emf = Persistence.createEntityManagerFactory("movie");
        em = emf.createEntityManager();
        try {
            insertPersons();
            insertGenres();
            
            em.close();
            emf.close();
        } finally {}


    }

    public static void insertPersons() {
        insertPerson("Detijon", "m");
        insertPerson("Furkan", "w");
        insertPerson("Schehat", "m");
        insertPerson("Jirro", "w");
    }
    public static void insertGenres() {
        insertGenre("Anime");
        insertGenre("Action");
        insertGenre("Comedy");
        insertGenre("Romance");
    }
    
    public static void insertPerson(String name, String c) {
        Person p = new Person();
        p.setName(name);
        p.setSex(c);
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(p);
        tx.commit();
    }
    
    public static void insertGenre(String name) {
        Genre g = new Genre();
        g.setGenre(name);
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(g);
        tx.commit();
    }
    public static void insertMovie(String name, String type, int year) {
        Movie m = new Movie();
        m.setTitle(name);
        m.setType(type);
        m.setYear(year);
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(m);
        tx.commit();
    }
    
    public static void insertMovChar(String character, String alias , Integer pos, Long movid, Person personid) {
        MovieCharacter mc = new MovieCharacter();
        mc.setCharacter(character);
        mc.setAlias(alias);
        mc.setPosition(pos);
        mc.setMovCharId(movid);
        mc.setPerson(personid);

        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(mc);
        tx.commit();
    } 
}