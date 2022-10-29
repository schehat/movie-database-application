package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name = "ue08MovieCharacter")
public class MovieCharacter {

    @Id @Column(name = "movCharId") @GeneratedValue
    private Long movCharId;
    
    @Column(name = "character")
    private String character;
    
    @Column(name = "alias")
    private String alias;
    
    @Column(name = "position")
    private Integer position;

    @ManyToOne
    private Movie movie;
    
    @ManyToOne
    private Person person;
    
    /**
     * constructor with parameters
     * @param movCharId
     * @param character
     * @param alias
     * @param type
     */
    public MovieCharacter (Long movCharId, String character, String alias, Integer position, Movie movie, Person person) {
        setMovCharId(movCharId);
        setCharacter(character);
        setAlias(alias);
        setPosition(position);
        setMovie(movie);
        setPerson(person);
    }
    
    /**
     * empty constructor
     */
    public MovieCharacter() {}
    
    /**
     * 
     * @param movCharId
     */
    public void setMovCharId(Long movCharId) {this.movCharId = movCharId;}
    
    /**
     * 
     * @param character
     */
    public void setCharacter(String character) {this.character = character;}
    
    /**
     * 
     * @param alias
     */
    public void setAlias(String alias) {this.alias = alias;}
    
    /**
     * 
     * @param position
     */
    public void setPosition(Integer position) {this.position = position;}
    
    /**
     * 
     * @param movie
     */
    public void setMovie(Movie movie) {this.movie = movie;}
    
    /**
     * 
     * @param person
     */
    public void setPerson(Person person) {this.person = person;}
    
    /**
     * 
     * @return movCharId
     */
    public Long getMovCharId() {return movCharId;}
    
    /**
     * 
     * @return character
     */
    public String getCharacter() {return character;}
    
    /**
     * 
     * @return alias
     */
    public String getAlias() {return alias;}
    
    /**
     * 
     * @return position
     */
    public Integer getPosition() {return position;}
    
    /**
     * 
     * @return movie
     */
    public Movie getMovie() {return movie;}
    
    /**
     * 
     * @return person
     */
    public Person getPerson() {return person;}
}