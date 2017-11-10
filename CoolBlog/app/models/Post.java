package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Enver on 11/10/2017.
 */

@Entity
@Table(name = "post")
public class Post extends Model {

    public static Finder<Long, Post> findPost = new Finder<>(Post.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @Column(name = "category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Column(name = "user")
    private User user;

    @Column(name = "create_date", updatable = false)
    private String creationDate;

    @Column(name = "story", length = 8000)
    private String Story;

    @Column(name = "visible")
    private Integer visible;

    @OneToMany
    private List<Comment> comments;

    @ManyToMany
    private List<User> members;

    //CONSTRUCTOR
    public Post() {
    }

    //GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getAuthor() {
        return user;
    }

    public void setAuthor(User user) {
        this.user = user;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getStory() {
        return Story;
    }

    public void setStory(String story) {
        Story = story;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
