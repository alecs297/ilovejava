package dev.palmes.ilovejava.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
public class Thread {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    private boolean locked = false;
    private boolean removed = false;

    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Post entry;

    @OneToMany
    private Set<Post> posts = new HashSet<>();

    @ManyToMany
    @JoinColumn(nullable = false)
    private List<Tag> tags = new ArrayList<>();


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Post getEntry() {
        return entry;
    }

    public void setEntry(Post entry) {
        this.entry = entry;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    protected void addPost(Post post) {
        this.posts.add(post);
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Thread)) return false;
        Thread thread = (Thread) o;
        return getId().equals(thread.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
