package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Enver on 11/5/2017.
 */
@Entity
@Table(name = "role")
public class Role extends Model {

    public static Model.Finder<Long, Role> findRole = new Finder<>(Role.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", length = 10)
    private String name;


    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User role: " + name;
    }
}
