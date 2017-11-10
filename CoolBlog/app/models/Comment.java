package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Enver on 11/10/2017.
 */

@Entity
@Table(name = "comment")
public class Comment extends Model {

    public static Finder<Long, Comment> findComment = new Finder<>(Comment.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Column(name = "user")
    private User user;

    @Column(name = "create_date", updatable = false)
    private String creationDate;

    @ManyToOne
    @Column(name = "post")
    private Post post;

    @Column(name = "content", length = 1000)
    private String content;

    //CONSTRUCTORS

    public Comment() {
    }

    public Comment(User user, Post post, String content) {
        this.user = user;
        this.post = post;
        this.content = content;
    }

    //GETTERS AND SETTERS


    public Long getId() {
        return id;
    }

    public User getCommentator() {
        return user;
    }

    public void setCommentator(User user) {
        this.user = user;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
