package models;

import com.avaje.ebean.Model;
import controllers.LoginController;
import helpers.MD5Hash;
import play.data.DynamicForm;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Enver on 11/1/2017.
 */

@Entity
@Table(name = "user")
public class User extends Model {

    public static Model.Finder<Long, User> find = new Finder<>(User.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "email", unique = true, updatable = false, length = 100)
    private String email;

    @Column(name = "password", length = 100)
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public static User findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }

    public static boolean createUser(DynamicForm dynamicForm) {
        boolean isOk = true;
        User user = new User();
        try {
            user.setUsername(dynamicForm.get("username"));
            user.setEmail(dynamicForm.get("email"));
            user.setPassword(MD5Hash.getEncriptedPasswordMD5(dynamicForm.get("password")));
            user.setBirthDate(dynamicForm.get("birth_date"));
            user.setCreationDate(new SimpleDateFormat("EEE, dd.MM.yyyy - HH:mm").format(new Date()));
            user.save();
            LoginController.createSession(user);
        }catch (PersistenceException e){
            isOk = false;
        }
        return isOk;
    }
}
