package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Enver on 11/1/2017.
 */

@Entity
@Table(name = "user")
public class User extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "email", unique = true, updatable = false, length = 100)
    private String email;

    @Column(name = "birth_date", updatable = false)
    private String birthDate;

    @Column(name = "create_date", updatable = false)
    private String creationDate;

    //Constructor
    public User() {
    }

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }


}
