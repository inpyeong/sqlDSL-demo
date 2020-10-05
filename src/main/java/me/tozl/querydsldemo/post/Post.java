package me.tozl.querydsldemo.post;

import javax.persistence.*;
import java.util.Date;

@Entity
/**
 * 도메인 엔티티가 더러워 진다는 단점이 있다.
 */
//@NamedQuery(name = "Post.findByTitle", query = "SELECT p from Post AS p WHERE p.title = ?1")
public class Post {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
