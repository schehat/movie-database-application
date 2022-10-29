package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name = "ue08Movie")
public class Movie {

    @Id @Column(name = "movieId") @GeneratedValue
    private Long movieId;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "year")
    private Integer year;
    
    @Column(name = "type")
    private String type;
    
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "movieGenre")
    private Set<Genre> genres = new HashSet<Genre>();
    
    @OneToMany(mappedBy = "movie", cascade = CascadeType.MERGE)
    private Set<MovieCharacter> movChars = new HashSet<MovieCharacter>();
    
    /**
     * constructor with parameters
     * @param movieId
     * @param title
     * @param year
     * @param type
     */
    public Movie (Long movieId, String title, Integer year, String type) {
        setMovieId(movieId);
        setTitle(title);
        setYear(year);
        setType(type);
    }
    
    /**
     * empty constructor
     */
    public Movie() {}
    
    /**
     * 
     * @param movieId
     */
    public void setMovieId(Long movieId) {this.movieId = movieId;}
    
    /**
     * 
     * @param title
     */
    public void setTitle(String title) {this.title = title;}
    
    /**
     * 
     * @param year
     */
    public void setYear(Integer year) {this.year = year;}
    
    /**
     * 
     * @param type
     */
    public void setType(String type) {this.type = type;}
    
    /**
     * Note: flat copy
     * @param genres
     */
    public void setGenres(Set<Genre> genres) {this.genres = new HashSet<Genre>(genres);}
    
    
    public void setMovChars(Set<MovieCharacter> movChars) {
        this.movChars = new HashSet<MovieCharacter>(movChars);
    }
    
    /**
     * 
     * @return movieId
     */
    public Long getMovieId() {return movieId;}
    
    /**
     * 
     * @return title
     */
    public String getTitle() {return title;}
    
    /**
     * 
     * @return year
     */
    public Integer getYear() {return year;}
    
    /**
     * 
     * @return type
     */
    public String getType() {return type;}
    
    /**
     * 
     * @return genres
     */
    public Set<Genre> getGenres() {return genres;}
    
    /**
     * 
     * @return movChars
     */
    public Set<MovieCharacter> getMovChars() {return movChars;}
}