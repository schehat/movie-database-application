package de.hsh.dbs2.imdb.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.hsh.dbs2.imdb.factories.PersonFactory;
import de.hsh.dbs2.imdb.util.EMFactory;
import entities.Person;


public class PersonManager {

	/**
	 * Liefert eine Liste aller Personen, deren Name den Suchstring enthaelt.
	 * @param text Suchstring
	 * @return Liste mit passenden Personennamen, die in der Datenbank eingetragen sind.
	 * @throws Exception
	 */
	public List<String> getPersonList(String text) throws Exception {
	    System.out.println("getPersonList");
	    
	    EntityManager em = EMFactory.getEntitymManager().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        List<String> personsS = new ArrayList<String>();
        try {
            tx.begin();
            
            List<Person> persons = PersonFactory.findByPersonAll(em);
            for (Person p: persons) {
                if (p.getName().contains(text)) {
                    personsS.add(p.getName());
                }
            }
            
            Collections.sort(personsS);
            
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
        return personsS;
	}
}