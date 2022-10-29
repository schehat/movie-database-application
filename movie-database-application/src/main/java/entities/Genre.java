package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity @Table(name = "ue08Genre")
public class Genre {

    @Id @Column(name = "genreId") @GeneratedValue
    private Long genreId;
    
    @Column(name = "genre", unique = true)
    private String genre;
    
    /**
     * constructor with parameters
     * @param genreId
     * @param genre
     */
    public Genre (Long genreId, String genre) {
        setGenreId(genreId);
        setGenre(genre);
    }
    
    /*
     * empty constructor
     */
    public Genre() {}
    
    /**
     * 
     * @param genreId
     */
    public void setGenreId(Long genreId) {this.genreId = genreId;}
    
    /**
     * 
     * @param genre
     */
    public void setGenre(String genre) {this.genre = genre;}
    
    /**
     * 
     * @return genreId
     */
    public Long getGenreId() {return genreId;}
    
    /**
     * 
     * @return genre
     */
    public String getGenre() {return genre;}
}