package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "ue08Person")
public class Person {
    
    @Id @Column(name = "personId") @GeneratedValue
    private Long personId;
    
    @Column(name = "name", unique = true)
    private String name;
    
    @Column(name = "sex")
    private String sex;
    
    /**
     * constructor with parameters
     * @param personId
     * @param name
     * @param sex
     */
    public Person (Long personId, String name, String sex) {
        setPersonId(personId);
        setName(name);
        setSex(sex);
    }
    
    /**
     * empty constructor
     */
    public Person() {}
    
    /**
     * 
     * @param personId
     */
    public void setPersonId(Long personId) {this.personId = personId;}
    
    /**
     * 
     * @param name
     */
    public void setName(String name) {this.name = name;}
    
    /**
     * 
     * @param sex
     */
    public void setSex(String sex) {this.sex = sex;}
    
    /**
     * 
     * @return personId
     */
    public Long getPersonId() {return personId;}
    
    /**
     * 
     * @return name
     */
    public String getName() {return name;}
    
    /**
     * 
     * @return sex
     */
    public String getSex() {return sex;}
}